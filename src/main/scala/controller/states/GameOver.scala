package cl.uchile.dcc.citric
package controller.states

import controller.GameController

import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** Game over state of a game.
 *
 * @param context the context of a game.
 *
 */
class GameOver(context: GameController) extends AbstractState(context) {

  override def reset(newPlayers: ArrayBuffer[PlayerCharacter]): Unit = {
    val newContext: GameController = new GameController
    newContext.createGame(newPlayers)
  }

  override def isGameOverState: Boolean = true

}
