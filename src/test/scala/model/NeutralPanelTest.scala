package cl.uchile.dcc.citric
package model

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class NeutralPanelTest extends munit.FunSuite {
  var characters: ArrayBuffer[PlayerCharacter] = _
  var nextPanels: ArrayBuffer[Panel] = _
  var testPlayer: PlayerCharacter = _

  private var testPanel: NeutralPanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    characters = ArrayBuffer.empty[PlayerCharacter]
    nextPanels = ArrayBuffer.empty[Panel]
    testPanel = new NeutralPanel(characters,nextPanels)
    testPlayer = new PlayerCharacter("testPlayer",10,1,1,1,new Random(11))
  }

  test("A panel should be able to receive new players") {
    testPanel.addCharacter(testPlayer)
    assert(characters.contains(testPlayer))
  }

  test("A panel should be able to remove players") {
    characters = ArrayBuffer(testPlayer)
    testPanel.removeCharacter(testPlayer)
    assert(!characters.contains(testPlayer))
  }
}
