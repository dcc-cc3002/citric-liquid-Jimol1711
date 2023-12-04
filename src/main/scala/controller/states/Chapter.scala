package cl.uchile.dcc.citric
package controller.states

import controller.GameController

import cl.uchile.dcc.citric.controller.states.player.{PlayerTurn, Recovery}

class Chapter(context: GameController) extends AbstractState(context) {

  override def newChapter(): Unit = {
    context.setCurrentChapter(context.getCurrentChapter + 1)
    context.setState(new Chapter(context))
  }

  override def normaSixReached(): Unit = {
    context.setState(new GameOver(context))
  }

  override def isKO(): Unit = {
    context.setState(new Recovery(context))
  }

  override def playTurn(): Unit = {
    context.setCurrentTurn(context.getCurrentTurn + 1)
    context.getCurrentPlayer.setStars(context.getCurrentPlayer.getStars + (math.floor(context.getCurrentChapter / 5) + 1).toInt)
    context.setState(new PlayerTurn(context))
  }

  override def isChapterState: Boolean = true

}
