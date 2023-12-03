package cl.uchile.dcc.citric
package controller.states.player

import controller.states.AbstractState

import cl.uchile.dcc.citric.controller.GameController

class Moving(context: GameController) extends AbstractState(context) {

  override def choosePath(): Unit = {
    context.setState(new Moving(context))
  }

  override def stopMovement(): Unit = {
    context.setState(new Combat(context))
  }

  override def outOfMovements(): Unit = {
    context.setState(new Combat(context))
  }

  override def isMovingState: Boolean = true

}
