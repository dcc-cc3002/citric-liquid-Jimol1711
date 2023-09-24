package cl.uchile.dcc.citric
package model.Unit

/** Abstract WildUnit class created to implement the attributes that are shared amongst the different
 * types of WildUnits.
 *
 */
abstract class AbstractWildUnit extends WildUnit {

  val maxHp: Int = 3
  var stars: Int = 0

}
