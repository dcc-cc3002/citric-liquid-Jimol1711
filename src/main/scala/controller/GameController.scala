package cl.uchile.dcc.citric
package controller

import controller.states.{GameState, PreGame}

import cl.uchile.dcc.citric.controller.observer.NormaObserver
import cl.uchile.dcc.citric.model.Units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

class GameController extends NormaObserver {

  var players: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]
  private var orderedPlayers: Map[Int,PlayerCharacter] = Map.empty

  private var state: GameState = new PreGame(this)

  def startGame(): Unit = {
    state.setTurns()
  }

  def setState(newState: GameState): Unit = state = newState

  def orderPlayers(): Unit = {
    var rolls: ArrayBuffer[Int] = ArrayBuffer.empty[Int]
    for (p <- players) {
      rolls += p.rollDice()
    }
  }

}
