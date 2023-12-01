package cl.uchile.dcc.citric
package controller.states

class GameOver extends AbstractState {

  override def reset(): Unit = {
    context.setState(new PreGame)
  }

}
