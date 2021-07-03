package com.azu


import com.azu.markov.Algorithm
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@react class AlgorithmRulesView extends StatelessComponent {
  case class Props(alg: Algorithm)

  override def render(): ReactElement = {
    ol(
      props.alg.rules.map { rule =>
        li(rule.left, span(if (rule.isTerminal) "->." else "->"), rule.right)
      }
    )
  }
}

