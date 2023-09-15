package cl.uchile.dcc.citric
package model

import scala.collection.mutable.ArrayBuffer

/** Represents a single cell on a board, known as a Panel.
  *
  * Each panel has its own effect, which can be applied to a character.
  * In the context of the board game, a panel represents a tile or space that a character lands on
  * and experiences an effect (e.g., losing stars, fighting an enemy, etc.).
  * Panels can also be connected to other panels, allowing for the formation of complex board
  * structures.
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

  /** A few reference constants
   *
   *  This constants will be a reference to other panels next to the current one. The first four are directional references. The last two
   *  represent the row and column of the panel. This is useful for the implementation of a method that allow for the connection of Panels.
   *  We use Option to handle the case in which there are no neighboring panels.
   *
   */
  var left: Option[Panel]
  var right: Option[Panel]
  var up: Option[Panel]
  var down: Option[Panel]

  /** Constants referencing the panels row and column
   *
   *  These are given to each panel each time a new instance is created
   *
   */
  var row: Int
  var col: Int

  /** Connects a panel to the current Panel
   *
   *  When a Panel is connected to another Panel, it switches the reference in the specified direction from None to the Panel that was connected.
   *
   */
  def connectTo(panel: Panel): Unit

  def addCharacter(player: PlayerCharacter): Unit

  /** Removes a character from the list of characters currently on this panel.
    *
    * This might be invoked when a player moves off this panel.
    *
    * @param player The player character to remove from this panel.
    */
  def removeCharacter(player: PlayerCharacter): Unit
}
