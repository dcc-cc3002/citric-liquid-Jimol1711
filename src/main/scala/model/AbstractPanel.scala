package cl.uchile.dcc.citric
package model

import scala.collection.mutable.ArrayBuffer
abstract class AbstractPanel extends Panel {
  /*
  In this abstract class we define the methods common to every panel. This is, the adding and removal of characters. We can't set the array buffers of characters
  or panels here since they are different for each panel. But the methods are common to all of them, so we define them here.
   */
  def addCharacter(player: PlayerCharacter): Unit = characters += player
  def removeCharacter(player: PlayerCharacter): Unit = characters -= player
}
