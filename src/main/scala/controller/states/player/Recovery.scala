package cl.uchile.dcc.citric
package controller.states.player

import controller.states.{AbstractState, Chapter}

import cl.uchile.dcc.citric.controller.GameController

class Recovery(context: GameController) extends AbstractState(context) {

  override def inSufficientRoll(): Unit = {
    context.setState(new Chapter(context))
  }

  override def sufficientRoll(): Unit = {
    context.getCurrentPlayer.setStars(context.getCurrentPlayer.getStars + (math.floor(context.getCurrentChapter / 5) + 1).toInt)
    context.setState(new PlayerTurn(context))
  }

  override def isRecoveryState: Boolean = true

}
