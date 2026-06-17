package cl.uchile.dcc.citric
package controller.observer
import controller.GameController

import cl.uchile.dcc.citric.controller.states.{GameOver, GameState}
import cl.uchile.dcc.citric.model.Norma.Norma

import scala.collection.mutable.ArrayBuffer

class NormaObserver(private var gameController: GameController) extends INormaObserver {

  override def update(gs: GameState, arg: Any): Unit = ???

}
