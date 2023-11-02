package cl.uchile.dcc.citric
package model.Norma

import model.Units.PlayerCharacter

/** A trait for the Norma of a player character
 *
 * The trait defines the methods related to the Norma, which are performed on PlayerCharacters so that they can increase their Norma.
 * The different level's of Norma extends from this trait.
 *
 */
trait Norma {

  /** Checks if a player has the required stars or victories to do a Norma Clear
    *
    * If a player meets the number of stars or victories necessary it can perform a Norma Clear
    *
    * @param player The player character to whom the Norma is being performed to
    *
    */
  def normaCheck(player: PlayerCharacter): Unit

  /** Increases a player's level if it meets the conditions, either stars or victories depending on the class of the Norma created
   *
   * @param player The player character whose Norma level is being increased
   */
  def normaClear(player: PlayerCharacter): Unit

}
