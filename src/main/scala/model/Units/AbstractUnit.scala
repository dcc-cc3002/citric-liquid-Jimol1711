package cl.uchile.dcc.citric
package model.Units

import model.Units.Units

/** An abstract class representing a Unit from the game.
 *
 * It allows for the definition of methods whose implementation is shared amongst any Unit entity in the game.
 *
 * @param stars The number of stars of a Unit. Set to 0 by default.
 *
 * @author [[https://github.com/Jimol1711/ Juan Molina L.]]
 *
 */
abstract class AbstractUnit(var stars: Int = 0) extends Units {

  /** Current Health Points of a Unit.
   *
   * They begin being equal to maxHp but will vary if the Unit enters combat with another Unit.
   *
   */
  var currentHp: Int = maxHp

  /** Method that determines if a Unit was defeated on combat.
   *
   * @return True if the Unit's currentHp reached 0 or below, False if not.
   */
  def defeated(): Boolean = {
    currentHp <= 0
  }

  /** Method to define combat between Units.
   *
   * Currently empty since combat can't be yet implemented.
   *
   * @param fighter1 One of the Units that fights
   * @param fighter2 One of the Units that fights
   */
  def fight(fighter1: Units, fighter2: Units): Unit = {
    println("Time to fight!")
  }
}
