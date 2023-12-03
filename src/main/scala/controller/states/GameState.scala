package cl.uchile.dcc.citric
package controller.states

trait GameState {

  /** Resets a game, setting it in the pre game state */
  def reset(): Unit

  /** Sets the turns players will play in based on their dice rolls and transitions to the first player's turn */
  def startGame(): Unit

  /** The game transitions to a new Chapter */
  def newChapter(): Unit

  /** Informs that a Norma 6 was reached by a player therefore ending the game transitioning to game over state */
  def normaSixReached(): Unit

  /** Checks if a player is KO and if so, transitions to recovery phase for said player */
  def isKO(): Unit

  /** Continues to the next player's turn */
  def playTurn(): Unit

  /** Transitions to the next chapter in case the player didn't roll a sufficient roll */
  def inSufficientRoll(): Unit

  /** Set the state on player turn for the player on recovery that rolled a sufficient roll */
  def sufficientRoll(): Unit

  /** A player moves a rolled amount of panels and sets the state to Panel */
  def rollDice(): Unit

  /** A player has to choose a path in the case it has more than one panel to go to */
  def choosePath(): Unit

  /** If a player wants to stop on their Home Panel this method is called */
  def stopMovement(): Unit

  /** If a player that's moving runs out of movements this method is called */
  def outOfMovements(): Unit

  /** The effect of all panels is applied unless it is an encounter panel and transitions to the next chapter */
  def attack(): Unit

  /** The effect of the encounter panel is applied and the player fights a wild unit */
  def endCombat(): Unit

  /** Same as apply and applyEffect, but there's a player on the panel, therefore the player is asked if it wants to start combat */
  def defend(): Unit

  /** Asks a player if it wants to fight another player after fighting a wild unit assuming it is still alive */
  def evade(): Unit

  /** The effect of the panel is applied on the Player */
  def doEffect(): Unit

  /** Asks for states type by calling isPreGameState method of the state */
  def isPreGameState: Boolean

  /** Asks for states type by calling isChapterState method of the state */
  def isChapterState: Boolean

  /** Asks for states type by calling isGameOverState method of the state */
  def isGameOverState: Boolean

  /** Asks for states type by calling isRecoveryState method of the state */
  def isRecoveryState: Boolean

  /** Asks for states type by calling isPlayerTurn method of the state */
  def isPlayerTurnState: Boolean

  /** Asks for states type by calling isMovingState method of the state */
  def isMovingState: Boolean

  /** Asks for states type by calling isPlayerAttackingState method of the state */
  def isCombatState: Boolean

  /** Asks for states type by calling isPlayerAttackedState method of the state */
  def isWaitState: Boolean

  /** Asks for states type by calling isOnPanelState method of the state */
  def isLandingPanelState: Boolean

  /* Other stuff */
  /** Getter of the recovery amount required for a player to leave recovery State */
  def getRequiredRecovery: Int

  /** Setter of the recovery amount required for a player to */
  def setRequiredRecovery(x: Int): Unit
}
