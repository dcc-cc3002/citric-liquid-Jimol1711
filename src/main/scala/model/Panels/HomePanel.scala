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
class HomePanel(private var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter],
                private var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]) extends AbstractPanel(characters, nextPanels) {

  /** Initially the Home Panel has no owner. It is set with an auxiliary constructor
   *
   */
  var owner: Option[PlayerCharacter] = None

  /** Auxiliary constructor to set an owner for the home panel
   *
   */
  def this(characters: ArrayBuffer[PlayerCharacter],
           nextPanels: ArrayBuffer[Panel],
           /** Sets the owner of this home panel using an Auxiliary constructor
            *
            * The owner is allowed to stop on the panel, no matter if it still has moves left or not.
            *
            */
           setOwner: PlayerCharacter) = {
    this(characters, nextPanels)
    owner = Some(setOwner)
  }

  // This variable is a placeholder that makes sure that the stop method works correctly since the implementation of user inputs can't be yet implemented
  var ans: Option[String] = None



  /** Asserts the player is the owner. If so, asks the player if it wants to stop on the Panel or keep going.
   *
   * If a player rests on the home panel, and it's currentHp is lower than it's maxHp, it's currentHp is increased by one point and a NormaCheck is done on the player.
   *
   * @param player the player that drops on this home panel, either the owner or another player who dropped exactly on it.
   */
  def apply(player: PlayerCharacter): Unit = {
    if(characters.contains(player)) {
      for (player <- characters) {
        if (owner.contains(player)) {
          println("Would you like to rest at home? Y/N")
          if (ans.contains("Y") && player.currentHp <= player.maxHp) {
            player.currentHp += 1
            player.getNorma.normaCheck(player)
          }
        } else if (!owner.contains(player) && player.currentHp <= player.maxHp) {
          player.currentHp += 1
          player.getNorma.normaCheck(player)
        }
      }
    }
  }

}
