package cl.uchile.dcc.citric
package model.Norma

import model.Units.PlayerCharacter

trait Norma {

  /** Checks if a player has the required stars or victories to do a Norma Clear
    *
    * If a player meets the number of stars or victories necessary it can perform a Norma Clear
    *
    */
  def normaCheck(player: PlayerCharacter): Unit

  def normaClear(player: PlayerCharacter): Unit

}
