package cl.uchile.dcc.citric
package controller

import controller.states.{GameState, PreGame}

import cl.uchile.dcc.citric.controller.observer.NormaObserver
import cl.uchile.dcc.citric.model.units.PlayerCharacter

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class GameController(private var players: ArrayBuffer[PlayerCharacter]) extends NormaObserver(this) {

  private var state: GameState = new PreGame

  private var orderedPlayers: mutable.Map[Int, PlayerCharacter] = mutable.Map.empty[Int, PlayerCharacter]

  def getOrderedPlayers: mutable.Map[Int, PlayerCharacter] = orderedPlayers


  def setState(newState: GameState): Unit = {
    state = newState
  }

  /** Orders the players in their turns, from the one with the highest roll to the lowest */
  def orderPlayers(): Unit = {

    val playerMap = mutable.Map[Int, PlayerCharacter]()
    var turn = 1

    val playerRolls = players.map(player => (player, player.rollDice())).sortBy(_._2).reverse

    playerRolls.foreach { case (player, initialRoll) =>
      var roll = initialRoll

      while (playerMap.exists { case (_, p) => p.rollDice() == roll }) {
        roll = player.rollDice()
      }

      playerMap(turn) = player
      turn += 1
    }

    orderedPlayers = playerMap
  }

}
