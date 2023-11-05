package cl.uchile.dcc.citric
package model.PanelTests

import cl.uchile.dcc.citric.model.Panels.{BonusPanel, Panel}
import cl.uchile.dcc.citric.model.Units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

class BonusPanelTest extends munit.FunSuite {
  /*
  This will be the initial constant values for each panel. Plus, there's an instantiation of test players to use on the panels tests.
  */
  private var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]
  private var panels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]
  private var panels2: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]
  private var testPlayer1: PlayerCharacter = new PlayerCharacter("testPlayer", 10, 1, 1, 1, "stars")
  private var testPlayer2: PlayerCharacter = new PlayerCharacter("testPlayer", 10, 1, 1, 1, "stars")

  /*
  This is the object under test, in this case, a panel. It's initialized before each test.
  */
  private var testPanel: BonusPanel = _

  // Method that is executed before each test method
  override def beforeEach(context: BeforeEach): Unit = {
    characters = ArrayBuffer(testPlayer1)
    testPanel = new BonusPanel(characters, panels)
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
    val newPanel: Panel = new BonusPanel(characters, panels2)
    testPanel.connectTo(newPanel)
    assert(panels.contains(newPanel))
    testPanel.disconnect(newPanel)
    assert(!panels.contains(newPanel))
  }

  test("A bonus panel should be able to grant stars to a player") {
    val currentStars = testPlayer1.getStars
    testPanel.apply(testPlayer1)
    assert(testPlayer1.getStars>currentStars)
  }

}
