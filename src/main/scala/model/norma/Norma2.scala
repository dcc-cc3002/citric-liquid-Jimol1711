package cl.uchile.dcc.citric
package model.norma

import model.units.PlayerCharacter

import cl.uchile.dcc.citric.exceptions.InvalidStatException

class Norma2(val statChosen: String,
             val statRequirement: Int) extends Norma {

  /** When a PlayerCharacter performs a NormaClear, the Norma level of the player is increased by one. */
  def normaClear(player: PlayerCharacter, nextChosenStat: String): Unit = {
    if (nextChosenStat == "stars") {
      player.setNorma(new Norma3(nextChosenStat, 70))
    } else if (nextChosenStat == "victories") {
      player.setNorma(new Norma3(nextChosenStat, 6))
    }
  }

}
