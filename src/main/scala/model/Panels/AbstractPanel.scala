package cl.uchile.dcc.citric
package model.Panels

import cl.uchile.dcc.citric.model.Units.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** An abstract class representing an abstract panel
 *
 * In this abstract class we define the methods common to every panel. This is, the adding and removal of characters. We can't set the array buffers of characters
 * or panels here since they are different for each panel. But the methods are common to all of them, so we define them here.
 *
 * @author [[https://github.com/Jimol1711/ Juan Molina L.]]
 */
abstract class AbstractPanel(private var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter],
                             private var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel],
                             private var row: Int,
                             private var col: Int) extends Panel {

  /**
   * Setting of the directional reference constants
   */
  private var left: Option[Panel] = None
  private var right: Option[Panel] = None
  private var up: Option[Panel] = None
  private var down: Option[Panel] = None

  /** Implementation of the method that connects Panels to each other
   *
   * @param panel corresponds to the panel that's going to be connected.
   */
  def connectTo2(panel: Panel): Unit = {
    if (panel.row == row && panel.col == col - 1) {
      left = Some(panel)
      panel.right = Some(this)
      this.connectTo(panel)
    } else if (panel.row == row && panel.col == col + 1) {
      right = Some(panel)
      panel.left = Some(this)
      this.connectTo(panel)
    } else if (panel.col == col && panel.row == row + 1) {
      up = Some(panel)
      panel.down = Some(this)
      this.connectTo(panel)
    } else if (panel.col == col && panel.row == row - 1) {
      down = Some(panel)
      panel.up = Some(this)
      this.connectTo(panel)
    } else {
      println("Panel can't be connected")
    }
  }

  /** Implementation of the method that adds panels to a panel's nextPanels ArrayBuffer
   *
   * @param panel the panel that's being added to the ArrayBuffer
   *
   */
  def connectTo(panel: Panel):Unit = {
    if(!this.nextPanels.contains(panel)) {
      nextPanels += panel
    }
  }

  /** Implementation of the method that removes a panel from a panel's nextPanels ArrayBuffer
   *
   * @param panel the panel that's going to be disconnected.
   */
  def disconnect(panel: Panel): Unit = {
    if(this.nextPanels.contains(panel)) {
      nextPanels -= panel
    }
  }

  /** Adds a character to the list of characters currently on this panel.
   *
   * This might be invoked when a player moves to this panel or starts their turn on it.
   *
   * @param player The player character to add to this panel.
   */
  def addCharacter(player: PlayerCharacter): Unit = {
    characters += player
  }

  /** Removes a character from the list of characters currently on this panel.
   *
   * This might be invoked when a player moves off this panel.
   *
   * @param player The player character to remove from this panel.
   */
  def removeCharacter(player: PlayerCharacter): Unit = {
    characters -= player
  }

  /** Getter of a PlayerCharacter on this Panel */
  def getPlayer(char: PlayerCharacter): Any = {
    if (this.characters.contains(char)) return char
    else println("The character is not on this Panel")
  }

  /** Getter of a panel's row */
  def getRow: Int = {
    row
  }

  /** Getter of a panel's column */
  def getCol: Int = {
    col
  }

  /** Getter of a panel's next Panel
   *
   * The main use of this getter is making sure the panel's array is not modifiable by a third party.
   */
  def getPanels: ArrayBuffer[Panel] = {
    val arrayOfPanels = ArrayBuffer.empty[Panel]
    for (panel <- nextPanels) {
      val panelType = panel.getClass.getSimpleName
      if (panel.getRow == row && panel.getCol == col - 1) {
        println(s"The left panel is a ${panelType}")
        arrayOfPanels += Some(left)
      }
      if (panel.getRow == row && panel.getCol == col + 1) {
        println(s"The right panel is a ${panelType}")
        arrayOfPanels += Some(right)
      }
      if (panel.getRow == row + 1 && panel.getCol == col) {
        println(s"The upper panel is a ${panelType}")
        arrayOfPanels += Some(up)
      }
      if (panel.getRow == row - 1 && panel.getCol == col) {
        println(s"The lower panel is a ${panelType}")
        arrayOfPanels += Some(down)
      }
    }
    arrayOfPanels
  }

}
