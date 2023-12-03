package cl.uchile.dcc.citric
package controller

import cl.uchile.dcc.citric.controller.states.{Chapter, GameOver}
import cl.uchile.dcc.citric.controller.states.player.Recovery
import cl.uchile.dcc.citric.exceptions.InvalidTransitionException
import cl.uchile.dcc.citric.model.norma.Norma6
import cl.uchile.dcc.citric.model.panels.{BonusPanel, DropPanel, EncounterPanel, HomePanel, NeutralPanel, Panel}
import cl.uchile.dcc.citric.model.units.PlayerCharacter

import org.junit.Assert.assertThrows

import scala.collection.mutable.ArrayBuffer

class GameControllerTest extends munit.FunSuite {

  private val testGame1 = new GameController
  private val testGame2 = new GameController
  private val testGame3 = new GameController

  private var testPlayer1: PlayerCharacter = new PlayerCharacter("testPlayer1", 10, 1, 1, 1, "stars")
  private var testPlayer2: PlayerCharacter = new PlayerCharacter("testPlayer2", 10, 1, 1, 1, "victories")
  private var testPlayer3: PlayerCharacter = new PlayerCharacter("testPlayer3", 10, 1, 1, 1, "stars")
  private var testPlayer4: PlayerCharacter = new PlayerCharacter("testPlayer4", 10, 1, 1, 1, "victories")
  private var players: ArrayBuffer[PlayerCharacter] = ArrayBuffer(testPlayer1,testPlayer2,testPlayer3,testPlayer4)

  private var neutralPanel: Panel = new HomePanel
  private var homePanel: Panel = new NeutralPanel
  private var bonusPanel: Panel = new BonusPanel
  private var dropPanel: Panel = new DropPanel
  private var encounterPanel: Panel = new EncounterPanel
  private var panels: ArrayBuffer[Panel] = ArrayBuffer(neutralPanel,homePanel,bonusPanel,dropPanel,encounterPanel)

  override def beforeEach(context: BeforeEach): Unit = {
    testGame1.createGame(players,panels)
    testGame2.createGame(players,panels)
  }

  test("A game should be able to be  and start on PreGame state") {
    testGame1.createGame(players,panels)
    assert(testGame1.isPreGameState)
  }

  test("The state should transition to PlayerTurn when all turns are set") {
    assert(testGame1.isPlayerTurnState)
  }

  test("If the current player is defeated it should transition to Recovery") {
    testGame1.getCurrentPlayer.setCurrentHp(0)
    testGame1.getState.isKO()
    assert(testGame1.getState.isRecoveryState)
  }

  test("If the player doesn't roll a sufficient roll it should go back to the next player's turn") {
    testGame1.inSufficientRoll()
    assert(testGame1.getState.isChapterState)
  }

  test("If the player rolls a sufficient roll, it should start the players turn") {
    val currentPlayerTurn: Int = testGame1.getCurrentPlayerTurn
    testGame1.setState(new Recovery(testGame1))
    testGame1.sufficientRoll()
    assertEquals(testGame1.getCurrentPlayerTurn,currentPlayerTurn)
    assert(testGame1.isPlayerTurnState)
  }

  test("If the player rolls a dice to move, the game should transition to the Moving state") {

  }

  test("If a player stops at a Home Panel, the game should transition to Player Attacking") {

  }

  test("If a player runs out of movements, the game should transition to Player Attacking") {

  }

  test("If the panel has another player, the game should transition to the other player's turn in combat") {

  }

  test("Combat should go correctly") {

  }

  test("If the panel is an encounter panel, the game should transition to combat against a Wild Unit") {

  }

  test("If the panel has no player, the panel should apply it's effect to the player and transition to the next Chapter") {

  }

  test("If a player reaches Norma 6, the game should transition to Game Over, ending the game") {
    testGame1.getCurrentPlayer.setNorma(new Norma6("stars",10))
    testGame1.setState(new Chapter(testGame1))
    testGame1.getState.setRequiredRecovery(testGame1.getState.getRequiredRecovery + 1)
    testGame1.checkNormaSix()
    assert(testGame1.getState.isGameOverState)
  }

  test("Testing of all incorrect state transitions") {
    assert(!testGame2.isChapterState)
    assert(!testGame2.isChapterState)
    assert(!testGame2.isGameOverState)
    assert(!testGame2.isRecoveryState)
    assert(!testGame2.isPlayerTurnState)
    assert(!testGame2.isMovingState)
    assert(!testGame2.isCombatState)
    assert(!testGame2.isWaitState)
    assert(!testGame2.isLandingPanelState)
    assertThrows(classOf[InvalidTransitionException], () => testGame2.reset())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.newChapter())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.normaSixReached())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.playTurn())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.inSufficientRoll())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.sufficientRoll())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.rollDice())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.choosePath())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.stopMovement())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.outOfMovements())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.attack())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.endCombat())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.defend())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.evade())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.doEffect())
    testGame2.setState(new GameOver(testGame2))
    assertThrows(classOf[InvalidTransitionException], () => testGame2.startGame())
    assert(!testGame2.isPreGameState)
  }

  test("If a game has not been created, an exception should be thrown when setting turns") {

  }

}
