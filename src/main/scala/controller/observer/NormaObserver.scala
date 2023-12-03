package cl.uchile.dcc.citric
package controller.observer

import cl.uchile.dcc.citric.model.units.PlayerCharacter

trait NormaObserver {
  def update(o: PlayerCharacter, arg: Any): Unit
}
