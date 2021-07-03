package com.azu

import com.azu.markov.{Algorithm, Interpreter}
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@react class InterpreterView extends Component {
  case class Props(alg: Algorithm, start: String)
  override type State = Interpreter
  override def initialState: Interpreter = Interpreter.build(props.alg, props.start)

  override def componentWillReceiveProps(props: Props): Unit = {
    println(s"props changed $props")
    setState(Interpreter.build(props.alg, props.start))
  }

  def nextStep(): Unit = {
    println(state)
    println(state.alg.findApplicable(state.current))
    println(state.step)
    for (newState <- state.step)
      setState(newState)
  }

  override def render(): ReactElement = div(
    button(
      "Шаг",
      className := "btn btn-primary",
      onClick := (() => nextStep())
    ),
    AlgorithmStepsView(state),

  )
}
