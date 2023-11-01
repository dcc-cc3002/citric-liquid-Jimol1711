package cl.uchile.dcc.citric
package model.Units.WildUnits

import model.Units.Units

/** Wild Unit is the "enemy" Unit in the game. They are found on encounter panels. A player character can fight them and if it loses
 *  the Wild Unit gets half of the players current stars.
 *
 *  @author [[https://github.com/Jimol1711/ Juan Molina L.]]
 *
 */
trait WildUnit extends Units {

  /** Setter of a wild unit's hp */
  def setWildUnitHp(wildUnit: WildUnit): Unit
}
