package cl.uchile.dcc.citric
package model

import model.Unit.{Chicken, Units, WildUnit}

class ChickenTest extends munit.FunSuite {

  var chicken : WildUnit = _

  override def beforeEach(context: BeforeEach): Unit = {
    chicken = new Chicken
  }

  test("A Chicken should have correctly set their attributes") {
    assertEquals(chicken.maxHp, 3)
    assertEquals(chicken.attack, -1)
    assertEquals(chicken.defense, -1)
    assertEquals(chicken.evasion, +1)
  }

  test("A Chicken should be able to gain stars") {
    val currentStars = chicken.stars
    chicken.stars += 1
    assert(chicken.stars > currentStars)
  }

}
