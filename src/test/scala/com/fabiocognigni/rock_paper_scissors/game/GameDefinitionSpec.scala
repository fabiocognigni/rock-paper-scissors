package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.GameDefinition.{Tie, Win, PlayerItem}
import org.scalatest.{MustMatchers, WordSpec}


class GameDefinitionSpec extends WordSpec with MustMatchers {

  "A complete game definition" must {
    import CompleteGameDefinition._

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
      case object Item4 extends PlayerItem("item4")
      case object Item5 extends PlayerItem("item5")

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

  "An incomplete/invalid game definition" must {
    import IncompleteGameDefinition._

    "throw an exception" when {
      "the items provided exist but no rule defines them" in {
        an [IllegalStateException] must be thrownBy {play(Item1, Item3)}
        an [IllegalStateException] must be thrownBy {play(Item3, Item1)}
      }
    }
  }

  "A game definition" must {
    "find the item entity given its name" in {
      CompleteGameDefinition.nameToItem(Item1.name) mustEqual Some(Item1)
      CompleteGameDefinition.nameToItem(Item2.name) mustEqual Some(Item2)
      CompleteGameDefinition.nameToItem(Item3.name) mustEqual Some(Item3)

      IncompleteGameDefinition.nameToItem(Item1.name) mustEqual Some(Item1)
      IncompleteGameDefinition.nameToItem(Item2.name) mustEqual Some(Item2)
      IncompleteGameDefinition.nameToItem(Item3.name) mustEqual Some(Item3)
    }

    "return no items when the name provided is not defined in the rules" in {
      val NotExistingName = "not-existing-name"

      CompleteGameDefinition.nameToItem(NotExistingName) mustEqual None
      IncompleteGameDefinition.nameToItem(NotExistingName) mustEqual None
    }
  }

}

case object Item1 extends PlayerItem("item1")
case object Item2 extends PlayerItem("item2")
case object Item3 extends PlayerItem("item3")

object CompleteGameDefinition extends GameDefinition {
  val OneBeatsTwoAction: String = "1 beats 2"
  val TwoBeatsThreeAction: String = "2 beats 3"
  val ThreeBeatsOneAction: String = "3 beats 1"

  override val winningRules: Map[(PlayerItem, PlayerItem), String] =
    Map(  (Item1, Item2) -> OneBeatsTwoAction,
          (Item2, Item3) -> TwoBeatsThreeAction,
          (Item3, Item1) -> ThreeBeatsOneAction)

}

object IncompleteGameDefinition extends GameDefinition {
  val OneBeatsTwoAction: String = "1 beats 2"
  val TwoBeatsThreeAction: String = "2 beats 3"

  /**
   * Note: there is no rule to define the result between Item1 and Item3
   */
  override val winningRules: Map[(PlayerItem, PlayerItem), String] =
    Map(  (Item1, Item2) -> OneBeatsTwoAction,
          (Item2, Item3) -> TwoBeatsThreeAction)

}
