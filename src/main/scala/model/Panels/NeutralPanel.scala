package cl.uchile.dcc.citric
package model.Panels

import cl.uchile.dcc.citric.model.Units.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** Class representing a Neutral Panel
  *
  * This panel has no effects, so there's nothing new to implement.
  *
  * @author [[https://github.com/Jimol1711/ Juan Molina L.]]
  */
class NeutralPanel(private var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter],
                   private var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel],
                   private var row: Int,
                   private var col: Int) extends AbstractPanel {

}