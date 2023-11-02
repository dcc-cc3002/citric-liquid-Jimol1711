package cl.uchile.dcc.citric
package model.Panels

import cl.uchile.dcc.citric.model.Units.WildUnits.{Chicken, Roboball, Seagull, WildUnit}
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

  /** Setting of three instances of WildUnits
   *
   */
  val chicken: WildUnit = new Chicken
  val roboball: WildUnit = new Roboball
  val seagull: WildUnit = new Seagull

  /** The bellaco will be the Wild Unit set for the particular panel that's created
   *
   */
  var bellaco: Option[WildUnit] = None

  /** Sets a random WildUnit for the panel
   *
   */
  private def encounterUnit(): Unit = {
    val random = new Random()
    val randomNumber = random.nextInt(3)
    if (randomNumber == 1) {
      bellaco = Some(chicken)
    } else if (randomNumber == 2) {
      bellaco = Some(seagull)
    } else {
      bellaco = Some(roboball)
    }
  }

  /** The encounterUnit() method is called each time an EncounterPanel is created, so that a bellaco is set */
  encounterUnit()

  /** It starts a fight between a player and a bellaco
   *
   * If the bellaco is defeated, the player obtains it's stars and the bellaco on the panel dissapears. Also, the player
   * gains 1 victory.
   *
   * @param player the player that drops on this panel
   * */
  def apply(player: PlayerCharacter): Unit = {
    bellaco match {
      case Some(b) =>

        if (b.defeated()) {
          // player.setStars(player.getStars+b.getStars)
          bellaco = None
          player.setVictories(player.getVictories+1)
        } else {
          // val starsGained = floor(player.stars / 2).toInt
          // b.stars += starsGained
          // player.stars -= starsGained
        }
    }
  }

}
