package cl.uchile.dcc.citric
package model

/** An abstract class representing a Unit from the game
 *
 * It allows for the definition of methods whose implementation is shared among any Unit entity in the game
 *
 */

abstract class AbstractUnit extends Units {

  /** Checks if a Unit was defeated after combat
   *
   * @return true if the Unit was defeated, by checking if it's currentHp reached 0 or below. false if it's not the case.
   */
  def defeated: Boolean = {
    if (currentHp <= 0) true
    else false
  }

}
