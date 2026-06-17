package cl.uchile.dcc.citric
package model.NormaTests

import model.Units.PlayerCharacter

import cl.uchile.dcc.citric.model.Norma.{Norma1, Norma2}

class Norma1Test extends munit.FunSuite {

  private val chosenStat1: String = "stars"
  private val chosenStat2: String = "victories"
  private val testPlayer1: PlayerCharacter = new PlayerCharacter("testPlayer1", 10, 1, 1, 1, "stars")
  private val testPlayer2: PlayerCharacter = new PlayerCharacter("testPlayer2", 10, 1, 1, 1, "victories")

  private var testNorma1: Norma1 = _
  private var testNorma2: Norma1 = _

  override def beforeEach(context: BeforeEach): Unit = {
    testNorma1 = new Norma1(chosenStat1, 10)
    testNorma2 = new Norma1(chosenStat2, 1)
  }

  test("The Norma should be able to perform a normaClear on a player, either if it chose stars or victories as their stat requirement") {
    testPlayer1.setStars(testPlayer1.getStars+20)
    testPlayer1.setNorma(testNorma1)
    testNorma1.normaClear(testPlayer1,"stars")
    assert(testPlayer1.getNorma.getClass==classOf[Norma2])
    testPlayer2.setStars(testPlayer2.getVictories + 2)
    testPlayer2.setNorma(testNorma2)
    testNorma2.normaClear(testPlayer2, "victories")
    assert(testPlayer2.getNorma.getClass == classOf[Norma2])
  }

}
