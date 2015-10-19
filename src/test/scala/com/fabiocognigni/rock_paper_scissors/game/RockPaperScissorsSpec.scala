package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.Game.Win
import org.scalatest.{MustMatchers, WordSpec}

class RockPaperScissorsSpec extends WordSpec with MustMatchers {
  import RockPaperScissors._

  "A Rock Paper Scissors game" must {
    "determine rock wins on scissors" in {
      val expectedResult = Win(Rock, "crushes", Scissors)
      play(Rock, Scissors) mustEqual expectedResult
      play(Scissors, Rock) mustEqual expectedResult
    }

    "determine scissors wins on paper" in {
      val expectedResult = Win(Scissors, "cuts", Paper)
      play(Scissors, Paper) mustEqual expectedResult
      play(Paper, Scissors) mustEqual expectedResult
    }

    "determine paper wins on rock" in {
      val expectedResult = Win(Paper, "covers", Rock)
      play(Paper, Rock) mustEqual expectedResult
      play(Rock, Paper) mustEqual expectedResult
    }

    "return its name" in {
      name mustEqual "Rock Paper Scissors"
    }
  }

}
