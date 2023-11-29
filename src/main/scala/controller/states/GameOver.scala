package cl.uchile.dcc.citric
package controller.states

import controller.GameController

import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

class GameOver(players: ArrayBuffer[PlayerCharacter]) extends AbstractState {

  override def reset(): Unit = {
    context.setState(new PreGame(new GameController, players))
  }

}
