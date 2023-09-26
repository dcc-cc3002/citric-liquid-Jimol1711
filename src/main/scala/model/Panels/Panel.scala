package cl.uchile.dcc.citric
package model.Panels

import cl.uchile.dcc.citric.model.Units.PlayerCharacter
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

  /** Array of the characters currently positioned on this panel.
    *
    * In the game, multiple characters might be on the same panel at once, e.g., if multiple players
    * land on the same space.
    */
  var characters: ArrayBuffer[PlayerCharacter]

  /** An array of panels that are directly connected to this one.
   *
   * In the context of the game, multiple routes or paths may exist, this could represent the
   * possible next steps a player might take after being on this panel.
   *
   * @return a List of Panel instances that are adjacent or connected to this panel.
   */
  var nextPanels: ArrayBuffer[Panel]

  /** Reference to the panel to the left of the current panel */
  var left: Option[Panel]

  /** Reference to the panel to the right of the current panel */
  var right: Option[Panel]

  /** Reference to the panel over the current panel */
  var up: Option[Panel]

  /** Reference to the panel below the current panel */
  var down: Option[Panel]

  /** Reference to the row of the panel */
  var row: Int

  /** Reference to the column of the panel */
  var col: Int

  /** Adds a panel to the ArrayBuffer of Panels connected to the current one
   *
   *  The first connectTo method adds the Panel that is connected to a Panel to an ArrayBuffer
   *  that the Panel has. This ArrayBuffer is empty by default.
   *
   * @param panel the panel that's being added to the ArrayBuffer
   *
   */
  def connectTo(panel: Panel): Unit

  /** Connects a panel to the current one through coordinates.
   *
   * This method connects the Panel through coordinates and handles the case where the panel
   * cannot be connected (Because there's already a panel connected in the specified direction)
   * Calls connectTo to add the panel to the nextPanels ArrayBuffer.
   *
   * @param panel the panel that's being connected to the current one. It is added to the current
   *              one's nextPanels ArrayBuffer, unless the coordinates aren't adequate for connection.
   */
  def connectTo2(panel: Panel): Unit

  /** Disconnects a panel from another one by removing it from it's nextPanels ArrayBuffer
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
}
