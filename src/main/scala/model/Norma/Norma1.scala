package cl.uchile.dcc.citric
package model.Norma

import model.Units.PlayerCharacter

import cl.uchile.dcc.citric.exceptions.InvalidStatException

class Norma1(val player: PlayerCharacter, val statChosen: Int) extends Norma {

  private var chosenStat: Option[Int] = None

  if(statChosen == player.getStars)
    chosenStat = Some(player.getStars)
  else if(statChosen == player.getVictories)
    chosenStat = Some(player.getVictories)
  else throw new InvalidStatException("The chosen stat has to be a valid number of stars or victories")

  /** NormaCheck checks if a player meets the conditions necessary to increase it's Norma Level.
   *
   * @param player The player to whom the Norma check is being done to, if it meets the conditions, the player performs a NormaClear
   */
  def normaCheck(player: PlayerCharacter): Unit = {
      if (player.getLevel == 1 && player.getStars >= 10
        || player.getLevel == 2 && player.getStars >= 30
        || player.getLevel == 3 && player.getStars >= 70
        || player.getLevel == 4 && player.getStars >= 120
        || player.getLevel == 5 && player.getStars >= 200) {
        normaClear(player)
      } else {
        println("You don't meet the conditions to modify your Norma Level")
      }
  }

  /** When a PlayerCharacter performs a NormaClear, the Norma level of the player is increased by one. */
  def normaClear(player:PlayerCharacter): Unit = {
    player.levelUp()
  }

}
