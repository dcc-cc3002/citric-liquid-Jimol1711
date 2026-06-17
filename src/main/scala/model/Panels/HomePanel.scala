package cl.uchile.dcc.citric
package model.Panels

import cl.uchile.dcc.citric.model.Units.PlayerCharacter
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
class HomePanel(var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter],
                var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel],
                var row: Int,
                var col: Int) extends AbstractPanel {

  /** Initially the Home Panel has no owner. It is set with an auxiliary constructor
   *
   */
  var owner: Option[PlayerCharacter] = None

  /** Auxiliary constructor to set an owner for the home panel
   *
   */
  def this(characters: ArrayBuffer[PlayerCharacter],
           nextPanels: ArrayBuffer[Panel],
           row: Int,
           col: Int,
           /** Sets the owner of this home panel using an Auxiliary constructor
            *
            * The owner is allowed to stop on the panel, no matter if it still has moves left or not.
            *
            */
           setOwner: PlayerCharacter) = {
    this(characters, nextPanels, row, col)
      owner = Some(setOwner)
  }

  // This variable is a placeholder that makes sure that the stop method works correctly since the implementation of user inputs can't be yet implemented
  var ans: Option[String] = None

  /** NormaCheck checks if a player meets the conditions necessary to increase it's Norma Level.
   *
   * @param player The player to whom the Norma check is being done to, if it meets the conditions, the player performs a NormaClear
   */
  def NormaCheck(player: PlayerCharacter): Unit = {
    if(characters.contains(player)) {
      if ((player.Norma == 1 && (player.stars >= 10 || player.victories == 1))
        || (player.Norma == 2 && (player.stars >= 30 || player.victories == 3))
        || (player.Norma == 3 && (player.stars >= 70 || player.victories == 6))
        || (player.Norma == 4 && (player.stars >= 120 || player.victories == 10))
        || (player.Norma == 5 && (player.stars >= 200 || player.victories == 14))) {
        player.NormaClear()
      } else {
        println("You don't meet the conditions to modify your Norma Level")
      }
    }
  }

  /** Asserts the player is the owner. If so, asks the player if it wants to stop on the Panel or keep going.
   *
   * If a player rests on the home panel, and it's currentHp is lower than it's maxHp, it's currentHp is increased by one point and a NormaCheck is done on the player.
   *
   * @param player the player that drops on this home panel, either the owner or another player who dropped exactly on it.
   */
  def rest(player: PlayerCharacter): Unit = {
    if(characters.contains(player)) {
      for (player <- characters) {
        if (owner.contains(player)) {
          println("Would you like to rest at home? Y/N")
          if (ans.contains("Y") && player.currentHp <= player.maxHp) {
            player.currentHp += 1
            NormaCheck(player)
          }
        } else if (!owner.contains(player) && player.currentHp <= player.maxHp) {
          player.currentHp += 1
          NormaCheck(player)
        }
      }
    }
  }

}
