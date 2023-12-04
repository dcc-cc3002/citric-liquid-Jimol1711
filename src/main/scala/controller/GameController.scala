package cl.uchile.dcc.citric
package controller

import controller.states.{Chapter, GameState, PreGame}

import cl.uchile.dcc.citric.controller.observer.NormaObserver
import cl.uchile.dcc.citric.exceptions.InvalidStatException
import cl.uchile.dcc.citric.model.Board
import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/** Game controller class to implement the controller of a game.
 *
 * Has methods to perform the transitions between the various states a game can be in and implement
 * a correct flux of a full game.
 *
 */
class GameController extends NormaObserver {

  /** State of a game. */
  private var state: GameState = new PreGame(this)

  /** Setter of a game's state. */
  def setState(newState: GameState): Unit = {
    state = newState
  }

  /** Getter of a game's state. */
  def getState: GameState = state

  /** Resets a game.
   *
   * @param newPlayers The players for the new game.
   *
   */
  def reset(newPlayers: ArrayBuffer[PlayerCharacter]): Unit = state.reset(newPlayers)

  /** Starts a game. */
  def startGame(): Unit = state.startGame()

  /** Begins a new chapter. */
  def newChapter(): Unit = state.newChapter()

  /** Informs the controller a norma six was reached therefore ending the game. */
  def normaSixReached(): Unit = state.normaSixReached()

  /** Informs the controller a player is KO, therefore entering recovery */
  def isKO(): Unit = state.isKO()

  /** Starts a player's turn. */
  def playTurn(): Unit = state.playTurn()

  /** A player didn't roll a sufficient roll to leave recovery, therefore the next player's turn in the chapter begins. */
  def inSufficientRoll(): Unit = state.inSufficientRoll()

  /** A player did roll a sufficient roll while on recovery therefore immediately starting it's turn. */
  def sufficientRoll(): Unit = state.sufficientRoll()

  /** A player rolls a dice to move. */
  def rollDice(): Unit = state.rollDice()

  /** A player chooses between more than one path options. */
  def choosePath(): Unit = state.choosePath()

  /** A player stops in case it passes by it's home panel. */
  def stopMovement(): Unit = state.stopMovement()

  /** A player runs out of movements and has to stop at the current panel. */
  def outOfMovements(): Unit = state.outOfMovements()

  /** A player on combat state attacks. */
  def attack(): Unit = state.attack()

  /** Ends combat. */
  def endCombat(): Unit = state.endCombat()

  /** An attacked unit defends. */
  def defend(): Unit = state.defend()

  /** An attacked unit evades. */
  def evade(): Unit = state.evade()

  /** The panel performs it's effect on the player. */
  def doEffect(): Unit = state.doEffect()

  /** Informs the controller if the player is on PreGame state. */
  def isPreGameState: Boolean = state.isPreGameState

  /** Informs the controller if the player is on Chapter state. */
  def isChapterState: Boolean = state.isChapterState

  /** Informs the controller if the player is on GameOver state. */
  def isGameOverState: Boolean = state.isGameOverState

  /** Informs the controller if the player is on Recovery state. */
  def isRecoveryState: Boolean = state.isRecoveryState

  /** Informs the controller if the player is on PlayerTurn state. */
  def isPlayerTurnState: Boolean = state.isPlayerTurnState

  /** Informs the controller if the player is on Moving state. */
  def isMovingState: Boolean = state.isMovingState

  /** Informs the controller if the player is on Combat state. */
  def isCombatState: Boolean = state.isCombatState

  /** Informs the controller if the player is on Wait state. */
  def isWaitState: Boolean = state.isWaitState

  /** Informs the controller if the player is on LandingPanel state. */
  def isLandingPanelState: Boolean = state.isLandingPanelState

  /* Observer elements */

  /** Update method for the observer, the condition for victory has been met. */
  def update(o: PlayerCharacter, arg: Any): Unit = {
    state = new Chapter(this)
    state.normaSixReached()
    println(s"Norma 6 reached, player ${arg} wins!" )
  }

  /** Player's on a game. */
  private val players: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]

  /** Getter of the player's on a game. */
  def getPlayers: ArrayBuffer[PlayerCharacter] = players.clone()

  /** Mapping of the player's after an order in which the turns are set is established. */

  private val orderedPlayers: mutable.Map[Int, PlayerCharacter] = mutable.Map.empty

  /** Getter of the ordered players. */
  def getOrderedPlayers: mutable.Map[Int, PlayerCharacter] = orderedPlayers.clone()

  /** Setter of the ordered players.
   *
   * @param turn The player's turn.
   *
   */
  def setOrderedPlayers(turn: Int, player: PlayerCharacter): Unit = orderedPlayers += (turn -> player)

  /** Getter of the current player. */
  def getCurrentPlayer: PlayerCharacter = orderedPlayers(currentTurn)

  /** The board of a game. */
  private val board: Board = new Board

  /** Getter of the game's board. */
  def getBoard: Board = board

  /** The player's turn the game is in. */

  private var currentTurn: Int = 0

  /** The getter of the current turn. */
  def getCurrentTurn: Int = currentTurn

  /** Setter of the current turn. It increases by 1 unless it's over four, in which case it goes back to 1.
   *
   * @throws InvalidStatException if turn is negative.
   *
   */
  def setCurrentTurn(turn: Int): Unit = {

    if (turn < 0) {
      throw new InvalidStatException("Turns can't be negative.")
    }

    currentTurn = turn
    if (currentTurn > 4) {
      currentTurn = 1
    }
  }

  /** Count of the chapters. */
  private var chapters: Int = 1

  /** Getter of the chapters count */
  def getCurrentChapter: Int = chapters

  /** Setter of the chapter's count.
   *
   * @param chapter the set chapter count.
   * @throws InvalidStatException if the chapter count is less than 1.
   *
   */
  def setCurrentChapter(chapter: Int): Unit = {

    if (chapter < 1) {
      throw new InvalidStatException("Chapter's can't be negative or zero.")
    }

    chapters = chapter
  }

  private var requiredRecovery: Int = 6
  def getRequiredRecovery: Int = requiredRecovery
  def setRequiredRecovery(recovery: Int): Unit = requiredRecovery = recovery

  /** The amount rolled by a player to move. */
  private var movingRoll: Int = 0

  /** Getter of the amount rolled by a player to move. */
  def getMovingRoll: Int = movingRoll

  /** Setter of the current player's moving roll to make it move through the board.
   *
   * @param newMovingRoll the moving roll set for the current player.
   * @throws InvalidStatException if a roll is not between 1 and 6.
   *
   */
  def setMovingRoll(newMovingRoll: Int): Unit = {

    if (newMovingRoll > 6 || newMovingRoll < 1) {
      throw new InvalidStatException("A roll has to be between 1 and 6.")
    }

    movingRoll = newMovingRoll
  }

  /** Creates a game with the players and panels and registers the controller as the observer of each player.
   *
   * @param playersToAdd The array of players of the PlayerCharacter class.
   *
   */
  def createGame(playersToAdd: ArrayBuffer[PlayerCharacter]): Unit = {
    for (player <- playersToAdd) {
      players += player
    }
    for (player <- players) {
      player.registerObserver(this)
    }
    board.createBoard(players)
  }

}
