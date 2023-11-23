package cl.uchile.dcc.citric
package model.Panels

import cl.uchile.dcc.citric.model.Units.WildUnits.{Chicken, Roboball, Seagull, AbstractWildUnit}
import cl.uchile.dcc.citric.model.Units.{PlayerCharacter, Units}

import scala.util.Random
import scala.math.floor
import scala.collection.mutable.ArrayBuffer

/** Class representing an Encounter Panel
  *
  *  This panel holds a WildUnit. When a PlayerCharacter drops on it, it enters combat against said WildUnit.
  *
  *  @author [[https://github.com/Jimol1711/ Juan Molina L.]]
  */
class EncounterPanel(protected var characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter],
                     protected var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]) extends AbstractPanel(characters, nextPanels) {

  /** Setting of three instances of WildUnits.
   *
   */
  val chicken: AbstractWildUnit = new Chicken
  val roboball: AbstractWildUnit = new Roboball
  val seagull: AbstractWildUnit = new Seagull

  /** The bellaco will be the Wild Unit set for the particular panel that's created.
   *
   */
  private var bellaco: AbstractWildUnit = chicken

  /** Sets a random WildUnit for the panel.
   *
   */
  private def encounterUnit(): Unit = {
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

  /** Getter of the panel's bellaco */
  def getBellaco: AbstractWildUnit = {
    bellaco
  }

  /** The encounterUnit() method is called each time an EncounterPanel is created, so that a bellaco is set */
  encounterUnit()

  /** It starts a fight between a player and a bellaco.
   *
   * If the bellaco is defeated, the player obtains it's stars and the bellaco on the panel dissapears. Also, the player
   * gains 1 victory.
   *
   * @param player the player that drops on this panel
   * */
  def apply(player: PlayerCharacter): Unit = {
    if(characters.contains(player)){
      player.attack(bellaco)
    }
  }

}
