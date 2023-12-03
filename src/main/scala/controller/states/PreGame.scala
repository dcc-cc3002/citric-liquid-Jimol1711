package cl.uchile.dcc.citric
package controller.states

import cl.uchile.dcc.citric.controller.GameController
import cl.uchile.dcc.citric.controller.states.player.PlayerTurn

class PreGame(context: GameController) extends AbstractState(context) {

  override def startGame(): Unit = {
    context.setOrderedPlayers()
    context.setState(new PlayerTurn(context))
  }

  override def isPreGameState: Boolean = true

}
