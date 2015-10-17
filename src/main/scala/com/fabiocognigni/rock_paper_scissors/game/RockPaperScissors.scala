package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.Game.{Win, Item}

trait RockPaperScissors  extends Game {

  case object Rock extends Item("rock")
  case object Paper extends Item("paper")
  case object Scissors extends Item("scissors")

  override def winRules: Set[Win] = Set(
    Win(Paper, "covers", Rock),
    Win(Rock, "crushes", Scissors),
    Win(Scissors, "cuts", Paper)
  )
}

object RockPaperScissors extends RockPaperScissors