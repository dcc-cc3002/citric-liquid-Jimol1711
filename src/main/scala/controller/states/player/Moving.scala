package cl.uchile.dcc.citric
package controller.states.player

import controller.states.AbstractState

import cl.uchile.dcc.citric.controller.GameController

class Moving(context: GameController) extends AbstractState(context) {

  override def stop(): Unit = {

  }

  override def outOfMovements(): Unit = {

  }

}
