package com.fabiocognigni.rock_paper_scissors

import com.fabiocognigni.rock_paper_scissors.game.Game.{Item, Win, Tie}
import com.fabiocognigni.rock_paper_scissors.game.{Game, RockPaperScissorsLizardSpock, RockPaperScissors}
import org.scalatest.{MustMatchers, WordSpec}


class PlayGameSpec extends WordSpec with MustMatchers {

  "A PlayGame component must" must {
    "select the correct game" in {
      PlayGame.selectGameType(Some("classic")) mustEqual RockPaperScissors
      PlayGame.selectGameType(Some("extended")) mustEqual RockPaperScissorsLizardSpock
    }

    "default to classic Rock Paper Scissors" in {
      PlayGame.selectGameType(None) mustEqual RockPaperScissors
      PlayGame.selectGameType(Some("not-valid-game-name")) mustEqual RockPaperScissors
    }
  }

  "build a proper message for the user given the Result" in {
    PlayGame.buildMessage(Tie) must include ("Tie")
    PlayGame.buildMessage(Win(Item1, "beats", Item2)) must include ("item1 beats item2")
  }

  "return a message including players' names, items' names, winner, loser and winning rule" in {
    implicit val game = GameExample

    val resultMessage = PlayGame.play(Player("player1", Item1), Player("player2", Item2))
    resultMessage must include ("player1")
    resultMessage must include ("player2")
    resultMessage must include ("item1 won")
    resultMessage must include ("item1 beats1 item2")
  }

  "return the proper message for a game player vs computer" in {
    implicit val game = GameExample

    PlayGame.playerVsComputer("not-valid-name") must include ("is not a valid item")

    val resultMessage = PlayGame.playerVsComputer("item1")
    resultMessage must include ("You")
    resultMessage must include ("Computer")
    resultMessage.contains("won!") || resultMessage.contains("Tie!") mustEqual true
  }

  "return the proper message for a game computer vs computer" in {
    implicit val game = GameExample

    val resultMessage = PlayGame.computerVsComputer
    resultMessage must include ("Computer 1")
    resultMessage must include ("Computer 2")
    resultMessage.contains("won!") || resultMessage.contains("Tie!") mustEqual true
  }

  case object Item1 extends Item("item1")
  case object Item2 extends Item("item2")
  case object Item3 extends Item("item3")

  object GameExample extends Game {
    override def name = "Example Game"

    val OneBeatsTwoAction: String = "beats1"
    val TwoBeatsThreeAction: String = "beats2"
    val ThreeBeatsOneAction: String = "beats3"

    override def winRules: Map[(Item, Item), String] = Map(
      (Item1, Item2) -> OneBeatsTwoAction,
      (Item2, Item3) -> TwoBeatsThreeAction,
      (Item3, Item1) -> ThreeBeatsOneAction)
  }

}
