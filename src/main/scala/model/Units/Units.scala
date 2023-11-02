package cl.uchile.dcc.citric
package model.Units

/** The 'Units' trait is a trait from which all 'Unit' entities in the game extend.
  *
  * It encapsulates methods all units share such as fighting, defending, evading, etc.
  *
  * @author [[https://github.com/Jimol1711 Juan Molina L]]
  *
  */

trait Units {

  /** Method that determines if a Unit was defeated on combat.
   *
   * @return True if the Unit's currentHp reached 0 or below, False if not.
   */
  def defeated(): Boolean

  /** Method to define combat between Units.
   *
   * Currently empty since combat can't be yet implemented.
   *
   */
  def attack(): Unit

  def attackPlayer(): Unit

  def attackWildUnit(): Unit

  /** Method for defending, following the schema provided on EP4 */
  def defend(): Unit

  /** Method for evading, following the schema provided on EP4 */
  def evade(): Unit

}
