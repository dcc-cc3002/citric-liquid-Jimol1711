package cl.uchile.dcc.citric
package model

trait Unit {
  val maxHp: Int
  var currentHp: Int
  val attack: Int
  val defense: Int
  val evasion: Int
  def defeated: Boolean
}
