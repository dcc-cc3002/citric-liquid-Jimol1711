package cl.uchile.dcc.citric
package model

import scala.collection.mutable.ArrayBuffer

class NeutralPanel(val characters: ArrayBuffer[PlayerCharacter],
                   var nextPanels: ArrayBuffer[Panel]) extends AbstractPanel {
  /*
  This panel has no effects. So there's nothing new to implement
    */
}