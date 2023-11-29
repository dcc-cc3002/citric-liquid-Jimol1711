package cl.uchile.dcc.citric
package controller

import controller.states.{GameState, PreGame}

import cl.uchile.dcc.citric.controller.observer.NormaObserver
import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, Map}

class GameController extends NormaObserver(this) {

  var players: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]
  private var orderedPlayers: mutable.Map[Int,PlayerCharacter] = mutable.Map.empty

  private var state: GameState = new PreGame(this, players)

  def startGame(): Unit = {
    state.setTurns()
  }

  def setState(newState: GameState): Unit = state = newState

  def setPlayersWithOrder(playersMap: mutable.Map[Int, PlayerCharacter]): Unit = orderedPlayers = playersMap
}
