package cl.uchile.dcc.citric
package model

import cl.uchile.dcc.citric.model.Panels.{BonusPanel, Panel}
import cl.uchile.dcc.citric.model.Units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class BonusPanelTest extends munit.FunSuite {
  /*
  This will be the initial constant values for each panel. Plus, there's an instantiation of test players to use on the panels tests.
  */
  var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]
  var panels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]
  var panels2: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]
  var row: Int = 0
  var col: Int = 0
  var testPlayer1: PlayerCharacter = new PlayerCharacter("testPlayer", 10, 1, 1, 1, new Random(11))
  var testPlayer2: PlayerCharacter = new PlayerCharacter("testPlayer", 10, 1, 1, 1, new Random(11))

  /*
  This is the object under test, in this case, a panel. It's initialized before each test.
  */
  private var testPanel: BonusPanel = _

  // Method that is executed before each test method
  override def beforeEach(context: BeforeEach): Unit = {
    characters = ArrayBuffer(testPlayer1)
    testPanel = new BonusPanel(characters, panels, row, col)
  }

  test("A panel should be able to receive new players") {
    testPanel.addCharacter(testPlayer2)
    assert(characters.contains(testPlayer2))
  }

  test("A panel should be able to remove players") {
    testPanel.removeCharacter(testPlayer1)
    assert(!characters.contains(testPlayer1))
  }

  test("A panel should be able to hold more than one player") {
    testPanel.addCharacter(testPlayer2)
    assert(characters.size >= 2)
  }

  test("A panel should be able to connect and disconnect panels to itself") {
    val newPanel: Panel = new BonusPanel(characters, panels2, 0, 1)
    testPanel.connectTo(newPanel)
    assert(panels.contains(newPanel))
    testPanel.disconnect(newPanel)
    assert(!panels.contains(newPanel))
  }

  test("A panel should be able to connect Panels to itself through coordinates") {
    val panel1 = new BonusPanel(characters, panels2, row, col - 1)
    val panel2 = new BonusPanel(characters, panels2, row, col + 1)
    val panel3 = new BonusPanel(characters, panels2, row + 1, col)
    val panel4 = new BonusPanel(characters, panels2, row - 1, col)
    testPanel.connectTo2(panel1)
    testPanel.connectTo2(panel2)
    testPanel.connectTo2(panel3)
    testPanel.connectTo2(panel4)
    assert(testPanel.left.contains(panel1))
    assert(testPanel.right.contains(panel2))
    assert(testPanel.up.contains(panel3))
    assert(testPanel.down.contains(panel4))
    assert(panels.contains(panel1)
      && panels.contains(panel2)
      && panels.contains(panel3)
      && panels.contains(panel4))
  }

  test("A panel without adequate coordinates should not connect to the current panel") {
    val newPanel = new BonusPanel(characters, panels2, row + 2, col + 2)
    testPanel.connectTo2(newPanel)
    assert(!panels.contains(newPanel))
  }

  test("A bonus panel should be able to grant stars to a player") {
    val currentStars = testPlayer1.stars
    testPanel.addStars(testPlayer1)
    assert(testPlayer1.stars >= currentStars)
  }
}
