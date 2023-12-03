package cl.uchile.dcc.citric
package controller.states.player

import controller.states.{AbstractState, Chapter}

import cl.uchile.dcc.citric.controller.GameController

class LandingPanel(context: GameController) extends AbstractState(context) {

  override def doEffect(): Unit = {
    context.setState(new Chapter(context))
  }

  override def isLandingPanelState: Boolean = true

}
