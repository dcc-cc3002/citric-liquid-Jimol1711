package cl.uchile.dcc.citric
package model

import model.Units.Units
import cl.uchile.dcc.citric.model.Units.WildUnits.{Seagull, WildUnit}

class SeagullTest extends munit.FunSuite {

  var seagull: Units = _

  override def beforeEach(context: BeforeEach): Unit = {
    seagull = new Seagull
  }

  // test("A Seagull should have correctly set their attributes")

  // test("A Seagull should be able to gain stars")

}
