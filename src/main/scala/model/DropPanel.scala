package cl.uchile.dcc.citric
package model

import scala.collection.mutable.ArrayBuffer
import scala.math.min

/** Class representing a Drop Panel
  *
  *  This panel removes stars from a PlayerCharacter when it drops on it. The PlayerCharacter has to drop a dice.
  *  The amount of stars lost by the PlayerCharacter will be roll * Norma, where roll is the number the PlayerCharacter
  *  rolled and Norma is it's current Norma level.
  *
  *  @author [[https://github.com/Jimol1711/ Juan Molina L.]]
  */
class DropPanel(val characters: ArrayBuffer[PlayerCharacter],
                var row: Int,
                var col: Int) extends AbstractPanel {

  /** Adds the corresponding stars to each PlayerCharacter on the panel
   *
   * @param player The PlayerCharacter to whom the function is adding the stars to.
   *
   */
  private def removeStars(player: PlayerCharacter): Unit = {
    val roll: Int = player.rollDice()
    player.stars += min(roll * player.Norma, roll * 3)
  }

  /** Removes stars from all characters on the panel by applying the removeStars function to each
   *
   */
  def removeAll(): Unit = {
    if (characters.nonEmpty) {
      characters.foreach(character => removeStars(character))
    }
  }
}