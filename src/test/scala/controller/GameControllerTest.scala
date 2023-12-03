package cl.uchile.dcc.citric
package controller

import cl.uchile.dcc.citric.controller.states.{Chapter, GameOver, PreGame}
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

  test("A game should be able to be  and start on PreGame state") {
    testGame1.createGame(players,panels)
    assert(testGame1.isPreGameState)
  }

  test("The state should transition to PlayerTurn when all turns are set") {
    testGame1.setTurns()
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

  test("If a player drops on a panel with combat involved, the game should transition to a combat related state") {
    assert(cond = true)
  }

  test("Once a combat ends or a Panel effect is applied, the game should transition to New Round") {
    assert(cond = true)
  }

  test("If a player is KO, it should transition to it's turn if it has a sufficient roll or to New Round if it doesn't") {
    assert(cond = true)
  }

  test("If a player reaches Norma 6, the game should transition to Game Over, ending the game") {
    testGame1.getCurrentPlayer.setNorma(new Norma6("stars",10))
    testGame1.setState(new Chapter(testGame1))
    testGame1.getState.setRequiredRecovery(testGame1.getState.getRequiredRecovery + 1)
    testGame1.checkNormaSix()
    assert(testGame1.getState.isGameOverState)
  }

  test("Testing of all incorrect state transitions") {
    assert(!testGame2.isPlayerTurnState)
    assert(!testGame2.isRecoveryState)
    assert(!testGame2.isChapterState)
    assert(!testGame2.isOnPanelState)
    assert(!testGame2.isMovingState)
    assert(!testGame2.isPlayerAttackingState)
    assert(!testGame2.isWildUnitAttackedState)
    assert(!testGame2.isPlayerAttackingState)
    assert(!testGame2.isPlayerAttackedState)
    assert(!testGame2.isGameOverState)
    assertThrows(classOf[InvalidTransitionException], () => testGame2.reset())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.isKO())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.sufficientRoll())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.inSufficientRoll())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.nextPlayer())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.inSufficientRoll())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.normaSixReached())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.moveRoll())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.stop())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.outOfMovements())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.applyEffect())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.applyEP())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.applyAndHasPlayer())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.hasPlayer())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.defendOrEvade())
    assertThrows(classOf[InvalidTransitionException], () => testGame2.attackRoll())
    testGame2.setState(new GameOver(testGame2))
    assertThrows(classOf[InvalidTransitionException], () => testGame2.setTurns())
    assert(!testGame2.isPreGameState)
  }

}
