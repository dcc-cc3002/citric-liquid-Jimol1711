package cl.uchile.dcc.citric
package model.Units

import model.Units.Units

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
abstract class AbstractPlayer(protected val aMaxHp: Int,
                              protected val aOffense: Int,
                              protected val aDefense: Int,
                              protected val aEvasion: Int) extends Units {

  /** Current Health Points of a Unit.
   *
   * They begin being equal to maxHp but will vary if the Unit enters combat with another Unit.
   *
   */
  protected var currentHp: Int = aMaxHp

  /** Number of stars a Unit has.
   *
   * Every Unit entity can have a number of Stars. They can be gained by several means, depending on the Unit.
   *
   */
  protected var stars: Int = 0

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
    aMaxHp
  }

  def getOffense: Int = {
    aOffense
  }

  def getDefense: Int = {
    aDefense
  }

  def getEvasion: Int = {
    aEvasion
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
    currentHp = hp
  }

}
