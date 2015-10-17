package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.Game.{Win, PlayerItem}

trait RockPaperScissors  extends Game {

  case object Rock extends PlayerItem("rock")
  case object Paper extends PlayerItem("paper")
  case object Scissors extends PlayerItem("scissors")

  override def winRules: Set[Win] = Set(
    Win(Paper, "covers", Rock),
    Win(Rock, "crushes", Scissors),
    Win(Scissors, "cuts", Paper)
  )
}

object RockPaperScissors extends RockPaperScissors