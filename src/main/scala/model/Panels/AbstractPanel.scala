package cl.uchile.dcc.citric
package model.Panels

import cl.uchile.dcc.citric.model.Unit.PlayerCharacter

/** An abstract class representing an abstract panel
 *
 * In this abstract class we define the methods common to every panel. This is, the adding and removal of characters. We can't set the array buffers of characters
 * or panels here since they are different for each panel. But the methods are common to all of them, so we define them here.
 *
 * @author [[https://github.com/Jimol1711/ Juan Molina L.]]
 */
abstract class AbstractPanel extends Panel {

  /**
   * Setting of the directional reference constants
   */
  var left: Option[Panel] = None
  var right: Option[Panel] = None
  var up: Option[Panel] = None
  var down: Option[Panel] = None

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

  def connectTo(panel: Panel):Unit = {
    if(!this.nextPanels.contains(panel)) {
      nextPanels += panel
    }
  }

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

}
