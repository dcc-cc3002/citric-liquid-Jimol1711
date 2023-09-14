package cl.uchile.dcc.citric
package model

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class HomePanelTest extends munit.FunSuite {
  /*
  This will be the initial constant values for each panel. Plus, there's an instantiation of a testPlayer to use on the panels tests.
  */
  var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]
  var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]
  var testPlayer: PlayerCharacter = new PlayerCharacter("testPlayer",10,1,1,1,new Random(11))

  /*
  This is the object under test, in this case, a panel. It's reset before each test.
  */
  private var testPanel: HomePanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    testPanel = new HomePanel(characters, nextPanels,testPlayer)
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
