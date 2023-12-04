package cl.uchile.dcc.citric
package controller

import controller.states.{Chapter, GameState, PreGame}

import cl.uchile.dcc.citric.controller.observer.NormaObserver
import cl.uchile.dcc.citric.exceptions.InvalidActionException
import cl.uchile.dcc.citric.model.Board
import cl.uchile.dcc.citric.model.panels.Panel
import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class GameController extends NormaObserver {

  /* Transitions and states */

  private var state: GameState = new PreGame(this)

  def setState(newState: GameState): Unit = {
    state = newState
  }

  def getState: GameState = state

  def reset(newPlayers: ArrayBuffer[PlayerCharacter], newPanels: ArrayBuffer[Panel]): Unit = state.reset(newPlayers,newPanels)

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


  def isPreGameState: Boolean = state.isPreGameState

  def isChapterState: Boolean = state.isChapterState

  def isGameOverState: Boolean = state.isGameOverState

  def isRecoveryState: Boolean = state.isRecoveryState

  def isPlayerTurnState: Boolean = state.isPlayerTurnState

  def isMovingState: Boolean = state.isMovingState

  def isCombatState: Boolean = state.isCombatState

  def isWaitState: Boolean = state.isWaitState

  def isLandingPanelState: Boolean = state.isLandingPanelState

  /* Observer elements */

  def update(o: PlayerCharacter, arg: Any): Unit = {
    state = new Chapter(this)
    state.normaSixReached()
    println(s"Norma 6 reached, player ${arg} wins!" )
  }

  /* Other stuff */

  private val players: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]
  def getPlayers: ArrayBuffer[PlayerCharacter] = players.clone()

  private val orderedPlayers: mutable.Map[Int, PlayerCharacter] = mutable.Map.empty
  def getOrderedPlayers: mutable.Map[Int, PlayerCharacter] = orderedPlayers.clone()

  def setOrderedPlayers(turn: Int, player: PlayerCharacter): Unit = orderedPlayers += (turn -> player)
  def getCurrentPlayer: PlayerCharacter = orderedPlayers(currentTurn)

  private val panels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]
  val board: Board = new Board

  private var currentTurn: Int = 0
  def getCurrentTurn: Int = currentTurn
  def setCurrentTurn(turn: Int): Unit = currentTurn = turn

  private var chapters: Int = 1
  def getCurrentChapter: Int = chapters
  def setCurrentChapter(chapter: Int): Unit = chapters = chapter

  private var requiredRecovery: Int = 6
  def getRequiredRecovery: Int = requiredRecovery
  def setRequiredRecovery(recovery: Int): Unit = requiredRecovery = recovery

  /** Creates a game with the players and panels and registers the controller as the observer of each player.
   *
   * @param playersToAdd The array of players of the PlayerCharacter class.
   * @param panelsToAdd  The array of panels that makes up the board.
   *
   */
  def createGame(playersToAdd: ArrayBuffer[PlayerCharacter], panelsToAdd: ArrayBuffer[Panel]): Unit = {
    for (player <- playersToAdd) {
      players += player
    }
    for (panel <- panelsToAdd) {
      panels += panel
    }
    for (player <- players) {
      player.registerObserver(this)
    }
    board.createBoard(players)
  }

  /** Method for when a player throws a dice */
  def dice(): Int = {
    getCurrentPlayer.rollDice()
  }

  /** Method for when a player gains it's turns stars */
  def playerTurnStars(): Unit = {
    getCurrentPlayer.setStars(getCurrentPlayer.getStars + (math.floor(chapters / 5) + 1).toInt)
  }

}
