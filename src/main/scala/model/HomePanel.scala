package cl.uchile.dcc.citric
package model

import scala.collection.mutable.ArrayBuffer
  /*
  The HomePanel Class receives a third parameter "owner". This is going the be the PlayerCharacter that "owns" this home panel.
   */
class HomePanel(val characters: ArrayBuffer[PlayerCharacter],
                var nextPanels: ArrayBuffer[Panel],val owner: PlayerCharacter) extends AbstractPanel {
  def activatePanel(owner: PlayerCharacter): Unit = {
    if characters.contains(owner):

  }
}
