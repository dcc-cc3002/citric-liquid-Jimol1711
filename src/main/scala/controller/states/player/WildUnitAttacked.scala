package cl.uchile.dcc.citric
package controller.states.player

import controller.states.AbstractState

import cl.uchile.dcc.citric.controller.GameController

class WildUnitAttacked(context: GameController) extends AbstractState(context) {

  override def isWildUnitAttackedState: Boolean = true

}
