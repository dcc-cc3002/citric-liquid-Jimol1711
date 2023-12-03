package cl.uchile.dcc.citric
package controller.states

import controller.GameController

import cl.uchile.dcc.citric.controller.observer.NormaObserver
import cl.uchile.dcc.citric.controller.states.player.PlayerTurn

class Chapter(context: GameController) extends AbstractState(context) {

  if (requiredRecovery > 0) requiredRecovery -= 1 else requiredRecovery = 0

  override def normaSixReached(): Unit = {
    context.setState(new GameOver(context))
  }

  override def nextPlayer(): Unit = {
    context.setState(new PlayerTurn(context))
  }

  override def isChapterState: Boolean = true

}
