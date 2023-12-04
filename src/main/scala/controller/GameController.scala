package cl.uchile.dcc.citric
package controller

import controller.states.{Chapter, GameState, PreGame}

import cl.uchile.dcc.citric.controller.observer.NormaObserver
import cl.uchile.dcc.citric.exceptions.InvalidActionException
import cl.uchile.dcc.citric.model.panels.Panel
import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class GameController extends NormaObserver {

  /* Transitions and states */

  private var state: GameState = new PreGame(this)

  def setState(newState: GameState): Unit = {
    state = newState
  }

  def getState: GameState = state

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
  private val orderedPlayers: mutable.Map[Int, PlayerCharacter] = mutable.Map.empty
  private var currentTurn: Int = 0
  private var chapters: Int = 1
  private var requiredRecovery: Int = 6
  private val panels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]
  private val board: mutable.Map[Int, Panel] = mutable.Map.empty

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
  }

  def getPlayers: ArrayBuffer[PlayerCharacter] = players.clone()

  def getBoard: ArrayBuffer[Panel] = panels.clone()

  def getCurrentPlayer: PlayerCharacter = orderedPlayers(currentTurn)

  def getCurrentTurn: Int = currentTurn

  def getChapters: Int = chapters

  /** Orders the players randomly, based on a dice roll done by each player */
  def setOrderedPlayers(): Unit = {

    if (players.isEmpty || board.isEmpty) {
      throw new InvalidActionException("A game has not been created")
    }

    var turn = 0

    val playerRolls = getPlayers.map(player => (player, player.rollDice())).sortBy(_._2).reverse

    playerRolls.foreach { case (player, initialRoll) =>
      var roll = initialRoll

      while (orderedPlayers.exists { case (_, p) => p.rollDice() == roll }) {
        roll = player.rollDice()
      }

      orderedPlayers += (turn -> player)
      turn += 1
    }
  }

  /** Setter of the current chapter and player turns, so that the player's can be obtained out of the ordered players map */
  def setCurrentChapter(): Unit = {
    currentTurn += 1
    if (currentTurn >= 4) {
      currentTurn = 1
      chapters += 1
      if (requiredRecovery > 1) {
        requiredRecovery -= 1
      }
    }
  }

  /** Sets the board.
   *
   * 
   *
   */
  def setBoard(): Unit = {
    for (panel <- panels) {
      val key: Int = 0
      board += (key -> panel)
    }
    board.foreach {
      case (key, panel) =>
        val prevKey = (key - 2 + panels.length) % panels.length + 1
        val nextKey = (key % panels.length) + 1
        panel.connectTo(board(prevKey))
        panel.connectTo(board(nextKey))
    }
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
