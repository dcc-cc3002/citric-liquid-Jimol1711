package cl.uchile.dcc.citric
package model.Panels

import cl.uchile.dcc.citric.model.Unit.{Chicken, Seagull, Roboball, PlayerCharacter, Units, WildUnit}

import scala.util.Random
import scala.util.Random
import scala.collection.mutable.ArrayBuffer

/** Class representing an Encounter Panel
  *
  *  This panel holds a WildUnit. When a PlayerCharacter drops on it, it enters combat against said WildUnit.
  *
  *  @author [[https://github.com/Jimol1711/ Juan Molina L.]]
  */
class EncounterPanel(var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter],
                     var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel],
                     var row: Int,
                     var col: Int) extends AbstractPanel {

  /** Setting of three instances of WildUnits
   *
   */
  val chicken: WildUnit = new Chicken
  val roboball: WildUnit = new Roboball
  val seagull: WildUnit = new Seagull
  var bellaco: WildUnit = _

  /** It sets a random WildUnit for the panel
   *
   */
  def encounterUnit() = {
    val random = new Random()
    val randomNumber = random.nextInt(3)
    if (randomNumber == 1) {
      bellaco = chicken
    } else if (randomNumber == 2) {
      bellaco = seagull
    } else {
      bellaco = roboball
    }
  }

  /** It starts a fight between a player and a bellaco
   *
   * It currently return a print since the combat can't yet be implemented
   *
   * @param player the player that drops on this panel
   * @param bellaco a WildUnit that is currently on this encounter Panel. It will be set randomly
   */
  def enterCombat(player: PlayerCharacter, bellaco: WildUnit) = {
    println("Time to fight!")
  }

}
