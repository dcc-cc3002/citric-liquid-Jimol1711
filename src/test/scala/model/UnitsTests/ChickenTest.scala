package cl.uchile.dcc.citric
package model

import model.Units.Units
import cl.uchile.dcc.citric.model.Units.WildUnits.{Chicken, WildUnit, AbstractWildUnit}

class ChickenTest extends munit.FunSuite {

  var chicken : Units = _

  override def beforeEach(context: BeforeEach): Unit = {
    chicken = new Chicken
  }

  test("A Chicken should have correctly set their attributes") {
    assertEquals(chicken.getMaxHp, 3)
    assertEquals(chicken.getOffense, -1)
    assertEquals(chicken.getDefense, -1)
    assertEquals(chicken.getEvasion, +1)
  }

  test("A Chicken should be able to gain stars") {
    val currentStars = chicken.stars
    chicken.stars += 1
    assert(chicken.stars > currentStars)
  }

}
