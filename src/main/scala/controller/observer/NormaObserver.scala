package cl.uchile.dcc.citric
package controller.observer

import cl.uchile.dcc.citric.model.units.PlayerCharacter

trait NormaObserver {

  /** The update method for the observer of the norma of a player.
   *
   * The name "Norma Observer" is because it observes the player's norma's, despite actually being an observer of the players.
   *
   * @param o the observed player.
   * @param arg an argument that gives information about the observed player.
   *
   */
  def update(o: PlayerCharacter, arg: Any): Unit
}
