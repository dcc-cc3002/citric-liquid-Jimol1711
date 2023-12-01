package cl.uchile.dcc.citric
package controller.states

import controller.GameController

import cl.uchile.dcc.citric.controller.states.player.PlayerTurn
import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

class Chapter extends AbstractState {

  override def checkNorma6(): Unit = {
    if (/* here should be the checking that a Norma6 was reached */) {
      context.setState(new GameOver)
    }
  }

  override def nextPlayer(): Unit = {
    context.setState(new PlayerTurn)
  }

}
