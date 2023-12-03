package cl.uchile.dcc.citric
package controller

import controller.states.{GameState, PreGame}

import cl.uchile.dcc.citric.controller.observer.NormaObserver
import cl.uchile.dcc.citric.model.panels.Panel
import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class GameController extends NormaObserver {

  private var state: GameState = new PreGame(this)
  private var players: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]
  private var orderedPlayers: mutable.Map[Int, PlayerCharacter] = mutable.Map.empty
  private var currentPlayer: Int = 0
  private var board: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]

  def setState(newState: GameState): Unit = {
    state = newState
  }

  def getState: GameState = state

  def createGame(playersToAdd: ArrayBuffer[PlayerCharacter], panelsToAdd: ArrayBuffer[Panel]): Unit = {
    for (player <- playersToAdd) {
      players += player
    }
    for (panel <- panelsToAdd) {
      board += panel
    }
    state = new PreGame(this)
  }

  def setTurns(): Unit = {
    state.setTurns()
  }

  def getPlayers: ArrayBuffer[PlayerCharacter] = players.clone()

  def getBoard: ArrayBuffer[Panel] = board.clone()

  def getOrderedPlayers: mutable.Map[Int, PlayerCharacter] = orderedPlayers

  def setOrderedPlayers(playersMap: mutable.Map[Int, PlayerCharacter]): Unit = {
    orderedPlayers = playersMap
  }

  def currentPlayerTurn(): Unit = {
    currentPlayer += 1
    if (currentPlayer==4)
      currentPlayer=1
  }

  def setCurrentPlayer(p: Int): Unit = {
    currentPlayer = p
  }

  def getCurrentPlayer: PlayerCharacter = orderedPlayers(currentPlayer)

  def getCurrentPlayerTurn: Int = currentPlayer

  def dice(): Int = {
    getCurrentPlayer.rollDice()
  }

  def sufficientRoll(): Unit = state.sufficientRoll()

  def inSufficientRoll(): Unit = state.inSufficientRoll()

  def checkNormaSix(): Unit = {
    if (getCurrentPlayer.getNorma.isNormaSix) {
      state.normaSixReached()
    }
  }

  def update(player: PlayerCharacter, arg: Any): Unit = {
    if (getCurrentPlayer.getNorma.isNormaSix) {
      player.notifyObservers(player)
    }
  }

  def isPreGameState: Boolean = state.isPreGameState

  def isPlayerTurnState: Boolean = state.isPlayerTurnState

}
