package cl.uchile.dcc.citric
package model

import cl.uchile.dcc.citric.controller.GameController
import cl.uchile.dcc.citric.model.panels.{BonusPanel, DropPanel, EncounterPanel, HomePanel, NeutralPanel, Panel}
import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/** Board of a game.
 *
 * A predetermined board is created for testing purposes. The board contains 4 home panels,
 * 6 neutral panels, 1 encounter panel, 1 bonus panel and 1 drop panel. Each player starts the
 * game on it's home panel.
 *
 */
class Board(players: ArrayBuffer[PlayerCharacter]) {

  var hP1 = new HomePanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel], players(0))
  var hP2 = new HomePanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel], players(1))
  var hP3 = new HomePanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel], players(2))
  var hP4 = new HomePanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel], players(3))

  val n1: Panel = new NeutralPanel
  val n2: Panel = new NeutralPanel
  val n3: Panel = new NeutralPanel
  val n4: Panel = new NeutralPanel
  val n5: Panel = new NeutralPanel
  val n6: Panel = new NeutralPanel

  val bP: Panel = new BonusPanel
  val dP: Panel = new DropPanel
  val eP: Panel = new EncounterPanel

  /** Creates a board.
   *
   * @param players The players of a game.
   *
   */
  def createBoard(players: ArrayBuffer[PlayerCharacter]): Unit = {

    hP1.connectTo(n3)
    hP1.connectTo(n1)

    hP2.connectTo(n2)
    hP2.connectTo(n4)

    hP3.connectTo(n5)
    hP3.connectTo(n3)

    hP4.connectTo(n4)
    hP4.connectTo(n6)

    n1.connectTo(hP1)
    n1.connectTo(bP)

    bP.connectTo(n1)
    bP.connectTo(n2)
    bP.connectTo(eP)

    n2.connectTo(bP)
    n2.connectTo(hP2)

    n3.connectTo(hP3)
    n3.connectTo(hP1)

    eP.connectTo(bP)
    eP.connectTo(dP)

    n4.connectTo(hP2)
    n4.connectTo(hP4)

    n5.connectTo(dP)
    n5.connectTo(hP3)

    dP.connectTo(n6)
    dP.connectTo(n5)

    n6.connectTo(hP4)
    n6.connectTo(dP)

    hP1.addCharacter(players(0))
    hP2.addCharacter(players(1))
    hP3.addCharacter(players(2))
    hP4.addCharacter(players(3))

  }

  val panels: ArrayBuffer[Panel] = ArrayBuffer(hP1,hP2,hP3,hP4,n1,n2,n3,n4,n5,n6,bP,dP,eP)

}
