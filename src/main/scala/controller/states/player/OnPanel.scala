package cl.uchile.dcc.citric
package controller.states.player

import controller.states.{AbstractState, Chapter}

import cl.uchile.dcc.citric.controller.GameController

class OnPanel(context: GameController) extends AbstractState(context) {

  override def applyEffect(): Unit = {
    context.setState(new Chapter(context))
  }

  override def applyAndHasPlayer(): Unit = {

  }

  override def applyEP(): Unit = {

  }

  override def isOnPanelState: Boolean = true

}
