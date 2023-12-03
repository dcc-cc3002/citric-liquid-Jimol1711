package cl.uchile.dcc.citric
package controller.states.player

import controller.states.{AbstractState, Chapter}

import cl.uchile.dcc.citric.controller.GameController
import cl.uchile.dcc.citric.model.units.PlayerCharacter

class Recovery(context: GameController) extends AbstractState(context) {

  val currentPlayer: PlayerCharacter = context.getCurrentPlayer

  override def sufficientRoll(): Unit = {
    val roll: Int = context.dice()
    currentPlayer.setCurrentHp(roll)
    context.setCurrentPlayer(context.getCurrentPlayerTurn - 1)
    context.setState(new PlayerTurn(context))
  }

  override def inSufficientRoll(): Unit = {
    context.setState(new Chapter(context))
  }

  override def isRecoveryState: Boolean = true

}
