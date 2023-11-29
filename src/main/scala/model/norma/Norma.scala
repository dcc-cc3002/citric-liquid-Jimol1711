package cl.uchile.dcc.citric
package model.norma

import model.units.PlayerCharacter

/** A trait for the Norma of a player character
 *
 * The trait defines the methods related to the Norma, which are performed on PlayerCharacters so that they can increase their Norma.
 * The different level's of Norma extends from this trait.
 *
 */
trait Norma {

  /** The chosen stat to increase to the next Norma level.
   *
   * This stat is selected by the player character when increasing the Norma level. The player character chooses the stat for the next
   * norma level to increase.
   *
   */
  val statChosen: String

  /** The required number to overcome to increase to the next Norma level.
   *
   * It can be either stars or victories, which is determined when creating a new instance of Norma.
   *
   */
  val statRequirement: Int

  /** Increases a player's level if it meets the conditions, either stars or victories depending on the class of the Norma created
   *
   * @param player The player character whose Norma level is being increased
   */
  def normaClear(player: PlayerCharacter, chosenStat: String): Unit

}
