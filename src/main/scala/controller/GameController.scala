package cl.uchile.dcc.citric
package controller

import controller.states.{GameState, PreGame}

import cl.uchile.dcc.citric.controller.observer.NormaObserver
import cl.uchile.dcc.citric.exceptions.InvalidActionException
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

  /** Creates a game with the players and panels.
   *
   * @param playersToAdd The array of players of the PlayerCharacter class.
   * @param panelsToAdd The array of panels that makes up the board.
   *
   */
  def createGame(playersToAdd: ArrayBuffer[PlayerCharacter], panelsToAdd: ArrayBuffer[Panel]): Unit = {
    for (player <- playersToAdd) {
      players += player
    }
    for (panel <- panelsToAdd) {
      board += panel
    }
  }

  /* Transitions */

  def reset(): Unit = state.reset()

  def startGame(): Unit = state.startGame()

  def newChapter(): Unit = state.newChapter()

  def normaSixReached(): Unit = state.normaSixReached()

  def isKO(): Unit = state.isKO()

  def playTurn(): Unit = state.playTurn()

  def inSufficientRoll(): Unit = state.inSufficientRoll()

  def sufficientRoll(): Unit = state.sufficientRoll()

  def rollDice(): Unit = state.rollDice()

  def choosePath(): Unit = state.choosePath()

  def stopMovement(): Unit = state.stopMovement()

  def outOfMovements(): Unit = state.outOfMovements()

  def attack(): Unit = state.attack()

  def endCombat(): Unit = state.endCombat()

  def defend(): Unit = state.defend()

  def evade(): Unit = state.evade()

  def doEffect(): Unit = state.doEffect()

  /* Other stuff */

  def getPlayers: ArrayBuffer[PlayerCharacter] = players.clone()

  def getBoard: ArrayBuffer[Panel] = board.clone()

  /** Orders the players randomly, based on a dice roll done by each player */
  def setOrderedPlayers(): Unit = {

    if (players.isEmpty && board.isEmpty) {
      throw new InvalidActionException("A game has not been created")
    }

    val playerMap = mutable.Map[Int, PlayerCharacter]()
    var turn = 0

    val playerRolls = getPlayers.map(player => (player, player.rollDice())).sortBy(_._2).reverse

    playerRolls.foreach { case (player, initialRoll) =>
      var roll = initialRoll

      while (playerMap.exists { case (_, p) => p.rollDice() == roll }) {
        roll = player.rollDice()
      }

      playerMap(turn) = player
      turn += 1
    }

    orderedPlayers = playerMap
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

  def update(player: PlayerCharacter): Unit = {

  }

  def isPreGameState: Boolean = state.isPreGameState

  def isChapterState: Boolean = state.isChapterState

  def isGameOverState: Boolean = state.isGameOverState

  def isRecoveryState: Boolean = state.isRecoveryState

  def isPlayerTurnState: Boolean = state.isPlayerTurnState

  def isMovingState: Boolean = state.isMovingState

  def isCombatState: Boolean = state.isCombatState

  def isWaitState: Boolean = state.isWaitState

  def isLandingPanelState: Boolean = state.isLandingPanelState

}
