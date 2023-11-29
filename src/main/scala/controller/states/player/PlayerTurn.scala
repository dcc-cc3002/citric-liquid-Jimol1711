package cl.uchile.dcc.citric
package controller.states.player

import controller.states.AbstractState

import cl.uchile.dcc.citric.controller.GameController
import cl.uchile.dcc.citric.model.units.PlayerCharacter

class PlayerTurn(context: GameController, player: PlayerCharacter) extends AbstractState {

  override def isKO(): Unit = {
    if(player.defeated()) {
      context.setState(new Recovery(context))
    }
  }

  override def moveRoll(): Unit = {
    /* movement */
    context.setState(new OnPanel)
  }

}
