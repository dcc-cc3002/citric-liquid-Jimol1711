package cl.uchile.dcc.citric
package model

import scala.collection.mutable.ArrayBuffer

class HomePanelTest extends munit.FunSuite {
  private var homePanel: NeutralPanel = _
  var playersArray: ArrayBuffer[PlayerCharacter] = _
  var nextPanelsArray: ArrayBuffer[Panel] = _

  override def beforeEach(context: BeforeEach): Unit = {
    playersArray = ArrayBuffer.empty[PlayerCharacter]
    nextPanelsArray = ArrayBuffer.empty[Panel]
    homePanel = new NeutralPanel(playersArray, nextPanelsArray)
  }
}
