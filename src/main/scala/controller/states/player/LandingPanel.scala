package cl.uchile.dcc.citric
package controller.states.player

import controller.states.{AbstractState, Chapter}

import cl.uchile.dcc.citric.controller.GameController
import cl.uchile.dcc.citric.model.panels.{NeutralPanel, Panel}

/** Landing panel state of a game.
 *
 * @param context the context of a game.
 *
 */
class LandingPanel(context: GameController) extends AbstractState(context) {

  /** The panel the current player is in. */
  var currentPanel: Panel = new NeutralPanel

  for (panel <- context.getBoard.panels) {
    if (panel.getCharacters.contains(context.getCurrentPlayer)) {
      currentPanel = panel
    }
  }
  override def doEffect(): Unit = {
    currentPanel(context.getCurrentPlayer)
    context.setState(new Chapter(context))
  }

  override def isLandingPanelState: Boolean = true

}
