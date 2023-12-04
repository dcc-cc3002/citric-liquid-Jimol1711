package cl.uchile.dcc.citric
package controller.states

import controller.GameController

import cl.uchile.dcc.citric.model.panels.Panel
import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

class GameOver(context: GameController) extends AbstractState(context) {

  override def reset(newPlayers: ArrayBuffer[PlayerCharacter],newPanels: ArrayBuffer[Panel]): Unit = {
    context.createGame(newPlayers,newPanels)
  }

  override def isGameOverState: Boolean = true

}
