package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.units.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** Class representing a Neutral Panel
  *
  * This panel has no effects, so there's nothing new to implement.
  *
  * @author [[https://github.com/Jimol1711/ Juan Molina L.]]
  */
class NeutralPanel(protected var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter],
                   protected var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]) extends AbstractPanel(characters, nextPanels) {

  /** On the Neutral panel the apply method is empty since it has no effects */
  def apply(player: PlayerCharacter): Unit = {}

}