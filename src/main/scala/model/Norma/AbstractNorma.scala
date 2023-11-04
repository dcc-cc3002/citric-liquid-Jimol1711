package cl.uchile.dcc.citric
package model.Norma

import model.Units.PlayerCharacter

class AbstractNorma(val statChosen: String, val statRequirement: Int) extends Norma {

  /** NormaCheck checks if a player meets the conditions necessary to increase it's Norma Level.
   *
   * @param player The player to whom the Norma check is being done to, if it meets the conditions, the player performs a NormaClear.
   * @param nextChosenStat The stat that the player chose to use for reaching when increasing to the next Norma level.
   */
  def normaCheck(player: PlayerCharacter, nextChosenStat: String): Unit = {
    if (statChosen == "stars") {
      if (player.getStars >= statRequirement) {
        normaClear(player, nextChosenStat)
      }
    } else if (statChosen == "victories") {
      if(player.getVictories>= statRequirement) {
        normaClear(player, nextChosenStat)
      }
    } else {}
  }

  def normaClear(player: PlayerCharacter, chosenStat: String): Unit = {}

}
