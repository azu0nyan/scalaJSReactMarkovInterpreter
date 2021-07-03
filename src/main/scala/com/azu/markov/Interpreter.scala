package com.azu.markov

object Interpreter {
  def build(alg:Algorithm, string:String):Interpreter =
    Interpreter(alg, string, Seq(), alg.findApplicable(string).isEmpty)

}

case class Interpreter(alg: Algorithm,
                       current: String,
                       previous: Seq[(Rule, String)],
                       isTerminated: Boolean) {
  def step:Option[Interpreter] = {
    val rule = alg.findApplicable(current)
    Option.when(!isTerminated && rule.nonEmpty) {
      val newCurrent = rule.get(current)
      Interpreter(alg, newCurrent, previous :+ (rule.get, current),
        rule.get.isTerminal || alg.findApplicable(newCurrent).isEmpty)
    }
  }
}
