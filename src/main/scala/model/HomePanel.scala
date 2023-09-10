package cl.uchile.dcc.citric
package model

import scala.collection.mutable.ArrayBuffer

class HomePanel(val characters: ArrayBuffer[PlayerCharacter],
                var nextPanels: ArrayBuffer[Panel]) extends AbstractPanel {

}
