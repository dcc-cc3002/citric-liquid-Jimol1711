package cl.uchile.dcc.citric
package model.Unit

/** The 'Units' trait is a trait from which all 'Unit' entities in the game extend.
  *
  * It encapsulates attributes all units share such as HP, Attack, Defense, Evasion and Stars.
  *
  * @author [[https://github.com/Jimol1711 Juan Molina L]]
  */

trait Units {
  val maxHp: Int
  var currentHp: Int
  val attack: Int
  val defense: Int
  val evasion: Int
  var stars: Int
  def defeated(): Boolean
  def fight(): Unit
}
