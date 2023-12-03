package cl.uchile.dcc.citric
package controller.states

import controller.GameController

class GameOver(context: GameController) extends AbstractState(context) {

  override def reset(): Unit = {
    val newContext = new GameController
    newContext.setState(new PreGame(newContext))
  }

  override def isGameOverState: Boolean = true

}
