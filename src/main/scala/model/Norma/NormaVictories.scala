package cl.uchile.dcc.citric
package model.Norma

import model.Units.PlayerCharacter

class NormaVictories(val player: PlayerCharacter) extends Norma {

  /** NormaCheck checks if a player meets the conditions necessary to increase it's Norma Level.
   *
   * @param player The player to whom the Norma check is being done to, if it meets the conditions, the player performs a NormaClear
   */
  def normaCheck(player: PlayerCharacter): Unit = {
    if (player.getLevel == 1 && player.victories == 1
      || player.getLevel == 2 && player.victories == 3
      || player.getLevel == 3 && player.victories == 6
      || player.getLevel == 4 && player.victories == 10
      || player.getLevel == 5 && player.victories == 14) {
      normaClear(player)
    } else {
      println("You don't meet the conditions to modify your Norma Level")
    }
  }

  /** When a PlayerCharacter performs a NormaClear, the Norma level of the player is increased by one. */
  def normaClear(player: PlayerCharacter): Unit = {
    player.levelUp()
  }

}
