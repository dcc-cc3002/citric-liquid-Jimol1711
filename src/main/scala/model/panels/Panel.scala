package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.units.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** Represents a single cell on a board, known as a Panel.
  *
  * Each panel has its own effect, which can be applied to a character.
  * In the context of the board game, a panel represents a tile or space that a character lands on
  * and experiences an effect (e.g., losing stars, fighting an enemy, etc.).
  * Panels can also be connected to other panels, allowing for the formation of complex board
  * structures. The connection of Panels is implemented through a coordinates system.
  *
  * @author [[https://github.com/r8vnhill Ignacio Slater M.]]
  * @author [[https://github.com/Jimol1711 Juan Molina L]]
  */
trait Panel {

  /** Adds a panel to the ArrayBuffer of Panels connected to the current one.
   *
   *  The first connectTo method adds the Panel that is connected to a Panel to an ArrayBuffer
   *  that the Panel has. This ArrayBuffer is empty by default.
   *
   * @param panel the panel that's being added to the ArrayBuffer
   *
   */
  def connectTo(panel: Panel): Unit

  /** Disconnects a panel from another one by removing it from it's nextPanels ArrayBuffer.
   *
   * @param panel the panel that's going to be disconnected.
   */
  def disconnect(panel: Panel): Unit

  /** Adds a character to the list of characters currently on this panel.
   *
   * This might be invoked when a player moves to this panel or starts their turn on it.
   *
   * @param player The player character to add to this panel.
   */
  def addCharacter(player: PlayerCharacter): Unit

  /** Removes a character from the list of characters currently on this panel.
    *
    * This might be invoked when a player moves off this panel.
    *
    * @param player The player character to remove from this panel.
    */
  def removeCharacter(player: PlayerCharacter): Unit

  /** Getter of the Panels next to the current One */
  def getPanels: ArrayBuffer[Panel]

  /** Definition of the apply method for the effects of each panel */
  def apply(player: PlayerCharacter): Unit

  /** Getter of a panels characters */
  def getCharacters: ArrayBuffer[PlayerCharacter]

  /** A method to check if a panel is a home panel and the currentPlayer is the owner, false by default */
  def isHomePanel(player: PlayerCharacter): Boolean
}
