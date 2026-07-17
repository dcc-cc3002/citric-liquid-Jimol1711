package cl.uchile.dcc.citric
package model.norma

import model.units.PlayerCharacter

import cl.uchile.dcc.citric.exceptions.InvalidStatException

class Norma6(val statChosen: String,
             val statRequirement: Int) extends Norma {

  /** When a PlayerCharacter performs a NormaClear, the Norma level of the player is increased by one. */
  def normaClear(player: PlayerCharacter, nextChosenStat: String): Unit = {
    if (nextChosenStat == "stars") {
      player.setNorma(new Norma1(nextChosenStat, 10))
    } else if (nextChosenStat == "victories") {
      player.setNorma(new Norma1(nextChosenStat, 1))
    }
  }

}
