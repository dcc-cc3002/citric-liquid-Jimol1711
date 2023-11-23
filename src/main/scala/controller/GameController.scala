package cl.uchile.dcc.citric
package controller

import controller.states.GameState

class GameController {
  // Estado actual del juego
  var state: GameState = new StartState(this)

  def startGame(): Unit = {
    state.startGame()
    /* ... */
  }

  def rollDice(): Unit = {
    /* ... */
  }

  def doEffect(): Unit = {
    /* ... */
  }
}
