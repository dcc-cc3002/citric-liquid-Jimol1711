package cl.uchile.dcc.citric
package model.Panels

import cl.uchile.dcc.citric.model.Units.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** Class representing a Drop Panel
  *
  *  This panel removes stars from a PlayerCharacter when it drops on it. The PlayerCharacter has to drop a dice.
  *  The amount of stars lost by the PlayerCharacter will be roll * Norma, where roll is the number the PlayerCharacter
  *  rolled and Norma is it's current Norma level.
  *
  *  @author [[https://github.com/Jimol1711/ Juan Molina L.]]
  */
class DropPanel extends AbstractPanel(characters = ArrayBuffer.empty[PlayerCharacter], nextPanels = ArrayBuffer.empty[Panel]) {

  /** Removes the corresponding stars from a PlayerCharacter on the panel
   *
   * If a player drops on this panel, the removeStars method is invoked on them
   *
   * @param player The PlayerCharacter to whom the function is removing the stars from.
   *
   */
  def removeStars(player: PlayerCharacter): Unit = {
    if (characters.contains(player)) {
      val roll: Int = player.rollDice()
      player.stars -= roll * player.Norma
    }
  }

}