package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.panels.{HomePanel, Panel}
import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

class HomePanelTest extends munit.FunSuite {
  /*
  This will be the initial constant values for each panel. Plus, there's an instantiation of test players to use on the panels tests.
  */
  private var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]
  private var panels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]
  private var panels2: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]
  private var testPlayer1: PlayerCharacter = new PlayerCharacter("testPlayer1", 10, 1, 1, 1, "stars")
  private var testPlayer2: PlayerCharacter = new PlayerCharacter("testPlayer2", 10, 1, 1, 1, "victories")

  /*
  This is the object under test, in this case, a panel. It's initialized before each test.
  */
  private var testPanel: HomePanel = _
  private var testPanel2: HomePanel = _

  // Method that is executed before each test method
  override def beforeEach(context: BeforeEach): Unit = {
    characters = ArrayBuffer(testPlayer1,testPlayer2)
    testPanel = new HomePanel(characters, panels, testPlayer1)
    testPanel2 = new HomePanel(characters, panels, testPlayer2)
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

  test("A panel should be able to connect and disconnect panels to itself") {
    val newPanel: Panel = new HomePanel(characters, panels2)
    testPanel.connectTo(newPanel)
    assert(panels.contains(newPanel))
    testPanel.disconnect(newPanel)
    assert(!panels.contains(newPanel))
  }

  test("A home panel should be able to be set an Owner") {
    assert(testPanel.owner==testPlayer1)
  }

  test("A home panel should perform a norma check for stars or victories on a player") {
    testPlayer1.setStars(5)
    testPanel.apply(testPlayer1)
    assert(testPlayer1.getNormaLevel == 1)
    testPlayer1.setStars(15)
    testPanel.apply(testPlayer1)
    assert(testPlayer1.getNormaLevel == 2)

    testPlayer2.setVictories(0)
    testPanel2.apply(testPlayer2)
    assert(testPlayer2.getNormaLevel == 1)
    testPlayer2.setVictories(1)
    testPanel2.apply(testPlayer2)
    assert(testPlayer2.getNormaLevel == 2)
  }

}
