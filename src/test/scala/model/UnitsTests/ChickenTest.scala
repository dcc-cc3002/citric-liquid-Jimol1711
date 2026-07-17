package cl.uchile.dcc.citric
package model.UnitsTests

import model.Units.PlayerCharacter
import model.Units.WildUnits.{Chicken, AbstractWildUnit}

import scala.math.max

class ChickenTest extends munit.FunSuite {

  private var player: PlayerCharacter = new PlayerCharacter("testPlayer",10,1,1,1,"stars")
  private var player2: PlayerCharacter = new PlayerCharacter("testPlayer",10,1,1,2,"stars")

  private var chicken: AbstractWildUnit = _
  private var chicken2: AbstractWildUnit = _

  override def beforeEach(context: BeforeEach): Unit = {
    chicken = new Chicken
    chicken2 = new Chicken
  }

  test("A Chicken should have correctly set their attributes") {
    assertEquals(chicken.maxHp, 3)
    assertEquals(chicken.offense, -1)
    assertEquals(chicken.defense, -1)
    assertEquals(chicken.evasion, +1)
    assertEquals(chicken.bonusStars, 3)
  }

  test("A chicken should be able to defend") {
    val initialHp: Int = chicken.getCurrentHp
    val defendedAttack: Int  = 5
    chicken.defend(player, defendedAttack)
    val expectedMinHp: Int = initialHp - max(1, (defendedAttack - chicken.offense) + defendedAttack)
    val expectedMaxHp: Int = initialHp
    assert(chicken.getCurrentHp >= expectedMinHp && chicken.getCurrentHp <= expectedMaxHp)
  }

  test("A chicken should be able to evade") {
    val initialHp: Int = chicken.getCurrentHp
    assert(chicken.getCurrentHp == initialHp || chicken.getCurrentHp <= initialHp)
  }

  test("A chicken should be able to get and set it's currentHp") {
    assert(chicken.getCurrentHp == 3)
    chicken.setCurrentHp(chicken.getCurrentHp - 1)
    assert(chicken.getCurrentHp == 2)
  }

  test("A chicken should be able to be attacked by a Player") {
    val playerHp: Int = player.getCurrentHp
    val chickenHp: Int = chicken.getCurrentHp
    chicken.attack(player)
    assert(playerHp == player.getCurrentHp)
    assert(chickenHp>chicken.getCurrentHp)
  }

  test("When a  WildUnit attack another WildUnit, nothing should happen") {
    chicken.attackWildUnit(chicken2)
    assert(chicken.testingVariable)
  }

}
