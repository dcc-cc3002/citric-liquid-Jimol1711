package cl.uchile.dcc.citric
package model.units.wildunits

import model.units.{AbstractUnit, PlayerCharacter, Units}

import scala.util.Random

/** An abstract class representing an abstract WildUnit.
 *
 * It implements methods common to every WildUnit, in particular those regarding defending and evading.
 *
 */
abstract class AbstractWildUnit(val maxHp: Int,
                                val offense: Int,
                                val defense: Int,
                                val evasion: Int,
                                val bonusStars: Int) extends AbstractUnit(maxHp,offense,defense,evasion) {


  /** Attack method for Wild Units.
   *
   * Follows a double dispatch logic. In the case of Wild Units it's implemented so that the Wild Unit can attack a player, but when it
   * comes to attacking another Wild Unit, it does nothing since there is no such thing as combat between Wild Units.
   *
   * @param unit The unit that's being attacked by the Wild Unit.
   */
  def attack(unit: Units): Unit = {
    unit.attackWildUnit(this)
  }

  /** Attack player method for Wild Units.
   *
   * The method that defines attack from a WildUnit to a player, which only happens in the context of a combat initialized by a player.
   *
   * @param player The player that's being attacked by the Wild Unit.
   */
  def attackPlayer(player: PlayerCharacter): Unit = {
    val defendChosen = player.defense >= player.evasion
    val evadeChosen = player.defense < player.evasion
    if(defendChosen) {
      player.defend(this,offense)
    } else if(evadeChosen) {
      player.evade(this,offense)
    }
    if(player.getCurrentHp>0) {
      player.attack(this)
    } else if(player.getCurrentHp==0) {
      player.setVictories(player.getVictories + 1)
      player.setStars(player.getStars + getStars + bonusStars)
    }
  }

  var testingVariable: Boolean = false
  /** Attack wild unit method for Wild Units.
   *
   * No implementation, since wild units can't fight each other in the game.
   *
   * @param wildUnit The wild unit that's being attacked by the Wild Unit.
   */
  def attackWildUnit(wildUnit: AbstractWildUnit): Unit = {
    testingVariable = true
  }

}
