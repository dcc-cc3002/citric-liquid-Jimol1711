package cl.uchile.dcc.citric
package controller.states

import controller.GameController

class GameOver extends AbstractState {

  override def reset(): Unit = {
    context.setState(new PreGame(new GameController))
  }

}
