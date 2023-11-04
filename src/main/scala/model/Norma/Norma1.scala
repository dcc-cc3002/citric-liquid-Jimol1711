package cl.uchile.dcc.citric
package model.Norma

import model.Units.PlayerCharacter

import cl.uchile.dcc.citric.exceptions.InvalidStatException

class Norma1(override val statChosen: String,
             override val statRequirement: Int) extends AbstractNorma(statChosen,statRequirement) {

  /** When a PlayerCharacter performs a NormaClear, the Norma level of the player is increased by one. */
  override def normaClear(player: PlayerCharacter, nextChosenStat: String): Unit = {
    if (nextChosenStat == "stars") {
      player.setNorma(new Norma2(nextChosenStat,30))
    } else if (nextChosenStat == "victories") {
      player.setNorma(new Norma2(nextChosenStat,3))
    }
  }

}
