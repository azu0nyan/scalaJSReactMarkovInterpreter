package com.azu.markov

import org.scalatest.AppendedClues.convertToClueful
import org.scalatest.Assertions._
import org.scalatest.funsuite.{AnyFunSuite, AsyncFunSuite}
import org.scalatest._
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class MarkovTests extends AnyFunSuite {
  test("rule parse test1"){
    Rule.fromSting("aa->bb") shouldBe Some(Rule("aa", "bb", false))
    Rule.fromSting("aa->  bb") shouldBe Some(Rule("aa", "bb", false))
    Rule.fromSting(" aa ->  bb") shouldBe Some(Rule("aa", "bb", false))
    Rule.fromSting(" aa ->  bb  ") shouldBe Some(Rule("aa", "bb", false))
    Rule.fromSting(" aa ->.  bb  ") shouldBe Some(Rule("aa", "bb", true))
    Rule.fromSting("aa->.bb") shouldBe Some(Rule("aa", "bb", true))
    Rule.fromSting("aa->. bb") shouldBe Some(Rule("aa", "bb", true))
    Rule.fromSting("aa ->. bb") shouldBe Some(Rule("aa", "bb", true))
    Rule.fromSting("aa -. bb") shouldBe None
    Rule.fromSting("aa - >. bb") shouldBe None
  }


}
