package com.azu

import com.azu.markov.{Algorithm, Interpreter, Rule}
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@react class AlgorithmStepsView extends StatelessComponent {
  case class Props(interp: Interpreter)

  override def render(): ReactElement = {
    div()(
      label(htmlFor := "currentStringId")("Текущая строка"),
      input(
        id := "currentStringId",
        readOnly,
        className := "form-control",
        value := props.interp.current
      ),
      label(htmlFor := "isTerminatedId")("Остановлено"),
      input(
        id := "isTerminatedId",
        disabled,
        value := props.interp.isTerminated.toString
      ),

      table(className := "table table-striped")(
        thead(
          tr(
            th("№"),
            th("строка"),
            th("правило")
          )
        ),
        props.interp.previous.zipWithIndex.reverse.map {
          case ((rule, string), id) =>
            tr(
              td(id.toString),
              td(string),
              td(rule.toPrettyString)
            )
        }
      )

    )
  }
}
