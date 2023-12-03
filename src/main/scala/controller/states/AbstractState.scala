package cl.uchile.dcc.citric
package controller.states

import controller.GameController

import cl.uchile.dcc.citric.exceptions.InvalidTransitionException

abstract class AbstractState(var context: GameController) extends GameState {

  def reset(): Unit = incorrectTransition("reset game")

  def startGame(): Unit = incorrectTransition("set turns")

  def newChapter(): Unit = incorrectTransition("advance to a new Chapter")

  def normaSixReached(): Unit = incorrectTransition("check that Norma 6 was reached")

  def isKO(): Unit = incorrectTransition("check KO")

  def playTurn(): Unit = incorrectTransition("transition to next player's turn")

  def inSufficientRoll(): Unit = incorrectTransition("check insufficient roll")

  def sufficientRoll(): Unit = incorrectTransition("check sufficient roll")

  def rollDice(): Unit = incorrectTransition("roll a dice")

  def choosePath(): Unit = incorrectTransition("choose a path")

  def stopMovement(): Unit = incorrectTransition("stop")

  def outOfMovements(): Unit = incorrectTransition("run out of movements")

  def attack(): Unit = incorrectTransition("attack")

  def endCombat(): Unit = incorrectTransition("end combat")

  def defend(): Unit = incorrectTransition("defend")

  def evade(): Unit = incorrectTransition("evade")

  def doEffect(): Unit = incorrectTransition("apply the panels effect")

  /** Incorrect transition exception, for when an incorrect transition is attempted
   *
   * @param action the method or transition that is called and throws the exception
   */
  private def incorrectTransition(action: String): Unit = {
    throw new InvalidTransitionException(s"Can't $action in current state: ${getClass.getSimpleName}")
  }

  def isPreGameState: Boolean = false

  def isChapterState: Boolean = false

  def isGameOverState: Boolean = false

  def isRecoveryState: Boolean = false

  def isPlayerTurnState: Boolean = false

  def isMovingState: Boolean = false

  def isCombatState: Boolean = false

  def isWaitState: Boolean = false

  def isLandingPanelState: Boolean = false

  /* Other stuff */
  protected var requiredRecovery: Int = 6

  def getRequiredRecovery: Int = requiredRecovery

  def setRequiredRecovery(x: Int): Unit = requiredRecovery = x
}
