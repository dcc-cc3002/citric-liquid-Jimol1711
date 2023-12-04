package cl.uchile.dcc.citric
package controller.states

import model.units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** Interface defining the methods of a game state.
 *
 * Each method is called so that a transition on the state occurs, depending on the state, certain methods can be called and others not (In which case and exception is thrown).
 *
 */
trait GameState {

  /** Resets a game, setting it in the pre game state with new players. */
  def reset(newPlayers: ArrayBuffer[PlayerCharacter]): Unit

  /** Starts a game by setting an order for player turns. */
  def startGame(): Unit

  /** The game transitions to a new chapter. */
  def newChapter(): Unit

  /** Informs that a Norma 6 was reached by a player therefore ending the game transitioning to game over state. */
  def normaSixReached(): Unit

  /** Checks if a player is KO and if so, transitions to recovery phase for said player. */
  def isKO(): Unit

  /** Continues to the next player's turn. */
  def playTurn(): Unit

  /** Transitions to the next chapter in case the player didn't roll a sufficient roll. */
  def inSufficientRoll(): Unit

  /** Set the state on player turn for the player on recovery that rolled a sufficient roll. */
  def sufficientRoll(): Unit

  /** A player moves a rolled amount of panels and sets the state to Panel. */
  def rollDice(): Unit

  /** A player has to choose a path in the case it has more than one panel to go to. */
  def choosePath(): Unit

  /** If a player wants to stop on their Home Panel this method is called. */
  def stopMovement(): Unit

  /** If a player that's moving runs out of movements this method is called. */
  def outOfMovements(): Unit

  /** A player attacks a unit while on combat state. */
  def attack(): Unit

  /** Ends combat. */
  def endCombat(): Unit

  /** An attacked unit defends. */
  def defend(): Unit

  /** An attacked unit evades. */
  def evade(): Unit

  /** Applies the effect of the panel on the player. */
  def doEffect(): Unit

  /** Asks for states type by calling isPreGameState method of the state. */
  def isPreGameState: Boolean

  /** Asks for states type by calling isChapterState method of the state. */
  def isChapterState: Boolean

  /** Asks for states type by calling isGameOverState method of the state. */
  def isGameOverState: Boolean

  /** Asks for states type by calling isRecoveryState method of the state. */
  def isRecoveryState: Boolean

  /** Asks for states type by calling isPlayerTurn method of the state. */
  def isPlayerTurnState: Boolean

  /** Asks for states type by calling isMovingState method of the state. */
  def isMovingState: Boolean

  /** Asks for states type by calling isPlayerAttackingState method of the state. */
  def isCombatState: Boolean

  /** Asks for states type by calling isPlayerAttackedState method of the state. */
  def isWaitState: Boolean

  /** Asks for states type by calling isOnPanelState method of the state. */
  def isLandingPanelState: Boolean

}
