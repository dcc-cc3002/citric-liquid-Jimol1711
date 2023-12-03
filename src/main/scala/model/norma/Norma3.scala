package cl.uchile.dcc.citric
package model.norma

import model.units.PlayerCharacter

import cl.uchile.dcc.citric.exceptions.InvalidStatException

class Norma3(val statChosen: String,
             val statRequirement: Int) extends AbstractNorma {

  /** When a PlayerCharacter performs a NormaClear, the Norma level of the player is increased by one. */
  def normaClear(player: PlayerCharacter, nextChosenStat: String): Unit = {
    if (nextChosenStat == "stars") {
      player.setNorma(new Norma4(nextChosenStat, 120))
    } else if (nextChosenStat == "victories") {
      player.setNorma(new Norma4(nextChosenStat, 10))
    }
  }

}
