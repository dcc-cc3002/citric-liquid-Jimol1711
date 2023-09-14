package cl.uchile.dcc.citric
package model

abstract class AbstractUnit extends Unit {
  def defeated: Boolean = {
    if (currentHp <= 0) true
    else false
  }

}
