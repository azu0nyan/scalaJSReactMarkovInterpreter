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
  case class State(inputRules: String, startString: String, alg: Algorithm, interpreter: Interpreter)

  override def initialState: State = State("", "", Algorithm.fromLines(""),
    Interpreter.build(Algorithm.fromLines(""), ""))


  def handleRulesChange(e: SyntheticEvent[html.TextArea, Event]): Unit = {
    val eventValue = e.target.value
    setState(_.copy(inputRules = eventValue, alg = Algorithm.fromLines(eventValue)))
  }

  def handleInputStringChange(e: SyntheticEvent[html.Input, Event]): Unit = {
    val eventValue = e.target.value
    setState(_.copy(startString = eventValue))
  }


  override def render(): ReactElement = div(className := "container-fluid")(
    div(className := "row")(
      div(className := "col")(
        label(htmlFor := "rulesInput")("Введите правила"),
        textarea(
          id := "rulesInput",
          className := "form-control",
          rows := "20",
          onChange := (handleRulesChange(_)),
          value := state.inputRules
        )
      ),
      div(className := "col")(
        label(htmlFor := "initialString")("Начальная строка"),
        input(
          id := "initialString",
          className := "form-control",
          onChange := (handleInputStringChange(_)),
          value := state.startString
        ),
        div(
          label(htmlFor := "rulesId")("Правила"),
          div(id := "rulesId")(
            AlgorithmRulesView(alg = state.alg)
          ),
        )
      ),
      div(className := "col")(
        InterpreterView(alg = state.alg, start = state.startString)
      ),
    )
  )
}
