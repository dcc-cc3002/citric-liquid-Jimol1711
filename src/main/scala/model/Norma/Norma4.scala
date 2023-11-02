package cl.uchile.dcc.citric
package model.Norma

import model.Units.PlayerCharacter

import cl.uchile.dcc.citric.exceptions.InvalidStatException

class Norma4(val player: PlayerCharacter, statChosen: Int) extends Norma {

  private var chosenStat: Option[Int] = None

  if (statChosen == player.getStars)
    chosenStat = Some(player.getStars)
  else if (statChosen == player.getVictories)
    chosenStat = Some(player.getVictories)
  else throw new InvalidStatException("The chosen stat has to be a valid number of stars or victories")

  /** NormaCheck checks if a player meets the conditions necessary to increase it's Norma Level.
   *
   * @param player The player to whom the Norma check is being done to, if it meets the conditions, the player performs a NormaClear
   */
  def normaCheck(player: PlayerCharacter): Unit = {

  }

  /** When a PlayerCharacter performs a NormaClear, the Norma level of the player is increased by one. */
  def normaClear(player: PlayerCharacter, nextChosenStat: Int): Unit = {
    player.setNorma(new Norma5(player, nextChosenStat))
  }

}
