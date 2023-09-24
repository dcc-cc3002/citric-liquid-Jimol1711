package cl.uchile.dcc.citric
package model

import cl.uchile.dcc.citric.model.Panels.{HomePanel, Panel}
import cl.uchile.dcc.citric.model.Unit.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class HomePanelTest extends munit.FunSuite {
  /*
  This will be the initial constant values for each panel. Plus, there's an instantiation of test players to use on the panels tests.
  */
  var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]
  var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]
  var row: Int = 0
  var col: Int = 0
  var testPlayer1: PlayerCharacter = new PlayerCharacter("testPlayer", 10, 1, 1, 1, new Random(11))
  var testPlayer2: PlayerCharacter = new PlayerCharacter("testPlayer", 10, 1, 1, 1, new Random(11))

  /*
  This is the object under test, in this case, a panel. It's initialized before each test.
  */
  private var testPanel: HomePanel = _

  // Method that is executed before each test method
  override def beforeEach(context: BeforeEach): Unit = {
    characters = ArrayBuffer(testPlayer1)
    testPanel = new HomePanel(characters, nextPanels, row, col, testPlayer1)
  }

  test("A panel should be able to receive new players") {
    testPanel.addCharacter(testPlayer1)
    assert(characters.contains(testPlayer1))
  }

  test("A panel should be able to remove players") {
    testPanel.removeCharacter(testPlayer1)
    assert(!characters.contains(testPlayer1))
  }

  test("A panel should be able to receive more than one player") {
    testPanel.addCharacter(testPlayer2)
    assert(characters.size >= 2)
  }

  test("A home panel should be able to be set an Owner") {
    assert(testPanel.owner.contains(testPlayer1))
  }

  test("A home panel should be able to do a NormaCheck on a player character on it") {
    testPanel.NormaCheck(testPlayer1)
    testPlayer1.stars += 10
    testPanel.NormaCheck(testPlayer1)
    assert(testPlayer1.Norma > 1)
  }

  test("A player on a Home Panel should be able to rest on it") {
    testPanel.rest(testPlayer2)
    testPanel.ans = Some("Y")
    testPanel.rest(testPlayer1)
  }
}
