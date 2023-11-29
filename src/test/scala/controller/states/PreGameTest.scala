package cl.uchile.dcc.citric
package controller.states

import model.units.PlayerCharacter

class PreGameTest extends munit.FunSuite {

  private var player1: PlayerCharacter = new PlayerCharacter("testPlayer1",10,1,1,1,"stars")
  private var player2: PlayerCharacter = new PlayerCharacter("testPlayer2",10,1,1,1,"stars")
  private var player3: PlayerCharacter = new PlayerCharacter("testPlayer3",10,1,1,1,"stars")
  private var player4: PlayerCharacter = new PlayerCharacter("testPlayer4",10,1,1,1,"stars")

  override def beforeEach(context: BeforeEach): Unit = {

  }

}
