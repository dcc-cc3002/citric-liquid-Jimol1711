package cl.uchile.dcc.citric
package model.norma

import model.units.PlayerCharacter

class Norma1(val statChosen: String,
             val statRequirement: Int) extends Norma {

  /** When a PlayerCharacter performs a NormaClear, the Norma level of the player is increased by one. */
  def normaClear(player: PlayerCharacter, nextChosenStat: String): Unit = {
    if (nextChosenStat == "stars") {
      player.setNorma(new Norma2(nextChosenStat,30))
    } else if (nextChosenStat == "victories") {
      player.setNorma(new Norma2(nextChosenStat,3))
    }
  }

}
