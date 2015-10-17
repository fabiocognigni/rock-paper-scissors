package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.Game.{Win, Item}

object RockPaperScissorsLizardSpock extends RockPaperScissors {

  override def winRules: Set[Win] = super.winRules ++ Set(
    Win(Rock, "crushes", Lizard),
    Win(Paper, "disproves", Spock),
    Win(Scissors, "decapitates", Lizard),
    Win(Lizard, "eats", Paper),
    Win(Lizard, "poisons", Spock),
    Win(Spock, "smashes", Scissors),
    Win(Spock, "vaporizes", Rock)
  )

  case object Lizard extends Item("lizard")
  case object Spock extends Item("spock")
}
