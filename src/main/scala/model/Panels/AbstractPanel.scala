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
abstract class AbstractPanel(protected var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter],
                             protected var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]) extends Panel {

  /** Implementation of the method that adds panels to a panel's nextPanels ArrayBuffer
   *
   * @param panel the panel that's being connected (added to the ArrayBuffer).
   *
   */
  def connectTo(panel: Panel):Unit = {
    if (this.nextPanels.size == 4) {
      println("This panel can't connect to more panels")
      // Here I should add an exception
    }
    if(!this.nextPanels.contains(panel) && this.nextPanels.size < 4) {
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
    } else {
      println(s"The panel ${panel} is not connected to this panel.")
      // Here I should add an exception
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

<<<<<<< HEAD
=======
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

>>>>>>> 51b570cbc2a127c83cdb674f64f9f8d182f52d5d
}
