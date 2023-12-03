package cl.uchile.dcc.citric
package controller.states

import cl.uchile.dcc.citric.controller.GameController
import cl.uchile.dcc.citric.controller.states.player.PlayerTurn
import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable

class PreGame(context: GameController) extends AbstractState(context) {

  override def setTurns(): Unit = {

    val playerMap = mutable.Map[Int, PlayerCharacter]()
    var turn = 0

    val playerRolls = context.getPlayers.map(player => (player, player.rollDice())).sortBy(_._2).reverse

    playerRolls.foreach { case (player, initialRoll) =>
      var roll = initialRoll

      while (playerMap.exists { case (_, p) => p.rollDice() == roll }) {
        roll = player.rollDice()
      }

      playerMap(turn) = player
      turn += 1
    }

    context.setOrderedPlayers(playerMap)
    context.setState(new PlayerTurn(context))
  }

  override def isPreGameState: Boolean = true


}
