package cl.uchile.dcc.citric
package model.NormaTests

import model.Units.PlayerCharacter

import cl.uchile.dcc.citric.model.Norma.{Norma3, Norma4}

import scala.util.Random

class Norma3Test extends munit.FunSuite {

  var testPlayer1: PlayerCharacter = new PlayerCharacter("testPlayer", 10, 1, 1, 1, new Random(11), 5)

  private var testNorma3: Norma3 = _

  override def beforeEach(context: BeforeEach): Unit = {
    testNorma3 = new Norma3(testPlayer1, 5)
  }

  test("The Norma should be able to perform a normaClear on a player") {
    testNorma3.normaClear(testPlayer1, 5)
    assert(testPlayer1.getNorma.isInstanceOf[Norma4])
  }

}
