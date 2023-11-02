package cl.uchile.dcc.citric
package model

import model.Units.Units
import model.Units.WildUnits.Chicken

class ChickenTest extends munit.FunSuite {

  var chicken : Units = _

  override def beforeEach(context: BeforeEach): Unit = {
    chicken = new Chicken
  }

  // test("A Chicken should have correctly set their attributes")

  // test("A Chicken should be able to gain stars when defeating a player")

}
