package com.fabiocognigni.rock_paper_scissors

import com.fabiocognigni.rock_paper_scissors.game.Game.{Item, Result, Tie, Win}
import com.fabiocognigni.rock_paper_scissors.game.{RockPaperScissorsLizardSpock, Game, RockPaperScissors}

import scala.util.{Failure, Success, Try}

object PlayGame {

  val games: Map[String, Game] =
    Map(  "classic" -> RockPaperScissors,
          "extended" -> RockPaperScissorsLizardSpock)

  def main (args: Array[String]) {
    // $COVERAGE-OFF$ this method cannot be tested: it's meant to drive the manual testing and it's not observable (it only prints to stdout)

    val gameSelector = Option(System.getProperty("game")) //from -Dgame JVM param
    implicit val game = selectGameType(gameSelector)

    val resultMessage = args.length match {
      case 0 =>
        println("Computer VS Computer")
        computerVsComputer
      case 1 =>
        println("You VS Computer")
        playerVsComputer(args(0))
      case _ =>
        println(s"""
                   |Invalid command!
                   |Please use:
                   |- no arguments for a ComputerVSComputer game;
                   |- one argument (your item choice) to play a game against the Computer.
                """.stripMargin)
    }

    println(resultMessage)

    // $COVERAGE-ON$
  }

  def selectGameType(gameArgument: Option[String]): Game = {
    if(gameArgument.isDefined) {
      games.getOrElse(gameArgument.get,
              {
                println(s"Invalid game type entered (values allowed are ${games.keys}): playing classic Rock Paper Scissors ...")
                RockPaperScissors
              })
    } else {
      //no -Dgame argument defaults silently to RockPaperScissors
      RockPaperScissors
    }
  }

  def computerVsComputer(implicit game: Game): String = {
    val computer1 = Player("Computer 1", game.randomItem)
    val computer2 = Player("Computer 2", game.randomItem)

    play(computer1, computer2)
  }

  def playerVsComputer(userItemName: String)(implicit game: Game): String = {
    game.nameToItem(userItemName) match {
      case Some(userItem) =>
        val user = Player("You", userItem)
        val computer = Player("Computer", game.randomItem)

        play(user, computer)
      case None =>
        val validItemNames = game.allItems map(_.name)
        s"$userItemName is not a valid item. Valid items: $validItemNames"
    }

  }

  /**
   * @return the result message to be displayed to the user
   */
  def play(player1: Player, player2: Player)(implicit game: Game): String = {
    Try(game.play(player1.item, player2.item)) match {
      case Success(result) =>
        s"${player1.name}: ${player1.item.name} \n" +
        s"${player2.name}: ${player2.item.name} \n" +
        buildMessage(result)
      case Failure(exception) =>
        s"Internal application error: ${exception.getMessage}"
    }
  }

  def buildMessage(result: Result): String = {
    result match {
      case Tie => "Tie!"
      case Win(winner, beatAction, loser) => s"${winner.name} won! (${winner.name} $beatAction ${loser.name})"
    }
  }
}

case class Player(name: String, item: Item)
