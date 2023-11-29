package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.units.PlayerCharacter
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
class HomePanel(protected var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter],
                protected var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]) extends AbstractPanel(characters, nextPanels) {

  /** Initially the Home Panel has no owner. It is set with an auxiliary constructor.
   *
   */
  var owner: Option[PlayerCharacter] = None

  /** Auxiliary constructor to set an owner for the home panel.
   *
   * The reason for using an auxiliary constructor is extensibility. It sounds reasonable that not every game has four players, but still
   * has four home panels (Or more). Therefore the creation of a home panel without owner must be allowed.
   *
   */
  def this(characters: ArrayBuffer[PlayerCharacter],
           nextPanels: ArrayBuffer[Panel],
           /** Sets the owner of this home panel using an Auxiliary constructor.
            *
            * The owner is allowed to stop on the panel, no matter if it still has moves left or not.
            *
            */
           setOwner: PlayerCharacter) = {
    this(characters, nextPanels)
    owner = Some(setOwner)
  }

  // This variable is a placeholder that makes sure that the stop method works correctly since the implementation of user inputs can't be yet implemented
  private var ans: String = "Y"

  /** NormaCheck checks if a player meets the conditions necessary to increase it's Norma Level.
   *
   * @param player The player to whom the Norma check is being done to, if it meets the conditions, the player performs a NormaClear
   */
  private def normaCheck(player: PlayerCharacter, nextChosenStat: String): Unit = {
    if (player.chosenStat == "stars") {
      if (player.getStars >= player.getNorma.statRequirement) {
        player.getNorma.normaClear(player, nextChosenStat)
      }
    } else if (player.chosenStat == "victories") {
      if (player.getVictories >= player.getNorma.statRequirement) {
        player.getNorma.normaClear(player, nextChosenStat)
      }
    } else {}
  }

  /** Asserts the player is the owner. If so, asks the player if it wants to stop on the Panel or keep going.
   *
   * If a player rests on the home panel, and it's currentHp is lower than it's maxHp, it's currentHp is increased by one point and a
   * NormaCheck is done on the player.
   *
   * @param player the player that drops on this home panel, either the owner or another player who dropped exactly on it.
   */
  def apply(player: PlayerCharacter): Unit = {
    if(characters.contains(player)) {
      for (player <- characters) {
        if (owner.contains(player)) {
          println("Would you like to rest at home? Y/N")
          if (ans=="Y") {
            player.setCurrentHp(player.getCurrentHp+1)
            normaCheck(player,player.chosenStat)
          }
        } else if (!owner.contains(player)) {
          player.setCurrentHp(player.getCurrentHp+1)
          normaCheck(player,player.chosenStat)
        }
      }
    }
  }

}
