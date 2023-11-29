package cl.uchile.dcc.citric
package controller.states

trait GameState {

  def reset(): Unit

  def setTurns(): Unit

  def isKO(): Unit

  def sufficientRoll(): Unit

  def inSufficientRoll(): Unit

  def nextPlayer(): Unit

  def normaSixReached(): Unit

  def moveRoll(): Unit

  def applyHP(): Unit

  def applyEffect(): Unit

  def applyEP(): Unit

  def applyAndHasPlayer(): Unit

  def defendOrEvade(): Unit

  def attackRoll(): Unit

}
