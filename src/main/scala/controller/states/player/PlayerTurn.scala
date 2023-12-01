package cl.uchile.dcc.citric
package controller.states.player

import controller.states.AbstractState

class PlayerTurn extends AbstractState {

  override def isKO(): Unit = {
    context.setState(new Recovery)
  }

  override def moveRoll(): Unit = {
    context.setState(new OnPanel)
  }

}
