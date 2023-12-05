package cl.uchile.dcc.citric
package controller

import cl.uchile.dcc.citric.controller.states.player.Moving
import cl.uchile.dcc.citric.controller.states.{Chapter, GameOver}
import cl.uchile.dcc.citric.exceptions.{InvalidActionException, InvalidStatException, InvalidTransitionException}
import cl.uchile.dcc.citric.model.units.PlayerCharacter
import org.junit.Assert
import org.junit.Assert.assertThrows

import scala.collection.mutable.ArrayBuffer

class GameControllerTest extends munit.FunSuite {

  private val testGame1 = new GameController
  private val testGame2 = new GameController
  private val testGame3 = new GameController
  private val testGame = new GameController

  private var testPlayer1: PlayerCharacter = new PlayerCharacter("testPlayer1", 10, 1, 1, 1, "stars")
  private var testPlayer2: PlayerCharacter = new PlayerCharacter("testPlayer2", 10, 1, 1, 1, "victories")
  private var testPlayer3: PlayerCharacter = new PlayerCharacter("testPlayer3", 10, 1, 1, 1, "stars")
  private var testPlayer4: PlayerCharacter = new PlayerCharacter("testPlayer4", 10, 1, 1, 1, "victories")
  private var players1: ArrayBuffer[PlayerCharacter] = ArrayBuffer(testPlayer1,testPlayer2,testPlayer3,testPlayer4)

  private var testPlayer5: PlayerCharacter = new PlayerCharacter("testPlayer5", 10, 1, 1, 1, "stars")
  private var testPlayer6: PlayerCharacter = new PlayerCharacter("testPlayer6", 10, 1, 1, 1, "victories")
  private var testPlayer7: PlayerCharacter = new PlayerCharacter("testPlayer7", 10, 1, 1, 1, "stars")
  private var testPlayer8: PlayerCharacter = new PlayerCharacter("testPlayer8", 10, 1, 1, 1, "victories")
  private var players2: ArrayBuffer[PlayerCharacter] = ArrayBuffer(testPlayer5,testPlayer6,testPlayer7,testPlayer8)

  private var testPlayer9: PlayerCharacter = new PlayerCharacter("testPlayer9", 10, 1, 1, 1, "stars")
  private var testPlayer10: PlayerCharacter = new PlayerCharacter("testPlayer10", 10, 1, 1, 1, "victories")
  private var testPlayer11: PlayerCharacter = new PlayerCharacter("testPlayer11", 10, 1, 1, 1, "stars")
  private var testPlayer12: PlayerCharacter = new PlayerCharacter("testPlayer12", 10, 1, 1, 1, "victories")
  private var players3: ArrayBuffer[PlayerCharacter] = ArrayBuffer(testPlayer9, testPlayer10, testPlayer11, testPlayer12)

  testGame1.createGame(players1)
  testGame2.createGame(players2)
  testGame.createGame(players3)

  test("A game should be able to be  and start on PreGame state") {
    assert(testGame1.isPreGameState)
  }

  test("The state should transition to the first chapter when all turns are set") {
    testGame1.startGame()
    assert(testGame1.isChapterState)
  }

  test("If the current player is defeated it should transition to Recovery") {
    testGame1.isKO()
    assert(testGame1.getState.isRecoveryState)
  }

  test("If the player doesn't roll a sufficient roll it should go back to the next player's turn") {
    testGame1.inSufficientRoll()
    assert(testGame1.getState.isChapterState)
  }

  test("Testing of the correct flux of a full game's transitions") {
    assert(testGame.isPreGameState)

    testGame.startGame()
    assert(testGame.isChapterState)

    testGame.isKO()
    assert(testGame.isRecoveryState)

    testGame.inSufficientRoll()
    assert(testGame.isChapterState)

    testGame.isKO()
    testGame.sufficientRoll()
    assert(testGame.isPlayerTurnState)

    testGame.setState(new Chapter(testGame))
    testGame.playTurn()
    assert(testGame.isPlayerTurnState)

    testGame.rollDice()
    assert(testGame.isMovingState)

    testGame.choosePath()
    assert(testGame.isMovingState)

    testGame.stopMovement()
    assert(testGame.isCombatState)

    testGame.setState(new Moving(testGame))
    testGame.outOfMovements()
    assert(testGame.isCombatState)

    testGame.attack()
    assert(testGame.isWaitState)

    testGame.defend()
    assert(testGame.isCombatState)

    testGame.attack()
    testGame.evade()
    assert(testGame.isCombatState)

    testGame.endCombat()
    assert(testGame.isLandingPanelState)

    testGame.doEffect()
    assert(testGame.isChapterState)

    testGame.newChapter()
    assert(testGame.isChapterState)

    testGame.normaSixReached()
    assert(testGame.isGameOverState)
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
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.reset(players1))
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.newChapter())
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.normaSixReached())
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.isKO())
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.playTurn())
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.inSufficientRoll())
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.sufficientRoll())
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.rollDice())
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.choosePath())
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.stopMovement())
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.outOfMovements())
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.attack())
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.endCombat())
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.defend())
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.evade())
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.doEffect())
    testGame2.setState(new GameOver(testGame2))
    Assert.assertThrows(classOf[InvalidTransitionException], () => testGame2.startGame())
    assert(!testGame2.isPreGameState)
  }

  test("Testing of all invalid stat exceptions") {
    Assert.assertThrows(classOf[InvalidStatException], () => testGame2.setCurrentTurn(-1))
    Assert.assertThrows(classOf[InvalidStatException], () => testGame2.setCurrentChapter(0))
    Assert.assertThrows(classOf[InvalidStatException], () => testGame2.setMovingRoll(0))
    Assert.assertThrows(classOf[InvalidActionException], () => testGame3.createGame(ArrayBuffer.empty[PlayerCharacter]))
    Assert.assertThrows(classOf[InvalidActionException], () => testGame3.startGame())
  }

  test("If the victory condition is met, a game should end") {
    testGame1.getCurrentPlayer.setStars(1000)
    testGame1.getCurrentPlayer.getNorma.normaClear(testGame1.getCurrentPlayer,"stars")
    testGame1.getCurrentPlayer.getNorma.normaClear(testGame1.getCurrentPlayer,"stars")
    testGame1.getCurrentPlayer.getNorma.normaClear(testGame1.getCurrentPlayer,"stars")
    testGame1.getCurrentPlayer.getNorma.normaClear(testGame1.getCurrentPlayer,"stars")
    testGame1.getCurrentPlayer.getNorma.normaClear(testGame1.getCurrentPlayer,"stars")
    assert(testGame1.isGameOverState)
  }

  test("A game should be able to be reset") {
    testGame.reset(players2)
  }

}
