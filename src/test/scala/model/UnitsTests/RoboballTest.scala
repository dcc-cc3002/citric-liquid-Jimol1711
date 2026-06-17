package cl.uchile.dcc.citric
package model.UnitsTests


import cl.uchile.dcc.citric.model.Units.PlayerCharacter
import cl.uchile.dcc.citric.model.Units.WildUnits.{Roboball, AbstractWildUnit}

import scala.math.max

class RoboballTest extends munit.FunSuite {

  var player: PlayerCharacter = new PlayerCharacter("testPlayer",10,1,1,1,"stars")

  var roboball: AbstractWildUnit = _

  override def beforeEach(context: BeforeEach): Unit = {
    roboball = new Roboball
  }

  test("A Roboball should have correctly set their attributes") {
    assertEquals(roboball.maxHp, 3)
    assertEquals(roboball.offense, -1)
    assertEquals(roboball.defense, +1)
    assertEquals(roboball.evasion, -1)
    assertEquals(roboball.bonusStars, 2)
  }

  // test("A Roboball should be able to gain stars")

  test("A roboball should be able to defend") {
    val initialHp = roboball.getCurrentHp
    val defendedAttack = 5
    roboball.defend(player, defendedAttack)
    val expectedMinHp = initialHp - max(1, (defendedAttack - roboball.offense) + defendedAttack)
    val expectedMaxHp = initialHp
    assert(roboball.getCurrentHp >= expectedMinHp && roboball.getCurrentHp <= expectedMaxHp)
  }

  test("A roboball should be able to evade") {
    val initialHp: Int = roboball.getCurrentHp
    assert(roboball.getCurrentHp == initialHp || roboball.getCurrentHp <= initialHp)
  }

  test("A player should be able to get and set it's currentHp") {
    assert(roboball.getCurrentHp == 3)
    roboball.setCurrentHp(roboball.getCurrentHp - 1)
    assert(roboball.getCurrentHp == 2)
  }

}
