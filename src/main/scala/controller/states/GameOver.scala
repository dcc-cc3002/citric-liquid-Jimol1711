package cl.uchile.dcc.citric
package controller.states

import controller.GameController

import cl.uchile.dcc.citric.model.Board
import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** Game over state of a game.
 *
 * @param context the context of a game.
 *
 */
class GameOver(context: GameController) extends AbstractState(context) {

  override def reset(newPlayers: ArrayBuffer[PlayerCharacter]): Unit = {
    context.setBoard(new Board(newPlayers))
    context.setCurrentTurn(0)
    context.resetOrderedPlayers()
    context.createGame(newPlayers)
    context.setCurrentChapter(1)
    context.createGame(newPlayers)
    context.setState(new PreGame(context))
  }

  override def isGameOverState: Boolean = true

}
