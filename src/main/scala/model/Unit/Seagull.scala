package cl.uchile.dcc.citric
package model.Unit

class Seagull extends WildUnit {

  /** The values for each WildUnit are set on it's particular class since they differ from one another
   *
   */
  var currentHp: Int = maxHp
  val attack: Int = + 1
  val defense: Int = - 1
  val evasion: Int = - 1

  /** We set the starting value of stars for a WildUnit
   *
   */
  var stars: Int = 0

}
