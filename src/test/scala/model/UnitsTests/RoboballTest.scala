package cl.uchile.dcc.citric
package model

import model.Units.Units
import cl.uchile.dcc.citric.model.Units.WildUnits.{Roboball, WildUnit}

class RoboballTest extends munit.FunSuite {

  var roboball: Units = _

  override def beforeEach(context: BeforeEach): Unit = {
    roboball = new Roboball
  }

  // test("A Roboball should have correctly set their attributes")

  // test("A Roboball should be able to gain stars")

}
