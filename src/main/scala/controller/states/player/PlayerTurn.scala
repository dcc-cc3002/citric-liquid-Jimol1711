package cl.uchile.dcc.citric
package controller.states.player

import controller.states.AbstractState

import cl.uchile.dcc.citric.controller.GameController

class PlayerTurn(context: GameController) extends AbstractState(context) {

  context.currentPlayerTurn()

  override def isKO(): Unit = {
    if (context.getCurrentPlayer.KO())
      context.setState(new Recovery(context))
  }

  isKO()

  override def moveRoll(): Unit = {
    context.setState(new OnPanel(context))
  }

  override def isPlayerTurnState: Boolean = true

}
