package cl.uchile.dcc.citric
package model

import model.Unit.{Chicken, Units}

class ChickenTest extends munit.FunSuite {

  var chicken : Units = _

  override def beforeEach(context: BeforeEach): Unit = {
    chicken = new Chicken
  }

  test("A Chicken should have correctly set their attributes") {
    assertEquals(chicken.maxHp, 3)
    assertEquals(chicken.attack, -1)
    assertEquals(chicken.defense, -1)
    assertEquals(chicken.evasion, +1)
  }

}
