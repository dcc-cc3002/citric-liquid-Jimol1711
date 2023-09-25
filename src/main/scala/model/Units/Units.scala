package cl.uchile.dcc.citric
package model.Units

/** The 'Units' trait is a trait from which all 'Unit' entities in the game extend.
  *
  * It encapsulates attributes all units share such as HP, Attack, Defense, Evasion and Stars.
  *
  * @author [[https://github.com/Jimol1711 Juan Molina L]]
  */

trait Units {

  /** Maximum Health Points of a Unit.
   *
   * It may vary, depending on if the Unit is a PlayerCharacter or a WildUnit. It's a val since it can't be changed
   * throughout a game.
   *
   */
  val maxHp: Int

  /** Current Health Points of a Unit.
   *
   * They begin being equal to maxHp but will vary if the Unit enters combat with another Unit.
   *
   */
  var currentHp: Int

  /** Attack points of a Unit.
   *
   * It is used in the formula to determine how much damage a Unit will deal during combat. This will depend
   * heavily of context. It is a fixed value for each Unit.
   *
   */
  val attack: Int

  /** Defense points of a Unit.
   *
   * It's used in the formula to determine how much damage a Unit will receive during combat. This will depend
   * heavily of context. It is a fixed value for each Unit.
   *
   */
  val defense: Int

  /** Evasion points of a Unit.
   *
   * It's used in the formula to determine if a Unit will evade damage during combat. It is a fixed value for each Unit.
   *
   */
  val evasion: Int

  /** Number of stars a Unit has.
   *
   * Every Unit entity can have a number of Stars. They can be gained by several means, depending on the Unit.
   *
   */
  var stars: Int

  /** Method that determines if a Unit was defeated on combat.
   *
   * @return True if the Unit's currentHp reached 0 or below, False if not.
   */
  def defeated(): Boolean

  /** Method to define combat between Units.
   *
   * Currently empty since combat can't be yet implemented.
   *
   * @param fighter1 One of the Units that fights
   * @param fighter2 One of the Units that fights
   */
  def fight(fighter1: Units, fighter2: Units): Unit
}
