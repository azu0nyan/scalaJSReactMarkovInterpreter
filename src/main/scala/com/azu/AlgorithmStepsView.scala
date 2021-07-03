package com.azu

import com.azu.markov.{Algorithm, Interpreter, Rule}
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@react class AlgorithmStepsView extends StatelessComponent {
  case class Props(interp:Interpreter  )

  override def render(): ReactElement = {
    ol(
      table(
        tr(
          th("step"),
          th("string"),
          th("rule")
        ),
        props.interp.previous.zipWithIndex.map{
          case ((rule, string), id) =>
            tr(
              td(id.toString),
              td(string),
              td(rule.toPrettyString)
            )
        }
      ),
      div("current", h3(props.interp.current)),
      div("terminated", h3(props.interp.isTerminated.toString))
    )
  }
}
