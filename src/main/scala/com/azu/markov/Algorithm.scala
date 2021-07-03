package com.azu.markov

object Algorithm {
  def fromLines(lines:String):Algorithm = {
    Algorithm(lines.split("\\n").flatMap(Rule.fromSting).toSeq)
  }
}

case class Algorithm(rules:Seq[Rule]){
  def findApplicable(to:String):Option[Rule] = rules.find(_.applicable(to))
}