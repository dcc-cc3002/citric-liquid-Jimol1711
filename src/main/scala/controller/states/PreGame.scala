package cl.uchile.dcc.citric
package controller.states

import controller.GameController

import cl.uchile.dcc.citric.model.Units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

class PreGame(controller: GameController, newPlayers: ArrayBuffer[PlayerCharacter]) extends AbstractState {

  override def setTurns(): Unit = {
    for (p <- newPlayers) {
      controller.players += p
    }
    controller.orderPlayers()
  }

}
