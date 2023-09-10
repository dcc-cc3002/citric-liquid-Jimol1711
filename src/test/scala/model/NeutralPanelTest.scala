package cl.uchile.dcc.citric
package model

import scala.collection.mutable.ArrayBuffer

class NeutralPanelTest extends munit.FunSuite {
  private var neutralPanel: NeutralPanel = _
  var playersArray: ArrayBuffer[PlayerCharacter] = _
  var nextPanelsArray: ArrayBuffer[Panel] = _

  override def beforeEach(context: BeforeEach): Unit = {
    playersArray = ArrayBuffer.empty[PlayerCharacter]
    nextPanelsArray = ArrayBuffer.empty[Panel]
    neutralPanel = new NeutralPanel(playersArray,nextPanelsArray)
  }


}
