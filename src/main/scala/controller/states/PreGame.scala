package cl.uchile.dcc.citric
package controller.states

import controller.GameController

import cl.uchile.dcc.citric.controller.states.player.PlayerTurn

import scala.collection.mutable.ArrayBuffer

class PreGame(controller: GameController) extends GameState {
  override def setOrder(): Unit = {
    val roll1: Int = player1.rollDice()
    val roll2: Int = player2.rollDice()
    val roll3: Int = player3.rollDice()
    val roll4: Int = player4.rollDice()
    val rolls: ArrayBuffer[Int] = ArrayBuffer(roll1,roll2,roll3,roll4)

    controller.state = new PlayerTurn()
  }

  override def rollDice(): Unit = {

    controller.state = new PlayerTurn()
  }

  override def doEffect(): Unit = {
    /* ... */
    controller.state = new /* ... */
  }
}
