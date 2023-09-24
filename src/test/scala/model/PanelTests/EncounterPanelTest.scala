package cl.uchile.dcc.citric
package model

import cl.uchile.dcc.citric.model.Panels.{EncounterPanel, Panel}
import cl.uchile.dcc.citric.model.Unit.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
import scala.math.floor
import scala.util.Random

class EncounterPanelTest extends munit.FunSuite {
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
  private var testPanel: EncounterPanel = _

  // Method that is executed before each test method
  override def beforeEach(context: BeforeEach): Unit = {
    characters = ArrayBuffer(testPlayer1)
    testPanel = new EncounterPanel(characters, nextPanels, row, col)
  }

  test("A panel should be able to receive new players") {
    testPanel.addCharacter(testPlayer1)
    assert(characters.contains(testPlayer1))
  }

  test("A panel should be able to remove players") {
    testPanel.removeCharacter(testPlayer1)
    assert(!characters.contains(testPlayer1))
  }

  test("A panel should be able to hold more than one player") {
    testPanel.addCharacter(testPlayer2)
    assert(characters.size >= 2)
  }

  test("A bellaco should be set when an Encounter Panel is created") {
    assert(testPanel.bellaco.contains(testPanel.chicken)
      || testPanel.bellaco.contains(testPanel.seagull)
      || testPanel.bellaco.contains(testPanel.roboball))
  }

  test("A bellaco should be able to be defeated on combat and dissapear from the panel") {
    testPanel.bellaco.foreach { bellaco =>
      bellaco.stars += 3
      bellaco.currentHp -= bellaco.maxHp
    }
    testPanel.enterCombat(testPlayer1)
    assert(testPanel.bellaco.isEmpty && testPlayer1.stars == 3)
  }

  test("A bellaco should be able to defeat a player and gain half of it's stars") {
    testPlayer1.stars += 3
    testPanel.bellaco.foreach { bellaco =>
      bellaco.currentHp += bellaco.maxHp
    }
    testPanel.enterCombat(testPlayer1)
    assert(testPanel.bellaco.nonEmpty)
    testPanel.bellaco.foreach { bellaco =>
      val bellacoStars = floor(testPlayer1.stars / 2).toInt
      assert(bellaco.stars == bellacoStars)
    }
  }
}
