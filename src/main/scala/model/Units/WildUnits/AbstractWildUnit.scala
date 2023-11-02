package cl.uchile.dcc.citric
package model.Units.WildUnits

class AbstractWildUnit extends WildUnit {

  private val maxHp: Int = 3
  def attack(): Unit = {

  }

  def attackPlayer(): Unit = {

  }

  def attackWildUnit(): Unit = {

  }

  def defeated(): Boolean = {
    true
  }

  def defend(): Unit = {

  }

  def evade(): Unit = {

  }

  def getMaxHp: Int = {
    maxHp
  }
}
