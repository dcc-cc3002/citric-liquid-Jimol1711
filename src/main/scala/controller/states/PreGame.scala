package cl.uchile.dcc.citric
package controller.states

import controller.GameController

import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, Map}

class PreGame(context: GameController, newPlayers: ArrayBuffer[PlayerCharacter]) extends AbstractState {

  /** Orders the players in their turns, from the one with the highest roll to the lowest */
  private def orderPlayers(): mutable.Map[Int,PlayerCharacter] = {

    val playerMap = mutable.Map[Int, PlayerCharacter]()
    var turn = 1

    val playerRolls = newPlayers.map(player => (player, player.rollDice())).sortBy(_._2).reverse

    playerRolls.foreach { case (player, initialRoll) =>
      var roll = initialRoll

      while (playerMap.exists { case (_, p) => p.rollDice() == roll }) {
        roll = player.rollDice()
      }

      playerMap(turn) = player
      turn += 1
    }

    playerMap
  }
  override def setTurns(): Unit = {
    context.setPlayersWithOrder(orderPlayers())
  }

}
