package cl.uchile.dcc.citric
package controller.states.player

import controller.states.AbstractState

import cl.uchile.dcc.citric.controller.GameController
import cl.uchile.dcc.citric.model.panels.{NeutralPanel, Panel}

import scala.util.control.Breaks._

/** Moving state of a game.
 *
 * @param context the context of a game.
 *
 */
class Moving(context: GameController) extends AbstractState(context) {

  /** The panel the player is in. */
  private var currentPanel: Panel = new NeutralPanel

  /** User input for testing purposes. */
  var userInput: String = ""

  for (panel <- context.getBoard.panels) {
    if (panel.getCharacters.contains(context.getCurrentPlayer)) {
      currentPanel = panel
    }
  }

  /* Each time we enter moving state the move method is called */
  move()

  /** Moving method.
   *
   * @param chosen the direction chosen by the user, front by default (represented as a 1,
   * since it is the element on index 1 of the panels array buffer.
   *
   */
  private def move(chosen: Int = 1): Unit = {

    var currentMovingRoll: Int = context.getMovingRoll

    while (currentMovingRoll != 0) {

      if (currentPanel.getPanels.length == 2) {
        currentPanel.removeCharacter(context.getCurrentPlayer)
        currentPanel.getPanels(1).addCharacter(context.getCurrentPlayer)
        currentPanel = currentPanel.getPanels(1)
        currentMovingRoll -= 1
      }

      if (currentPanel.getPanels.length == 3) {
        currentPanel.removeCharacter(context.getCurrentPlayer)
        currentPanel.getPanels(chosen).addCharacter(context.getCurrentPlayer)
        currentPanel = currentPanel.getPanels(chosen)
        currentMovingRoll -= 1
      }

      // Stopping at home panel
      if (currentPanel.isHomePanel(context.getCurrentPlayer)) {
        println("Would you like to rest at home? Yes/No")
        // scala.io.StdIn.readLine()
        if (userInput == "Yes") {
          stopMovement()
          break
        }
      }

      if (currentPanel.getPanels.length == 3) {
        choosePath()
        break
      }
    }
    if (!context.isCombatState) {
      outOfMovements()
    }

    }

  override def choosePath(): Unit = {
    if (userInput=="Front") {
      move()
    } else if (userInput=="Right") {
      move(2)
    }
    context.setState(new Moving(context))
  }

  override def stopMovement(): Unit = {
    context.setState(new Combat(context))
  }

  override def outOfMovements(): Unit = {
    context.setState(new Combat(context))
  }

  override def isMovingState: Boolean = true

}
