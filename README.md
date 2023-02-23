# Title of Project:

#### Project Description: 

###### Team members: 



Card Class:
 * Class that represents a single playing card
 
  * rank: ace, 2-10, jack, queen, king (13 possible values)
  * suit: heart, club, diamond, spade
  * value: combination of the rank and suit of a card (5H, KD)
  * faceDown: boolean if card value is showing or the back of the card is showing
  * when a card is constructed a specific .png file is associated to it based on the value of the card (AH = AH.png, FD = backOfCard.png, etc..)
  * each card object has a draw function that returns a JPanel of the Card Image

CardStack:
- holds an arrayList of card objects

- each stack object has a draw function that returns a JPanel of the stack of card images with the stack name listed below (player_1, discard_pile_1, etc...)
