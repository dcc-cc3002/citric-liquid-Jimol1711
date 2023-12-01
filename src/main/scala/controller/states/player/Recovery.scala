package cl.uchile.dcc.citric
package controller.states.player

import controller.states.{AbstractState, Chapter}

import cl.uchile.dcc.citric.controller.GameController

class Recovery extends AbstractState {

  override def sufficientRoll(): Unit = {
    context.setState(new PlayerTurn)
  }

  override def inSufficientRoll(): Unit = {
    context.setState(new Chapter)
  }

}
