package cl.uchile.dcc.citric
package model

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
class HomePanel(val characters: ArrayBuffer[PlayerCharacter],
                var row: Int,
                var col: Int,
                /** Sets the owner of this home panel
                 *
                 *  The owner is allowed to stop on the panel, no matter if it still has moves left or not.
                 *
                 */
                var owner: PlayerCharacter) extends AbstractPanel {

  // This variable is a placeholder that makes sure that the stop method works correctly since the implementation of user inputs can't be yet implemented
  private var ans: String = _

  /** Asserts the player is the owner. If so, asks the player if it wants to stop on the Panel or keep going.
   *
   * @param player the player that drops on this home panel, either the owner or another player who dropped exactly on it.
   */
  def rest(player: PlayerCharacter): Unit = {
    for (player <- characters) {
      if (owner == player) {
        println("Would you like to rest at home? Y/N")
        if (ans == "Y" && player.currentHp <= player.maxHp) {
          player.currentHp += 1
          player.NormaCheck()
        }
      } else {
        player.currentHp += 1
        player.NormaCheck()
      }
    }
  }
}
