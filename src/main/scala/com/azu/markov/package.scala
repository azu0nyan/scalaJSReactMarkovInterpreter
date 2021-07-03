package com.azu

package object markov {
  val epsilon:Char = 'e'
  val arrow:String = "->"

  implicit class MyStrip(val str:String) extends AnyVal  {
    //scala js has no strip
    def myStrip():String = str.dropWhile(_ == ' ').reverse.dropWhile(_ == ' ').reverse
  }
}
