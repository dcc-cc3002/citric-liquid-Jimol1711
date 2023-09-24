package cl.uchile.dcc.citric
package model.Unit

import model.Unit.Units

/** An abstract class representing a Unit from the game.
 *
 * It allows for the definition of methods whose implementation is shared amongst any Unit entity in the game.
 *
 */
abstract class AbstractUnit extends Units {

  var currentHp: Int = maxHp

  def defeated(): Boolean = {
    if (currentHp <= 0) true
    else false
  }

  def fight(): String = {
    return "Time to fight!"
  }
}
