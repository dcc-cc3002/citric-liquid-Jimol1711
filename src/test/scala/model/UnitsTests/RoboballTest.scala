package cl.uchile.dcc.citric
package model

import model.Units.Units
import cl.uchile.dcc.citric.model.Units.WildUnits.{Roboball, WildUnit}

class RoboballTest extends munit.FunSuite {

  var roboball: Units = _

  override def beforeEach(context: BeforeEach): Unit = {
    roboball = new Roboball
  }

  test("A Roboball should have correctly set their attributes") {
    assertEquals(roboball.maxHp, 3)
    assertEquals(roboball.attack, -1)
    assertEquals(roboball.defense, +1)
    assertEquals(roboball.evasion, -1)
  }

  test("A Roboball should be able to gain stars") {
    val currentStars = roboball.stars
    roboball.stars += 1
    assert(roboball.stars > currentStars)
  }

}
