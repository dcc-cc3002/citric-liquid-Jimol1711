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

  /* Transitions */
  def reset(): Unit = state.reset()

  def setTurns(): Unit = state.setTurns()

  def isKO(): Unit = state.isKO()

  def sufficientRoll(): Unit = state.sufficientRoll()

  def inSufficientRoll(): Unit = state.inSufficientRoll()

  def nextPlayer(): Unit = state.nextPlayer()

  def normaSixReached(): Unit = state.normaSixReached()

  def moveRoll(): Unit = state.moveRoll()

  def stop(): Unit = state.stop()

  def outOfMovements(): Unit = state.outOfMovements()

  def applyEffect(): Unit = state.applyEffect()

  def applyEP(): Unit = state.applyEP()

  def applyAndHasPlayer(): Unit = state.applyAndHasPlayer()

  def hasPlayer(): Unit = state.hasPlayer()

  def defendOrEvade(): Unit = state.defendOrEvade()

  def attackRoll(): Unit = state.attackRoll()


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

  def checkNormaSix(): Unit = {
    if (getCurrentPlayer.getNorma.isNormaSix) {
      state.normaSixReached()
    }
  }

  def isPreGameState: Boolean = state.isPreGameState

  def isPlayerTurnState: Boolean = state.isPlayerTurnState

  def isRecoveryState: Boolean = state.isRecoveryState

  def isChapterState: Boolean = state.isChapterState

  def isOnPanelState: Boolean = state.isOnPanelState

  def isMovingState: Boolean = state.isMovingState

  def isPlayerAttackingState: Boolean = state.isPlayerAttackingState

  def isPlayerAttackedState: Boolean = state.isPlayerAttackedState

  def isWildUnitAttackedState: Boolean = state.isWildUnitAttackedState

  def isGameOverState: Boolean = state.isGameOverState

}
