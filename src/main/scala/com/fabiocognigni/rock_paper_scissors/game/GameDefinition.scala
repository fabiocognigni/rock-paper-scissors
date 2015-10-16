package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.GameDefinition.{Win, Tie, Result, PlayerItem}


trait GameDefinition {
  
  /**
   * Override it by defining the winning rules as a map.
   * Keys are tuples (winnerItem, loserItem).
   * Values are the beat actions.
   */
  val winningRules: Map[(PlayerItem, PlayerItem), String]

  /**
   * Determines all the distinct items according to the rules of the game
   * @return all the distinct items according to the rules of the game
   */
  def allItems: Set[PlayerItem] = {
    val allWinners: Set[PlayerItem] = winningRules.keys.map(_._1).toSet
    val allLosers: Set[PlayerItem] = winningRules.keys.map(_._2).toSet

    allWinners union allLosers
  }

  /**
   * Retrieves the player item entity given the name of the item.
   * Assumption: a valid item must exist in the rules of the game.
   * Note: defining the conversion name to item in a separate data structure would be less complex from a
   * computational point of view but harder to maintain and prone to inconsistencies because it would introduce redundancy.
   * @param itemName name of the item
   * @return a defined option with the player item object when the name exists, None if the name does not exist in the rules.
   */
  def nameToItem(itemName: String): Option[PlayerItem] = {
    val gameItems: Set[PlayerItem] = allItems

    gameItems.filter(_.name == itemName).headOption
  }

  /**
   * Determines who the winner is.
   * @param item1 item chosen by player 1
   * @param item2 item chosen by player 2
   * @return an object Result containing the winner, the loser and the beat action or an object Tie when players choose same items.
   * @throws IllegalStateException when no matching rules are found to determine the winner because either the items passed in
   *                               input does not exist in the game domain or the rules have not been consistently defined.
   */
  def play(item1: PlayerItem, item2: PlayerItem): Result = {
    if(item1 == item2)
      Tie
    else {
      val winner1 = winningRules.get((item1, item2))
      val winner2 = winningRules.get((item2, item1))

      (winner1, winner2) match {
        case (Some(beatAction), None) =>
          Win(item1, beatAction, item2)
        case (None, Some(beatAction)) =>
          Win(item2, beatAction, item1)
        case _ =>
          throw new IllegalStateException(s"No matching rules for the players' items: player1=$item1; player2=$item2; rules=$winningRules")
      }
    }
  }
}

object GameDefinition {

  abstract class PlayerItem(val name: String)

  //case object item1 extends PlayerItem("item1")

  /**
   * Common abstraction to express the 2 possible outcomes with their types.
   */
  sealed trait Result

  case object Tie extends Result

  case class Win(winner: PlayerItem, beatAction: String, loser: PlayerItem) extends Result

}
