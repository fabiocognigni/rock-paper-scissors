package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.GameDefinition.Win
import org.scalatest.{MustMatchers, WordSpec}

class RockPaperScissorsSpec extends WordSpec with MustMatchers {
  import RockPaperScissors._

  "A Rock Paper Scissors game definition" must {
    "determine rock wins on scissors" in {
      play(Rock, Scissors) mustEqual Win(Rock, "crushes", Scissors)
      play(Scissors, Rock) mustEqual Win(Rock, "crushes", Scissors)
    }

    "determine scissors wins on paper" in {
      play(Scissors, Paper) mustEqual Win(Scissors, "cuts", Paper)
      play(Paper, Scissors) mustEqual Win(Scissors, "cuts", Paper)
    }

    "determine paper wins on rock" in {
      play(Paper, Rock) mustEqual Win(Paper, "covers", Rock)
      play(Rock, Paper) mustEqual Win(Paper, "covers", Rock)
    }
  }

}
