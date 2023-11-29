package cl.uchile.dcc.citric
package controller.observer

import cl.uchile.dcc.citric.controller.states.GameState

trait INormaObserver {

  def update(gs: GameState, arg: Any)

}
