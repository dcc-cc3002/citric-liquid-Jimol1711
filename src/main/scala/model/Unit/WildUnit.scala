package cl.uchile.dcc.citric
package model.Unit

abstract class AbstractWildUnit extends AbstractUnit {

  /**
   * 
   */
  val maxHp: Int = 3
  var currentHp: Int = maxHp
  var stars: Int = 0


}
