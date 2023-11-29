package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.units.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** An abstract class representing an abstract panel
 *
 * In this abstract class we define the methods common to every panel. This is, the adding and removal of characters and the adding and
 * removal of panels connected to this panel. We can't set the array buffers of characters or panels here since they are different for
 * each panel. But the methods are common to all of them, so we define them here. The characters and panels arrays are included since
 * they are necessary for the implementation of the panel's methods
 *
 * @param aCharacters Array of characters currently on this panel
 * @param aNextPanels Array of panels connected to this panel
 *
 * @author [[https://github.com/Jimol1711/ Juan Molina L.]]
 *
 */
abstract class AbstractPanel(protected var aCharacters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter],
                             protected var aNextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]) extends Panel {

  /** Implementation of the method that adds panels to a panel's nextPanels ArrayBuffer.
   *
   * @param panel the panel that's being connected (added to the ArrayBuffer).
   *
   */
  def connectTo(panel: Panel):Unit = {
      aNextPanels += panel
  }

  /** Implementation of the method that removes a panel from a panel's nextPanels ArrayBuffer.
   *
   * @param panel the panel that's going to be disconnected.
   */
  def disconnect(panel: Panel): Unit = {
      aNextPanels -= panel
  }

  /** Adds a character to the list of characters currently on this panel.
   *
   * This might be invoked when a player moves to this panel or starts their turn on it.
   *
   * @param player The player character to add to this panel.
   */
  def addCharacter(player: PlayerCharacter): Unit = {
    aCharacters += player
  }

  /** Removes a character from the list of characters currently on this panel.
   *
   * This might be invoked when a player moves off this panel.
   *
   * @param player The player character to remove from this panel.
   */
  def removeCharacter(player: PlayerCharacter): Unit = {
    aCharacters -= player
  }

  /** Getter of the panel's currently connected Panels.
   *
   * It returns a copy of the nextPanels array buffer so that said panel can't be directly modified.
   *
   */
  def getPanels: ArrayBuffer[Panel] = {
    val pseudoNextPanels = aNextPanels
    pseudoNextPanels
  }

}
