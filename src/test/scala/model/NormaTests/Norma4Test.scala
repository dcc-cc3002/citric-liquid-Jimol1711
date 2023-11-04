package cl.uchile.dcc.citric
package model.NormaTests

import model.Units.PlayerCharacter

import cl.uchile.dcc.citric.model.Norma.{Norma4, Norma5}

import scala.util.Random

class Norma4Test extends munit.FunSuite {

  var testPlayer1: PlayerCharacter = new PlayerCharacter("testPlayer", 10, 1, 1, 1, new Random(11), 5)

  private var testNorma4: Norma4 = _

  override def beforeEach(context: BeforeEach): Unit = {
    testNorma4 = new Norma4(testPlayer1, 5)
  }

  test("The Norma should be able to perform a normaClear on a player") {
    testNorma4.normaClear(testPlayer1, 5)
    assert(testPlayer1.getNorma.isInstanceOf[Norma5])
  }

}
