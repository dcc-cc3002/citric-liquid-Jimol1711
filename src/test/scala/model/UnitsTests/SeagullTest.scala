package cl.uchile.dcc.citric
package model

import model.Unit.{Roboball, Units}
class SeagullTest extends munit.FunSuite {

  var seagull: Units = _

  override def beforeEach(context: BeforeEach): Unit = {
    seagull = new Roboball
  }

  test("A Seagull should have correctly set their attributes") {
    assertEquals(seagull.maxHp, 3)
    assertEquals(seagull.attack, +1)
    assertEquals(seagull.defense, -1)
    assertEquals(seagull.evasion, -1)
  }

}
