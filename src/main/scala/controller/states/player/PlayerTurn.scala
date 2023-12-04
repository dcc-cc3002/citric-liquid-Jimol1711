package cl.uchile.dcc.citric
package controller.states.player

import controller.states.AbstractState

import cl.uchile.dcc.citric.controller.GameController

/** Player turn state of a game.
 *
 * @param context the context of a game.
 *
 */
class PlayerTurn(context: GameController) extends AbstractState(context) {

  context.getCurrentPlayer.setStars(context.getCurrentPlayer.getStars + (math.floor(context.getCurrentChapter / 5) + 1).toInt)
  override def rollDice(): Unit = {
    val roll = context.getCurrentPlayer.rollDice()
    context.setMovingRoll(roll)
    context.setState(new Moving(context))
  }

  override def isPlayerTurnState: Boolean = true

}
