package cl.uchile.dcc.citric
package model.units

import model.units.{PlayerCharacter, Units}

import cl.uchile.dcc.citric.model.units.wildunits.{Seagull, AbstractWildUnit}
import scala.math.max

class SeagullTest extends munit.FunSuite {

  var player: PlayerCharacter = new PlayerCharacter("testPlayer",10,1,1,1,"stars")

  var seagull: AbstractWildUnit = _

  override def beforeEach(context: BeforeEach): Unit = {
    seagull = new Seagull
  }

  test("A Roboball should have correctly set their attributes") {
    assertEquals(seagull.maxHp, 3)
    assertEquals(seagull.offense, +1)
    assertEquals(seagull.defense, -1)
    assertEquals(seagull.evasion, -1)
    assertEquals(seagull.bonusStars, 2)
  }

  test("A seagull should be able to defend") {
    val initialHp = seagull.getCurrentHp
    val defendedAttack = 5
    seagull.defend(player, defendedAttack)
    val expectedMinHp = initialHp - max(1, (defendedAttack - seagull.offense) + defendedAttack)
    val expectedMaxHp = initialHp
    assert(seagull.getCurrentHp >= expectedMinHp && seagull.getCurrentHp <= expectedMaxHp)
  }

  test("Evade method should correctly adjust HP based on random dice roll") {
    val initialHp: Int = seagull.getCurrentHp
    assert(seagull.getCurrentHp == initialHp || seagull.getCurrentHp <= initialHp)
  }

  test("A player should be able to get and set it's currentHp") {
    assert(seagull.getCurrentHp == 3)
    seagull.setCurrentHp(seagull.getCurrentHp - 1)
    assert(seagull.getCurrentHp == 2)
  }

}
