package cl.uchile.dcc.citric
package model.NormaTests

import model.Units.PlayerCharacter

import cl.uchile.dcc.citric.model.Norma.{Norma2, Norma3}

import scala.util.Random

class Norma2Test extends munit.FunSuite {

  var testPlayer1: PlayerCharacter = new PlayerCharacter("testPlayer", 10, 1, 1, 1, new Random(11), 5)

  private var testNorma2: Norma2 = _

  override def beforeEach(context: BeforeEach): Unit = {
    testNorma2 = new Norma2(testPlayer1, 5)
  }

  test("The Norma should be able to perform a normaClear on a player") {
    testNorma2.normaClear(testPlayer1, 5)
    assert(testPlayer1.getNorma.isInstanceOf[Norma3])
  }

}
