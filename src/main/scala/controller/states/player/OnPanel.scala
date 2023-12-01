package cl.uchile.dcc.citric
package controller.states.player

import controller.states.{AbstractState, Chapter}

class OnPanel extends AbstractState {

  override def applyHP(): Unit = {

  }

  override def applyEffect(): Unit = {
    context.setState(new Chapter)
  }

  override def applyHPAndHasPlayer(): Unit = {

  }

  override def applyAndHasPlayer(): Unit = {

  }

  override def applyEP(): Unit = {

  }

}
