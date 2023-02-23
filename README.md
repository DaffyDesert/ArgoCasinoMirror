# US1_Tasks_3_4_5_7:

Card Class:
 * Class that represents a single playing card
 
  * rank: ace, 2-10, jack, queen, king (13 possible values)
  * suit: heart, club, diamond, spade
  * value: combination of the rank and suit of a card (5H, KD)
  * faceDown: boolean value representing wether a card value is showing or the back of the card is showing
  * cardImage: when a card is constructed a specific .png file is associated to it based on the value of the card (AH = AH.png, FD = backOfCard.png, etc..)
  * each card object has a draw function that returns a JPanel of the Card Image

CardStack:
 * Class used to implement what a stack of cards should be expected to do (shuffle the stack, deal from the top or bottom of a stack, deal to the top or bottom of a stack, etc...)
 * Allows for better abstraction between a deck, player hand, dealer hand, discard piles, etc..
 * stackName - name of the stack object
 * stack - list of card objects to handle 
- each stack object has a draw function that returns a JPanel of the stack of card images with the stack name listed below (player_1, discard_pile_1, etc...)

Deck extends CardStack:
 * only holds a default constructor that creates a standard deck of 52 playing cards

Board
 * Board class that will create a playing area for stack of cards to be created and manipulated
 * Class should eventually be abstract class to more defined GameBoard classes such as WarBoard, BlackJackBoard, etc..
 * HAS STUBB draw function that returns a JPanel of the various stacks created for the board (this should be removed because defined GameBoard classes should be drawing how their board is laid out)
