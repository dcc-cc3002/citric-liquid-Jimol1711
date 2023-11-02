package cl.uchile.dcc.citric
package model.NormaTests

import model.Units.PlayerCharacter

import cl.uchile.dcc.citric.model.Norma.{Norma, Norma1}

import scala.util.Random

class Norma1Test extends munit.FunSuite {

  var testPlayer1: PlayerCharacter = new PlayerCharacter("testPlayer", 10, 1, 1, 1, new Random(11), 5)

  override def beforeEach(context: BeforeEach): Unit = {
    val testNorma1: Norma = new Norma1(testPlayer1, testPlayer1.chosenStat)
  }

  test("The Norma should be able to perform a normaClear on it's player") {

  }

}
