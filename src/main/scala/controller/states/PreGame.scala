package cl.uchile.dcc.citric
package controller.states

import cl.uchile.dcc.citric.controller.GameController
import cl.uchile.dcc.citric.exceptions.InvalidActionException

/** Pre game state of a game.
 *
 * @param context the context of a game.
 *
 */
class PreGame(context: GameController) extends AbstractState(context) {

  /** Starts a game by setting an order for player turns.
   *
   * @throws InvalidActionException if there are no players.
   *
   */
  override def startGame(): Unit = {

    if (context.getPlayers.isEmpty) {
      throw new InvalidActionException("A game has not been created")
    }

    var turn = 0

    val playerRolls = context.getPlayers.map(player => (player, player.rollDice())).sortBy(_._2).reverse

    playerRolls.foreach { case (player, initialRoll) =>
      var roll = initialRoll

      while (context.getOrderedPlayers.exists { case (_, p) => p.rollDice() == roll }) {
        roll = player.rollDice()
      }

      context.setOrderedPlayers(turn,player)
      turn += 1
    }

    context.setState(new Chapter(context))
  }

  override def isPreGameState: Boolean = true

}
