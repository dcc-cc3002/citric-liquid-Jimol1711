package cl.uchile.dcc.citric
package controller.states.player

import controller.states.AbstractState

import cl.uchile.dcc.citric.controller.GameController

class Recovery(context: GameController) extends AbstractState {

  override def sufficientRoll(): Unit = {

  }

  override def inSufficientRoll(): Unit = {
    context.setState(Chapter)
  }

}
