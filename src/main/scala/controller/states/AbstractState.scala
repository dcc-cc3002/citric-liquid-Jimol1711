package cl.uchile.dcc.citric
package controller.states

import controller.GameController

import cl.uchile.dcc.citric.exceptions.InvalidTransitionException

abstract class AbstractState extends GameState {

  protected val context: GameController = new GameController

  def reset(): Unit = incorrectTransition("reset game")

  def setTurns(): Unit = incorrectTransition("set turns")

  def isKO(): Unit = incorrectTransition("check KO")

  def sufficientRoll(): Unit = incorrectTransition("check sufficient roll")

  def inSufficientRoll(): Unit = incorrectTransition("check insufficient roll")

  def nextPlayer(): Unit = incorrectTransition("transition to next player's turn")

  def normaSixReached(): Unit = incorrectTransition("check that Norma 6 was reached")

  def moveRoll(): Unit = incorrectTransition("roll a dice to move")

  def applyHP(): Unit = incorrectTransition("stop on Home Panel")

  def applyEffect(): Unit = incorrectTransition("apply current panel effect")

  def applyEP(): Unit = incorrectTransition("apply Encounter Panel effect")

  def applyAndHasPlayer(): Unit = incorrectTransition("apply current panel effect and fight player")

  def defendOrEvade(): Unit = incorrectTransition("defend or evade")

  def attackRoll(): Unit = incorrectTransition("roll a dice to get attack stat")

  /** Incorrect transition exception, for when an incorrect transition is attempted
   *
   * @param method the method or transition that is called and throws the exception
   */
  private def incorrectTransition(method: String): Unit = {
    throw new InvalidTransitionException(s"Can't $method in current state: ${getClass.getSimpleName}")
  }

}
