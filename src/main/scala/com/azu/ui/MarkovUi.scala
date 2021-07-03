package com.azu.ui

import com.azu.{AlgorithmRulesView, AlgorithmStepsView, InterpreterView}
import com.azu.markov.{Algorithm, Interpreter}
import org.scalajs.dom.Event
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._
import org.scalajs.dom.{Event, html}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@react class MarkovUi extends Component {
  type Props = Unit
  case class State(inputRules:String, startString:String, alg:Algorithm, interpreter:Interpreter)

  override def initialState: State = State("", "", Algorithm.fromLines(""),
    Interpreter.build(Algorithm.fromLines(""), ""))


  def handleRulesChange(e: SyntheticEvent[ html.TextArea, Event]): Unit = {
    val eventValue = e.target.value
    setState(_.copy(inputRules = eventValue, alg = Algorithm.fromLines(eventValue)))
  }

  def handleInputStringChange(e: SyntheticEvent[ html.Input, Event]): Unit = {
    val eventValue = e.target.value
    setState(_.copy(startString = eventValue))
  }


  override def render(): ReactElement = div(
    div(style := js.Dynamic.literal(float = "left"))(
      h3("Enter rules"),
      textarea(
        onChange := (handleRulesChange(_)),
        value := state.inputRules
      )
    ),
    div(style := js.Dynamic.literal(float = "left"))(
      h3("Rules"),
      AlgorithmRulesView(alg = state.alg)
    ),
    div(
      h3("Interpretation"),
      input(
        onChange := (handleInputStringChange(_)),
        value := state.startString
      ),
      InterpreterView(alg = state.alg, start = state.startString)
    ),
  )
}
