package cl.uchile.dcc.citric
package controller.states

import controller.GameController

class GameOver(context: GameController) extends AbstractState(context) {

  override def reset(): Unit = {
    context.setState(new PreGame(context))
  }

  override def isGameOverState: Boolean = true

}
