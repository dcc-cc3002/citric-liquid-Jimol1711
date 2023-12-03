package cl.uchile.dcc.citric
package model.norma

import model.units.PlayerCharacter

import cl.uchile.dcc.citric.exceptions.InvalidStatException

class Norma4(val statChosen: String,
             val statRequirement: Int) extends AbstractNorma {

  /** When a PlayerCharacter performs a NormaClear, the Norma level of the player is increased by one. */
  def normaClear(player: PlayerCharacter, nextChosenStat: String): Unit = {
    if (nextChosenStat == "stars") {
      player.setNorma(new Norma5(nextChosenStat, 200))
    } else if (nextChosenStat == "victories") {
      player.setNorma(new Norma5(nextChosenStat, 14))
    }
  }

}
