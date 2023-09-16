package cl.uchile.dcc.citric
package model.Panels

import cl.uchile.dcc.citric.model.Unit.{PlayerCharacter, Units, WildUnit}

import scala.util.Random
import scala.collection.mutable.ArrayBuffer

/** Class representing an Encounter Panel
  *
  *  This panel holds a WildUnit. When a PlayerCharacter drops on it, it enters combat against said WildUnit.
  *
  *  @author [[https://github.com/Jimol1711/ Juan Molina L.]]
  */
class EncounterPanel(val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter],
                     var row: Int,
                     var col: Int) extends AbstractPanel {

  /** It sets a random WildUnit for the panel
   *
   */
  def encounterUnit(): Units = {

  }

  /** It starts a fight between a player and a bellaco
   *
   * It currently return a simple print since the combat can't yet be implemented
   *
   * @param player the player that drops on this panel
   * @param bellaco a WildUnit that is currently on this encounter Panel. It will be set randomly
   */
  def enterCombat(player: PlayerCharacter, bellaco: WildUnit): Unit = {
    println("Time to fight!")
  }

}
