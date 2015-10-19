package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.Game.{Tie, Win, Item}
import org.scalatest.{MustMatchers, WordSpec}


class GameSpec extends WordSpec with MustMatchers {

  "A well defined game" must {
    import CompleteGame._

    "determine the winner" in {
      play(Item1, Item2) mustEqual Win(Item1, OneBeatsTwoAction, Item2)
      play(Item2, Item1) mustEqual Win(Item1, OneBeatsTwoAction, Item2)

      play(Item2, Item3) mustEqual Win(Item2, TwoBeatsThreeAction, Item3)
      play(Item3, Item2) mustEqual Win(Item2, TwoBeatsThreeAction, Item3)

      play(Item3, Item1) mustEqual Win(Item3, ThreeBeatsOneAction, Item1)
      play(Item1, Item3) mustEqual Win(Item3, ThreeBeatsOneAction, Item1)
    }

    "detect a tie" in {
      play(Item1, Item1) mustEqual Tie
      play(Item2, Item2) mustEqual Tie
      play(Item3, Item3) mustEqual Tie
    }

    "throw an exception" when {
      case object Item4 extends Item("item4")
      case object Item5 extends Item("item5")

      "one of the items provided does not exist in the rules of the game" in {
        an[IllegalStateException] must be thrownBy {
          play(Item4, Item1)
        }
        an[IllegalStateException] must be thrownBy {
          play(Item1, Item4)
        }
      }

      "both the items provided do not exist in the rules of the game" in {
        an[IllegalStateException] must be thrownBy { play(Item4, Item5) }
        an[IllegalStateException] must be thrownBy { play(Item5, Item4) }
      }
    }
  }

  "A game with incomplete rules" must {
    import IncompleteGame._

    "throw an exception" when {
      "the items provided exist but no rule defines them" in {
        an [IllegalStateException] must be thrownBy {play(Item1, Item3)}
        an [IllegalStateException] must be thrownBy {play(Item3, Item1)}
      }
    }
  }

  "A game" must {
    "find out the set of all participant items in the game from the rules" in {
      CompleteGame.allItems must contain theSameElementsAs Set(Item1, Item2, Item3)
      IncompleteGame.allItems must contain theSameElementsAs Set(Item1, Item2, Item3)
    }

    "find the item entity given its name" in {
      CompleteGame.nameToItem(Item1.name) mustEqual Some(Item1)
      CompleteGame.nameToItem(Item2.name) mustEqual Some(Item2)
      CompleteGame.nameToItem(Item3.name) mustEqual Some(Item3)

      IncompleteGame.nameToItem(Item1.name) mustEqual Some(Item1)
      IncompleteGame.nameToItem(Item2.name) mustEqual Some(Item2)
      IncompleteGame.nameToItem(Item3.name) mustEqual Some(Item3)
    }

    "return no items when the name provided is not defined in the rules" in {
      val NotExistingName = "not-existing-name"

      CompleteGame.nameToItem(NotExistingName) mustEqual None
      IncompleteGame.nameToItem(NotExistingName) mustEqual None
    }

    "generate a random Item that belongs to the set of allowed items" in {
      val randomItem = CompleteGame.randomItem
      (randomItem == Item1 || randomItem == Item2 || randomItem == Item3) mustEqual true
    }
  }

}

case object Item1 extends Item("item1")
case object Item2 extends Item("item2")
case object Item3 extends Item("item3")

object CompleteGame extends Game {
  override def name = "Game with a complete set of rules"

  val OneBeatsTwoAction: String = "1 beats 2"
  val TwoBeatsThreeAction: String = "2 beats 3"
  val ThreeBeatsOneAction: String = "3 beats 1"

  override def winRules: Map[(Item, Item), String] = Map(
    (Item1, Item2) -> OneBeatsTwoAction,
    (Item2, Item3) -> TwoBeatsThreeAction,
    (Item3, Item1) -> ThreeBeatsOneAction)

}

object IncompleteGame extends Game {
  override def name = "Game with incomplete set of rules"

  val OneBeatsTwoAction: String = "1 beats 2"
  val TwoBeatsThreeAction: String = "2 beats 3"

  /**
   * Note: there is no rule to define the result between Item1 and Item3
   */
  override def winRules: Map[(Item, Item), String] = Map(
    (Item1, Item2) -> OneBeatsTwoAction,
    (Item2, Item3) -> TwoBeatsThreeAction)

}
