package cl.uchile.dcc.citric
package model.Panels

import cl.uchile.dcc.citric.model.Units.PlayerCharacter
import scala.collection.mutable.ArrayBuffer
import scala.math.min

/** Class representing a Bonus Panel
  *
  *  This panel grants stars to a PlayerCharacter when it drops on it. The PlayerCharacter has to drop a dice.
  *  Once done so, the PlayerCharacter obtains an amount of stars equivalent to min(roll * Norma, roll * 3), where
  *  roll is the number that the PlayerCharacter rolled on the dice and Norma is it's current Norma level.
  *
  *  @author [[https://github.com/Jimol1711/ Juan Molina L.]]
  */
class BonusPanel(var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter],
                 var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel],
                 var row: Int,
                 var col: Int) extends AbstractPanel {

  /** Adds the corresponding stars to a PlayerCharacter on the panel
   *
   * If a player drops on this panel, the addStars method is invoked on them
   *
   * @param player The PlayerCharacter to whom the function is adding the stars to.
   *
   */
  def addStars(player: PlayerCharacter): Unit = {
    if (characters.contains(player)) {
      val roll: Int = player.rollDice()
      player.stars += min(roll * player.Norma, roll * 3)
    }
  }

}
