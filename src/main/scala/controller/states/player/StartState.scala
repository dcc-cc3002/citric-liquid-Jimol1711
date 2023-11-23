package cl.uchile.dcc.citric
package controller.states.player

import controller.GameController
import controller.states.GameState

class StartState(controller: GameController) extends GameState {
  override def startGame(): Unit = {
    /* ... */
    controller.state = new /* ... */
  }

  override def rollDice(): Unit = {
    /* ... */
    controller.state = new /* ... */
  }

  override def doEffect(): Unit = {
    /* ... */
    controller.state = new /* ... */
  }
}
