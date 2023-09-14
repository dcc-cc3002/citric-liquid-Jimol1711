package cl.uchile.dcc.citric
package model

import scala.collection.mutable.ArrayBuffer

/** Class representing a Neutral Panel
  *
  * This panel has no effects, so there's nothing new to implement.
  *
  * @author [[https://github.com/Jimol1711/ Juan Molina L.]]
  */
class NeutralPanel(var characters: ArrayBuffer[PlayerCharacter],
                   var row: Int,
                   var col: Int) extends AbstractPanel {

  /*
  The neutral panel has no implementation since it has no effects
   */

}