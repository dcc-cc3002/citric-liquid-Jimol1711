package cl.uchile.dcc.citric
package controller

import controller.states.{GameState, PreGame}

class GameController {

  private var state: GameState = new PreGame(this)

  def startGame(): Unit = {
    state.startGame()
  }

  def dice(): Unit = {

  }

  def doEffect(): Unit = {

  }

  def setState(newState: GameState): Unit = state = newState
}
