package cl.uchile.dcc.citric
package model

import cl.uchile.dcc.citric.model.panels.{BonusPanel, DropPanel, EncounterPanel, HomePanel, NeutralPanel, Panel}
import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** Board of a game.
 *
 * a predetermined board is created for testing purposes. The board contains 4 home panels,
 * 6 neutral panels, 1 encounter panel, 1 bonus panel and 1 drop panel. Each player starts the
 * game on it's home panel. A visual representation of the board can be seen on the README file
 * of this project.
 *
 * @param players
 * @param panels
 */
class Board {

  /** Creates a board with the players given to the object */
  def createBoard(players: ArrayBuffer[PlayerCharacter]): Unit = {
    val hP1: Panel = new HomePanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel], players(0))
    val hP2: Panel = new HomePanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel], players(1))
    val hP3: Panel = new HomePanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel], players(2))
    val hP4: Panel = new HomePanel(ArrayBuffer.empty[PlayerCharacter], ArrayBuffer.empty[Panel], players(3))

    val n1: Panel = new NeutralPanel
    val n2: Panel = new NeutralPanel
    val n3: Panel = new NeutralPanel
    val n4: Panel = new NeutralPanel
    val n5: Panel = new NeutralPanel
    val n6: Panel = new NeutralPanel

    val bP: Panel = new BonusPanel
    val dP: Panel = new DropPanel
    val eP: Panel = new EncounterPanel

    hP1.connectTo(n1)
    hP1.connectTo(n3)

    hP2.connectTo(n2)
    hP2.connectTo(n4)

    hP3.connectTo(n3)
    hP3.connectTo(n5)

    hP4.connectTo(n4)
    hP4.connectTo(n6)

    n1.connectTo(hP1)
    n1.connectTo(bP)

    bP.connectTo(n1)
    bP.connectTo(n2)
    bP.connectTo(eP)

    n2.connectTo(bP)
    n2.connectTo(hP2)

    n3.connectTo(hP1)
    n3.connectTo(hP3)

    eP.connectTo(bP)
    eP.connectTo(dP)

    n4.connectTo(hP2)
    n4.connectTo(hP4)

    n5.connectTo(hP3)
    n5.connectTo(dP)

    dP.connectTo(n5)
    dP.connectTo(n6)
    dP.connectTo(eP)

    n6.connectTo(dP)
    n6.connectTo(hP4)

    hP1.addCharacter(players(0))
    hP2.addCharacter(players(1))
    hP3.addCharacter(players(2))
    hP4.addCharacter(players(3))
  }

}
