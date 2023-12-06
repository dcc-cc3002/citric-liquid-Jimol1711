package cl.uchile.dcc.citric
package model.norma

import model.units.PlayerCharacter

import cl.uchile.dcc.citric.model.norma.{Norma5, Norma6}

class Norma5Test extends munit.FunSuite {

  private val chosenStat1: String = "stars"
  private val chosenStat2: String = "victories"
  private val testPlayer1: PlayerCharacter = new PlayerCharacter("testPlayer1", 10, 1, 1, 1, "stars")
  private val testPlayer2: PlayerCharacter = new PlayerCharacter("testPlayer2", 10, 1, 1, 1, "victories")

  private var testNorma1: Norma5 = _
  private var testNorma2: Norma5 = _

  override def beforeEach(context: BeforeEach): Unit = {
    testNorma1 = new Norma5(chosenStat1, 200)
    testNorma2 = new Norma5(chosenStat2, 14)
  }

  test("The Norma should be able to perform a normaClear on a player, either if it chose stars or victories as their stat requirement") {
    testPlayer1.setStars(testPlayer1.getStars + 210)
    testPlayer1.setNorma(testNorma1)
    testNorma1.normaClear(testPlayer1, "stars")
    assert(testPlayer1.getNorma.getClass == classOf[Norma6])
    testPlayer2.setStars(testPlayer2.getVictories + 15)
    testPlayer2.setNorma(testNorma2)
    testNorma2.normaClear(testPlayer2, "victories")
    assert(testPlayer2.getNorma.getClass == classOf[Norma6])
  }

}
