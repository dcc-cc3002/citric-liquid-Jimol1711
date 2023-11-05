package cl.uchile.dcc.citric
package model.Norma

import model.Units.PlayerCharacter

import cl.uchile.dcc.citric.exceptions.InvalidStatException

class Norma5(val statChosen: String,
             val statRequirement: Int) extends Norma {

  /** When a PlayerCharacter performs a NormaClear, the Norma level of the player is increased by one. */
  def normaClear(player: PlayerCharacter, nextChosenStat: String): Unit = {
    if (nextChosenStat == "stars") {
      player.setNorma(new Norma6(nextChosenStat, 0))
    } else if (nextChosenStat == "victories") {
      player.setNorma(new Norma6(nextChosenStat, 0))
    }
  }

}
