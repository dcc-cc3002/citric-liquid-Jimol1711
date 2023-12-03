package cl.uchile.dcc.citric
package controller.states.player

import controller.states.AbstractState

import cl.uchile.dcc.citric.controller.GameController

class Wait(context: GameController) extends AbstractState(context) {

  override def defend(): Unit = {
    context.setState(new Combat(context))
  }

  override def evade(): Unit = {
    context.setState(new Combat(context))
  }

  override def isWaitState: Boolean = true

}