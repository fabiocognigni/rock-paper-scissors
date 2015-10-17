package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.Game._
import org.scalatest.{MustMatchers, WordSpec}

class RockPaperScissorsLizardSpockSpec extends WordSpec with MustMatchers {
  import com.fabiocognigni.rock_paper_scissors.game.RockPaperScissorsLizardSpock._

  "A Rock Paper Scissors Lizard Spock game" must {
    "determine rock wins on scissors" in {
      val expectedResult = Win(Rock, "crushes", Scissors)
      play(Rock, Scissors) mustEqual expectedResult
      play(Scissors, Rock) mustEqual expectedResult
    }
    "determine rock wins on lizard" in {
      val expectedResult = Win(Rock, "crushes", Lizard)
      play(Rock, Lizard) mustEqual expectedResult
      play(Lizard, Rock) mustEqual expectedResult
    }

    "determine scissors wins on paper" in {
      val expectedResult = Win(Scissors, "cuts", Paper)
      play(Scissors, Paper) mustEqual expectedResult
      play(Paper, Scissors) mustEqual expectedResult
    }
    "determine scissors wins on lizard" in {
      val expectedResult = Win(Scissors, "decapitates", Lizard)
      play(Scissors, Lizard) mustEqual expectedResult
      play(Lizard, Scissors) mustEqual expectedResult
    }

    "determine paper wins on rock" in {
      val expectedResult = Win(Paper, "covers", Rock)
      play(Paper, Rock) mustEqual expectedResult
      play(Rock, Paper) mustEqual expectedResult
    }
    "determine paper wins on spock" in {
      val expectedResult = Win(Paper, "disproves", Spock)
      play(Paper, Spock) mustEqual expectedResult
      play(Spock, Paper) mustEqual expectedResult
    }

    "determine lizard wins on spock" in {
      val expectedResult = Win(Lizard, "poisons", Spock)
      play(Lizard, Spock) mustEqual expectedResult
      play(Spock, Lizard) mustEqual expectedResult
    }
    "determine lizard wins on paper" in {
      val expectedResult = Win(Lizard, "eats", Paper)
      play(Lizard, Paper) mustEqual expectedResult
      play(Paper, Lizard) mustEqual expectedResult
    }

    "determine spock wins on scissors" in {
      val expectedResult = Win(Spock, "smashes", Scissors)
      play(Spock, Scissors) mustEqual expectedResult
      play(Scissors, Spock) mustEqual expectedResult
    }
    "determine spock wins on rock" in {
      val expectedResult = Win(Spock, "vaporizes", Rock)
      play(Spock, Rock) mustEqual expectedResult
      play(Rock, Spock) mustEqual expectedResult
    }

  }

}
