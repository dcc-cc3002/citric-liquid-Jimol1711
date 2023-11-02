package cl.uchile.dcc.citric
package model.Units.WildUnits

import model.Units.Units

class Chicken extends WildUnit {

  /** The values for each WildUnit are set on it's particular class since they differ from one another
   *
   */
  val maxHp: Int = 3
  val offense: Int = - 1
  val defense: Int = - 1
  val evasion: Int = + 1

}
