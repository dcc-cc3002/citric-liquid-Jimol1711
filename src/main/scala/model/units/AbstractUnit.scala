package cl.uchile.dcc.citric
package model.units

import exceptions.InvalidStatException

import model.units.wildunits.WildUnit

import scala.math.max
import scala.util.Random

/** An abstract class representing a Unit from the game.
 *
 * It allows for the definition of methods whose implementation is shared amongst any Unit entity in the game.
 *
 * @param aMaxHp Maximum Health Points of a Unit.
 * @param aOffense Attack points of a Unit.
 * @param aDefense Defense points of a Unit.
 * @param aEvasion Evasion points of a Unit.
 *
 * @author [[https://github.com/Jimol1711/ Juan Molina L.]]
 *
 */
abstract class AbstractUnit(val aMaxHp: Int,
                            val aOffense: Int,
                            val aDefense: Int,
                            val aEvasion: Int,
                            val randomNumberGenerator: Random = new Random()) extends Unit with WildUnit {

  /** Current Health Points of a Unit.
   *
   * They begin being equal to maxHp but will vary if the Unit enters combat with another Unit.
   *
   */
  private var currentHp: Int = aMaxHp

  /** Number of stars a Unit has.
   *
   * Every Unit entity can have a number of Stars. They can be gained by several means, depending on the Unit.
   *
   */
  private var stars: Int = 0

  def rollDice(): Int = {
    randomNumberGenerator.nextInt(6) + 1
  }

  /** Method that determines if a Unit was defeated on combat.
   *
   * @return True if the Unit's currentHp reached 0 or below, False if not.
   */
  def defeated(): Boolean = {
    currentHp <= 0
  }

  /** Getter of a Unit's stars */
  def getStars: Int = {
    stars
  }

  /** Getter of a Unit's currentHp */
  def getCurrentHp: Int = {
    currentHp
  }

  /** Setter of a Unit's currentHp */
  def setCurrentHp(hp: Int): Unit = {
    if (hp > 0) currentHp = hp else currentHp = 0
  }

  /** Defend method for defending on combat */
  def defend(unit: Units, defendedAttack: Int): Unit = {
    val rollDefend: Int = rollDice()
    setCurrentHp(getCurrentHp - max(1, (defendedAttack-aOffense) + defendedAttack - (rollDefend + aDefense)))
  }

  /** Evade method for evading on combat */
  def evade(unit: Units, evadedAttack: Int): Unit = {
    val rollEvade: Int = rollDice()
    if (rollEvade + aEvasion > (evadedAttack-aOffense) + evadedAttack) {
      setCurrentHp(getCurrentHp)
    } else {
      setCurrentHp(getCurrentHp - evadedAttack)
    }
  }

  /** Setter of the Unit's stars */
  def setStars(newStars: Int): Unit = {
      stars = newStars
  }

  /** Exceptions method to validate the Units maxHp.
   *
   * Units can't have negative maxHp.
   *
   */
  def validateStat(value: Int, statName: String): Unit = {
    if (value < 0) {
      throw new InvalidStatException(s"Invalid $statName: $value. $statName must be a non-negative value.")
    }
  }

}
