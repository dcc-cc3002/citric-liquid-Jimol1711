package cl.uchile.dcc.citric
package controller.states

import cl.uchile.dcc.citric.controller.states.player.PlayerTurn

class PreGame extends AbstractState {

  override def setTurns(): Unit = {
    context.orderPlayers()
    context.setState(new PlayerTurn)
  }

}
