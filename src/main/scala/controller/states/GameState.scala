package cl.uchile.dcc.citric
package controller.states

trait GameState {

  /** Resets a game, setting it in the pre game state */
  def reset(): Unit

  /** Sets the turns players will play in based on their dice rolls and transitions to the first player's turn */
  def setTurns(): Unit

  /** Checks if a player is KO and if so, transitions to recovery phase for said player */
  def isKO(): Unit

  /** Set the state on player turn for the player on recovery that rolled a sufficient roll */
  def sufficientRoll(): Unit

  /** Transitions to the next chapter in case the player didn't roll a sufficient roll */
  def inSufficientRoll(): Unit

  /** Continues to the next player's turn */
  def nextPlayer(): Unit

  /** Informs that a Norma 6 was reached by a player therefore ending the game transitioning to game over state */
  def normaSixReached(): Unit

  /** A player moves a rolled amount of panels and sets the state to Panel */
  def moveRoll(): Unit

  /** The effect of the home panel is applied on a player and transitions to the next chapter */
  def applyHP(): Unit

  /** The effect of all other panels is applied unless it is an encounter panel and transitions to the next chapter */
  def applyEffect(): Unit

  /** The effect of the encounter panel is applied and the player fights a wild unit */
  def applyEP(): Unit

  /** Same as apply and applyEffect, but there's a player on the panel, therefore the player is asked if it wants to start combat */
  def applyAndHasPlayer(): Unit

  /** Same as apply and applyHP, but there's a player on the panel, therefore the player is asked if it wants to start combat */
  def applyHPAndHasPlayer(): Unit

  /** Asks a player if it wants to fight another player after fighting a wild unit assuming it is still alive */
  def hasPlayer(): Unit

  /** The unit whose fighting state the game is chooses beetwen defending or evading */
  def defendOrEvade(): Unit

  /** The unit whose fighting state the game is throws a dice, sets it's attack and attacks */
  def attackRoll(): Unit

}
