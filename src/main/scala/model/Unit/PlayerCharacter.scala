package cl.uchile.dcc.citric
package model.Unit

import scala.util.Random
import scala.math.floor

/** The `PlayerCharacter` class represents a character or avatar in the game, encapsulating
  * several attributes such as health points, attack strength, defense capability,
  * and evasion skills. Each player has a unique name, and throughout the game,
  * players can collect stars, roll dice, and progress in levels known as 'norma'.
  * This class not only maintains the state of a player but also provides methods
  * to modify and interact with these attributes.
  *
  * For instance, players can:
 *
  * - Increase or decrease their star count.
 *
  * - Roll a dice, a common action in many board games.
 *
  * - Advance their norma level.
  *
  * Furthermore, the `Player` class has a utility for generating random numbers,
  * which is primarily used for simulating dice rolls. By default, this utility is
  * an instance of the `Random` class but can be replaced if different random
  * generation behaviors are desired.
  *
  * @param name The name of the player. This is an identifier and should be unique.
  * @param maxHp The maximum health points a player can have. It represents the player's endurance.
  * @param attack The player's capability to deal damage to opponents.
  * @param defense The player's capability to resist or mitigate damage from opponents.
  * @param evasion The player's skill to completely avoid certain attacks.
  * @param randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random`
  *                              instance.
  *
  * @constructor Creates a new PlayerCharacter, specifying it's parameters
  *
  * @example
  * {{{
  * val Player1 = new PlayerCharacter("Johnny", 10, 3, 1, 20)
  * val roll = Player1.rollDice()
  * val name = Player1.name
  * println(s"$name has rolled $roll!")
  * }}}
  * This would print something like "Johnny has rolled 6!"
  *
  * @throws InvalidStatException If maxHp is less than or equal to 0 (The player would never be alive!)
  *
  * @author [[https://github.com/danielRamirezL/ Daniel Ramírez L.]]
  * @author [[https://github.com/joelriquelme/ Joel Riquelme P.]]
  * @author [[https://github.com/r8vnhill/ Ignacio Slater M.]]
  * @author [[https://github.com/Seivier/ Vicente González B.]]
  * @author [[https://github.com/Jimol1711/ Juan Molina L.]]
  */
class PlayerCharacter(val name: String,
                      val maxHp: Int,
                      val attack: Int,
                      val defense: Int,
                      val evasion: Int,
                      val randomNumberGenerator: Random = new Random()) extends AbstractUnit {

  var Norma: Int = 1

  /** The number of victories of a PlayerCharacter.
   *
   * This variable increases by one when the player defeats a Wild Unit and by 2 when it defeats another PlayerCharacter
   *
   */
  var victories: Int = 0

  /** Variable used for star gaining and to determine the amount needed for a character to recover after being defeated.
   *
   */
  private var chapters: Int = 0

  /** Rolls a dice and returns a value between 1 to 6. */
  def rollDice(): Int = {
    randomNumberGenerator.nextInt(6) + 1
  }

  /** When a PlayerCharacter is defeated it enters a KO state
   *
   * The KO method assert the PlayerCharacter was defeated. It so, the PlayerCharacter enters the recovery phase defined in another method.
   *
   */
  def KO(): Boolean = {
    if (this.defeated()) {
      true
    } else false
  }

  /** While a PlayerCharacter is on KO state it enters recovery.
   *
   * While on recovery, the PlayerCharacter throws a dice and when the roll is a number greater than or equal to the amount needed
   * it leaves the recovery phase and the KO state becomes false. The amount needed starts as six and goes down by one as chapters
   * go by.
   *
   */
  def recovery(): Unit = {
    var roll = this.rollDice()
    if (this.KO()) {
      if (6 - chapters >= roll) {
        currentHp = roll
      }
    }
  }

  /** When a PlayerCharacter performs a NormaClear, the Norma level of the player is increased by one. */
  def NormaClear(): Unit = {
    this.Norma += 1
  }

  /** On a PlayerCharacter's turn, they will be able to gain a number of stars equal to ⌊chapters/5⌋ + 1. */
  def onTurnStars(): Unit = {
    this.stars += floor(chapters/5).toInt + 1
  }

}
