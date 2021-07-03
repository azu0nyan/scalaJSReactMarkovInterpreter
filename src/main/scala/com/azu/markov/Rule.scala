package com.azu.markov

object Rule {


  def fromSting(from: String): Option[Rule] = {
    val splitted = from.split(arrow)
    Option.when(splitted.length == 2) {
      println(splitted(0))
      println(splitted(0).filter(_ != epsilon))
      println(splitted(0).filter(_ != epsilon).myStrip())
      val left = splitted(0).filter(_ != epsilon).myStrip()
      val isTerminal = splitted(1).startsWith(".")
      val right =
        (if (isTerminal) splitted(1).substring(1)
        else splitted(1)).filter(_ != epsilon).myStrip()
      Rule(left, right, isTerminal)
    }
  }
}

case class Rule(left: String, right: String, isTerminal: Boolean) {
  def applicable(to: String): Boolean = to.contains(left)
  def apply(to: String): String = {
    val from = to.indexOf(left)
    if(from >= 0){
      to.substring(0, from) + right + to.substring(from + left.length)
    } else to
  }

  def toPrettyString = s"$left ->${if(isTerminal)"." else ""} $right"
}
