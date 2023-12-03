package cl.uchile.dcc.citric
package controller.states.player

import controller.states.AbstractState

import cl.uchile.dcc.citric.controller.GameController

class PlayerTurn(context: GameController) extends AbstractState(context) {

  override def rollDice(): Unit = {
    context.setState(new Moving(context))
  }

  override def isPlayerTurnState: Boolean = true

}
