package cl.uchile.dcc.citric
package model.Panels

import cl.uchile.dcc.citric.model.Units.{PlayerCharacter, Units}
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
class BonusPanel(protected var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter],
                 protected var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]) extends AbstractPanel(characters,nextPanels) {

  /** Implementation of the apply method for the effect of the bonus panel, replaces the addStars method defined previously.
   *
   * If a player drops on this panel, the apply method is invoked on them.
   *
   * @param player The PlayerCharacter to whom the function is adding the stars to.
   *
   */
  def apply(player: PlayerCharacter): Unit = {
    for (player <- characters) {
      val roll = player.rollDice()
      player.setStars(player.getStars + min(roll * player.getNormaLevel, roll * 3))
    }
  }
}
