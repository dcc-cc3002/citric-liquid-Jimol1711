package cl.uchile.dcc.citric
package model

import model.Unit.{WildUnit, Roboball, Units}

class RoboballTest extends munit.FunSuite {

  var roboball: WildUnit = _

  override def beforeEach(context: BeforeEach): Unit = {
    roboball = new Roboball
  }

  test("A Roboball should have correctly set their attributes") {
    assertEquals(roboball.maxHp, 3)
    assertEquals(roboball.attack, -1)
    assertEquals(roboball.defense, +1)
    assertEquals(roboball.evasion, -1)
  }

}
