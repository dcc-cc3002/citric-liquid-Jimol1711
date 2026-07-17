package cl.uchile.dcc.citric
package controller.states.player

import controller.states.AbstractState

import cl.uchile.dcc.citric.controller.GameController

/** Combat state of a game.
 *
 * @param context the context of a game.
 *
 */
class Combat(context: GameController) extends AbstractState(context) {

  override def attack(): Unit = {
    context.setState(new Wait(context))
  }

  override def endCombat(): Unit = {
    context.setState(new LandingPanel(context))
  }

  override def isCombatState: Boolean = true

}
