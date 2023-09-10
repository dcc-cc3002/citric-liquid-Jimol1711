package cl.uchile.dcc.citric
package model

import scala.collection.mutable.ArrayBuffer
class PanelsTest extends munit.FunSuite {
  private var neutralPanel: NeutralPanel = _
  private var homePanel: HomePanel = _
  private var encounterPanel: EncounterPanel = _
  private var dropPanel: DropPanel = _
  private var bonusPanel: BonusPanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    var playersArray = ArrayBuffer.empty[PlayerCharacter]
    var nextPanelsArray = ArrayBuffer.empty[Panel]
    neutralPanel = new NeutralPanel(playersArray, nextPanelsArray)
    homePanel = new HomePanel(playersArray, nextPanelsArray)
    encounterPanel = new EncounterPanel(playersArray, nextPanelsArray)
    dropPanel = new DropPanel(playersArray, nextPanelsArray)
    bonusPanel = new BonusPanel(playersArray, nextPanelsArray)
  }

}
