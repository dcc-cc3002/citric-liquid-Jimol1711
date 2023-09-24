package cl.uchile.dcc.citric
package model.Unit

import model.Unit.Units

/** An abstract class representing a Unit from the game.
 *
 * It allows for the definition of methods whose implementation is shared amongst any Unit entity in the game.
 *
 * @param stars The number of stars of a Unit. Set to 0 by default.
 *
 */
abstract class AbstractUnit(var stars: Int = 0) extends Units {

  var currentHp: Int = maxHp

  def defeated(): Boolean = {
    currentHp <= 0
  }

  def fight(fighter1: Units, fighter2: Units): Unit = {
    println("Time to fight!")
  }
}
