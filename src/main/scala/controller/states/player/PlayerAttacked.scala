package cl.uchile.dcc.citric
package controller.states.player

import controller.states.AbstractState

import cl.uchile.dcc.citric.controller.GameController

class PlayerAttacked(context: GameController) extends AbstractState(context) {

  override def isPlayerAttackedState: Boolean = true

}
