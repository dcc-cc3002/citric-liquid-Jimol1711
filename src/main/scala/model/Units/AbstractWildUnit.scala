package cl.uchile.dcc.citric
package model.Units

/** Abstract WildUnit class created to implement the attributes that are shared amongst the different
 * types of WildUnits.
 *
 *
 * @author [[https://github.com/Jimol1711/ Juan Molina L.]]
 *
 */
abstract class AbstractWildUnit extends WildUnit {

  /** All Wild Units share a maxHp of 3 */
  val maxHp: Int = 3

}
