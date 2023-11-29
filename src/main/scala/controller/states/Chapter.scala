package cl.uchile.dcc.citric
package controller.states

import controller.GameController

import cl.uchile.dcc.citric.controller.states.player.PlayerTurn
import cl.uchile.dcc.citric.model.units.PlayerCharacter

class Chapter extends AbstractState {
  def checkNorma6(controller: GameController): Unit = {
    if (/* here should be the checking that a Norma6 was reached */) {
      controller.setState(new GameOver)
    }
  }

  def nextPlayer(controller: GameController): Unit = {
    controller.setState(new PlayerTurn)
  }
}
