package cl.uchile.dcc.citric
package model.Panels

import cl.uchile.dcc.citric.model.Unit.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** Class representing a Home Panel
  *
  *  This panel is assigned an owner at the beginning of the game. It can be activated by either of the following ways:
  *  If a PlayerCharacter owns the panel and goes through it, it can choose to either stay on it or keep advancing. If not,
  *  it will only be activated by dropping on it.
  *
  *  Whenever the panel is activated, the PlayerCharacter who does so will recover Hp by one point and the Panel will do a
  *  "Norma Check".
  *
  *  @author [[https://github.com/Jimol1711/ Juan Molina L.]]
  */
class HomePanel(val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter],
                var row: Int,
                var col: Int,
                /** Sets the owner of this home panel
                 *
                 *  The owner is allowed to stop on the panel, no matter if it still has moves left or not.
                 *
                 */
                var owner: PlayerCharacter) extends AbstractPanel {

  // This variable is a placeholder that makes sure that the stop method works correctly since the implementation of user inputs can't be yet implemented
  private var ans: Option[String] = None

  /** Increases the Norma level of a player by one.
   *
   * It can only be invoked through a Norma check, therefore is made private.
   *
   * @param player The player whose Norma level is being increased.
   */
  private def NormaClear(player: PlayerCharacter): Unit = {
    player.Norma += 1
  }

  /** NormaCheck checks if a player meets the conditions necessary to increase it's Norma Level.
   *
   * @param player The player to whom the Norma check is being done to
   */
  def NormaCheck(player: PlayerCharacter): Unit = {
    if ((player.Norma == 1 && (player.stars >= 10 || player.victories == 1))
    || (player.Norma == 2 && (player.stars >= 30 || player.victories == 3))
    || (player.Norma == 3 && (player.stars >= 70 || player.victories == 6))
    || (player.Norma == 4 && (player.stars >= 120 || player.victories == 10))
    || (player.Norma == 5 && (player.stars >= 200 || player.victories == 14))) {
      NormaClear(player)
    } else {
      println("You Won!")
    }
  }

  /** Asserts the player is the owner. If so, asks the player if it wants to stop on the Panel or keep going.
   *
   * If a player rests on the home panel, and it's currentHp is lower than it's maxHp, it's currentHp is increased by one point and a NormaCheck is done on the player.
   *
   * @param player the player that drops on this home panel, either the owner or another player who dropped exactly on it.
   */
  def rest(player: PlayerCharacter): Unit = {
    for (player <- characters) {
      if (owner == player) {
        println("Would you like to rest at home? Y/N")
        if (ans == "Y" && player.currentHp <= player.maxHp) {
          player.currentHp += 1
          NormaCheck(player)
        }
      } else {
        player.currentHp += 1
        NormaCheck(player)
      }
    }
  }
}
