package cl.uchile.dcc.citric
package controller.states

import controller.GameController

import cl.uchile.dcc.citric.exceptions.InvalidTransitionException

abstract class AbstractState(var context: GameController) extends GameState {

  protected var requiredRecovery: Int = 6

  def getRequiredRecovery: Int = requiredRecovery

  def setRequiredRecovery(x: Int): Unit = requiredRecovery = x

  def reset(): Unit = incorrectTransition("reset game")

  def setTurns(): Unit = incorrectTransition("set turns")

  def isKO(): Unit = incorrectTransition("check KO")

  def sufficientRoll(): Unit = incorrectTransition("check sufficient roll")

  def inSufficientRoll(): Unit = incorrectTransition("check insufficient roll")

  def nextPlayer(): Unit = incorrectTransition("transition to next player's turn")

  def normaSixReached(): Unit = incorrectTransition("check that Norma 6 was reached")

  def moveRoll(): Unit = incorrectTransition("roll a dice to move")

  def stop(): Unit = incorrectTransition("stop")

  def outOfMovements(): Unit = incorrectTransition("run out of movements")

  def applyEffect(): Unit = incorrectTransition("apply current panel effect")

  def applyEP(): Unit = incorrectTransition("apply Encounter Panel effect")

  def applyAndHasPlayer(): Unit = incorrectTransition("apply current panel effect and fight player")

  def hasPlayer(): Unit = incorrectTransition("fight another player")

  def defendOrEvade(): Unit = incorrectTransition("defend or evade")

  def attackRoll(): Unit = incorrectTransition("roll a dice to get attack stat")

  /** Incorrect transition exception, for when an incorrect transition is attempted
   *
   * @param method the method or transition that is called and throws the exception
   */
  private def incorrectTransition(method: String): Unit = {
    throw new InvalidTransitionException(s"Can't $method in current state: ${getClass.getSimpleName}")
  }

  def isPreGameState: Boolean = {
    println("It is not overriding")
    false
  }

  def isPlayerTurnState: Boolean = false

  def isRecoveryState: Boolean = false

  def isChapterState: Boolean = false

  def isOnPanelState: Boolean = false

  def isMovingState: Boolean = false

  def isPlayerAttackingState: Boolean = false

  def isPlayerAttackedState: Boolean = false

  def isWildUnitAttackedState: Boolean = false

  def isGameOverState: Boolean = false

}
