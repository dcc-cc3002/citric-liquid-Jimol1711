package cl.uchile.dcc.citric
package model.Units

import model.Units.Units

/** An abstract class representing a Unit from the game.
 *
 * It allows for the definition of methods whose implementation is shared amongst any Unit entity in the game.
 *
 * @author [[https://github.com/Jimol1711/ Juan Molina L.]]
 *
 */
abstract class AbstractUnit(private val maxHp: Int,
                            private val offense: Int,
                            private val defense: Int,
                            private val evasion: Int) extends Units {

  /** Current Health Points of a Unit.
   *
   * They begin being equal to maxHp but will vary if the Unit enters combat with another Unit.
   *
   */
  private var currentHp: Int = maxHp

  /** The number of stars of a Unit */
  private var stars: Int = 0

  /** Method that determines if a Unit was defeated on combat.
   *
   * @return True if the Unit's currentHp reached 0 or below, False if not.
   */
  def defeated(): Boolean = {
    currentHp <= 0
  }

  def getStars: Int = {
    stars
  }
  def getCurrentHp: Int = {
    currentHp
  }

  def getMaxHp: Int = {
    maxHp
  }

  def getAttack: Int = {
    offense
  }

  def getDefense: Int = {
    defense
  }

  def getEvasion: Int = {
    evasion
  }

  def defend(): Unit = {

  }

  def evade(): Unit = {

  }

  protected def setStars(addedStars: Int): Unit = {
    if (addedStars >= 0) {
      stars += addedStars
    }
  }
  protected def setHp(hp: Int): Unit = {
    currentHp += hp
    if (this.defeated()) {
      currentHp = 0
    }
  }
}
