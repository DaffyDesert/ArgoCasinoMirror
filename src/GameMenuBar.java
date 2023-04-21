import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class GameMenuBar extends JMenuBar{
	private JMenu help;
	private JMenu menu;
	private MainMenu mainmenu;
	private JFrame dialogFrame;
	
	public GameMenuBar(MainMenu mainmenu) {
		this.mainmenu = mainmenu;
		dialogFrame = new JFrame();
		buildMenuMenu();
		buildHelpMenu();
	}
	
	public void buildMenuMenu() {
		menu = new JMenu("Menu");
		JMenuItem mainMenu = new JMenuItem("Navigate to Main Menu");
		JMenuItem gameSelection = new JMenuItem("Navigate to Game Selection Menu");
		JMenuItem userMenu = new JMenuItem("Navigate to User Menu");
		JMenuItem exit = new JMenuItem("Exit Game");
		
		menu.add(mainMenu);
		menu.add(gameSelection);
		menu.add(userMenu);
		menu.add(exit);
		this.add(menu);
		
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayMainMenu();
			}
		});
		
		gameSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayGameSelect();
			}
		});
		
		userMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayUserMenu();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitGame();
			}
		});
	}
	
	public void buildHelpMenu() {
		help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About Program");
		JMenuItem playAGame = new JMenuItem("Playing a Game");
		JMenu gameRules = new JMenu("Game Rules");
		JMenuItem warRules = new JMenuItem("Rules of War");
		JMenuItem howToPlayWar = new JMenuItem("How to Play War");
		JMenuItem blackJackRules = new JMenuItem("Rules of BlackJack");
		JMenuItem howToPlayBlackJack = new JMenuItem("How to Play BlackJack");
		JMenuItem solitaireRules = new JMenuItem("Rules of Solitaire");
		JMenuItem howToPlaySolitaire = new JMenuItem("How to Play Solitaire");
		JMenuItem userMenu = new JMenuItem("User Profile Features");
		
		gameRules.add(warRules);
		gameRules.add(howToPlayWar);
		gameRules.add(blackJackRules);
		gameRules.add(howToPlayBlackJack);
		gameRules.add(solitaireRules);
		gameRules.add(howToPlaySolitaire);
		
		help.add(about);
		help.add(playAGame);
		help.add(gameRules);
		help.add(userMenu);
		this.add(help);
		
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAbout();
			}
		});
		
		playAGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayPlayAGame();
			}
		});
		
		warRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayWarRules();
			}
		});
		
		howToPlayWar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayHTPWar();
			}
		});
		
		userMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayUserMenuHelp();
			}
		});
		
		blackJackRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayBlackJackRules();
			}
		});
		
		howToPlayBlackJack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayHTPBlackJack();
			}
		});
		
		solitaireRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displaySolitaireRules();
			}
		});
		
		howToPlaySolitaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayHTPSolitaire();
			}
		});
	}
	
	public void displayMainMenu() {
		mainmenu.displayMainMenu();
	}
	
	public void displayGameSelect() {
		mainmenu.displayGameSelect();
	}
	
	public void displayUserMenu() {
		mainmenu.displayUserMenu();
	}
	
	public void exitGame() {
		System.exit(0);
	}
	
	public void displayAbout() {
		JOptionPane.showMessageDialog(dialogFrame,"Hello and Welcome to Argo Casino!\n\n"
				+ "In this game, you can play a variety of 3 different card games. These games include War, BlackJack, and Solitaire.\n"
				+ "This game was developed in Java using Eclipse IDE. Game Rules are based on official Bicycle rules\n"
				+ "We do not claim ownership of any of the games included in this program.\n\n"
				+ "This game was created by RoeMello and Co. Studios. Developers include: \n"
				+ "Daniel Miller, Daniel Padget, RoeMello Holliday, Zachary Wilson, Alexander DeAngelis", "About the Program", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void displayPlayAGame() {
		JOptionPane.showMessageDialog(dialogFrame,"To play a game, navigate to the Main Menu and select \"Start Game.\"\n"
				+ "The games available are War, BlackJack, and Solitaire.", "Playing a Game", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void displayWarRules() {
		JOptionPane.showMessageDialog(dialogFrame,"The rules of War are simple:\n"
				+ "Two players shuffle the 52 card deck and split it 50/50, 26 cards vs. 26 cards.\n"
				+ "Each turn, both players take a card off the top of their deck and place it in the\n"
				+ "middle. Whoever's card has the highest value wins both cards, which are placed at\n"
				+ "the bottom of that player's deck. If the players' cards' values are equal, the game\n"
				+ "\"Goes to War.\" Now, both players place the top card of their deck face-down, then\n"
				+ "draw a second card and place it face-up. Whoever wins this conflict gets to take all 6\n"
				+ "cards. If it's a tie here, the game \"Goes to War\" as many times as it takes until a\n"
				+ "winner is found. Each time \"War\" happens, the winning pile grows.", "Rules of War", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void displayHTPWar() {
		JOptionPane.showMessageDialog(dialogFrame,"To play War, simply click on the deck titled \"Player Deck.\" This will move your first card. "
				+ "Then, keep clicking every turn to draw cards. See \"Rules of War\" in the Help Menu for more details on what's happening.", "How to Play War", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void displayBlackJackRules() {
		JOptionPane.showMessageDialog(dialogFrame,"The rules of BlackJack are simple:\n"
				+ "2 players are dealt two cards at random by a Dealer. The goal of the game is to get the values of the cards in your hand to"
				+ "sum to 21 or get as close as possible without going over. If you go over, you \"Bust\" and lose.\n"
				+ "To raise your hand's value, you may choose to \"Hit,\" which prompts the dealer to deal you a new card. "
				+ "Otherwise, you may choose to \"Hold\" and no card will be dealt.\n"
				+ "If all players still in the game choose to Hold, they will be prompted to reveal their hands. Whoever is closest to 21 wins.", "Rules of BlackJack", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void displayHTPBlackJack() {
		JOptionPane.showMessageDialog(dialogFrame,"In BlackJack, dealing cards will be handled by the game. Your only concern is to choose whether to \"Hit\" or \"Hold.\"\n"
				+ "The Hit option will deal you a new card, and the Hold option will move the game into the reveal phase. "
				+ "See \"Rules of BlackJack\" in the Help Menu for more details on what's happening.", "How to Play BlackJack", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void displaySolitaireRules() {
		JOptionPane.showMessageDialog(dialogFrame,"The rules of Solitaire are a bit more complicated. First, to set-up the board:\n"
				+ "Shuffle the deck, then draw 28 of the cards and arrange them in 7 columns face-down. The first column will have 1 card, then the next 2, etc. Then, "
				+ "flip the card at the bottom of each column face-up. Then, set aside space for 6 stacks of cards. 4 of these stacks will be for suits, one will be for "
				+ "the remaining deck, and one will be for the discard pile.\n\n"
				+ "Playing the game:\n"
				+ "Your goal in Solitaire is to sort all the cards into the 4 suit piles, starting with the Aces and ending with the Kings, in order. To do this, "
				+ "you'll need to arrange the cards on the board in the 7 columns and in your deck. You may move any car, but there are some restrictions:\n"
				+ "-You can only place a card on another card.\n"
				+ "-Kings may be placed on empty columns\n"
				+ "-Aces may be placed in empty suit piles\n"
				+ "-When placing cards in the 7 columns, the card you place must be the opposite color from the card you place it on (Red or Black). Suit doesn't matter.\n"
				+ "-When placing cards in the 7 columns, the card you place must be one less in value than the card you place it on (Aces are one, Jacks 11, Queens 12, Kings 13).\n"
				+ "-When placing cards in the suit piles, the card you place must be the same suit as the card you place it on.\n"
				+ "-When placing cards in the suit piles, the card you place must be one greater in value than the card you place it on.\n"
				+ "You lose the game when you run out of valid moves or give up. No penalty for losing, just try again!", "Rules of Solitaire", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void displayHTPSolitaire() {
		JOptionPane.showMessageDialog(dialogFrame,"ERROR: Solitaire not implemented at time of writing. Please implement Solitaire and then describe its gameplay here.", "How to Play Solitaire", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void displayUserMenuHelp() {
		JOptionPane.showMessageDialog(dialogFrame,"The User Menu gives you access to profile creation and maintenance.\n"
				+ "Profiles allow you to save and track your game data. Here are a list of User Menu functions:\n"
				+ "-Create a new Profile with the \"Add New User\" Button.\n"
				+ "-Copy an existing Profile by selecting a Profile in the list and clicking \"Copy Selected User.\"\n"
				+ "-Delete a Profile by selecting it and clicking \"Delete Selected User\"\n"
				+ "-Login to a Profile by selecting it and clicking \"Select User\"\n"
				+ "-View a Profile's data by selecting it and clicking \"View Selected User\"\n"
				+ "-While viewing a profile's data, if you are an Administrator on this system, you can edit that profile's administrative permissions.\n"
				+ "-While viewing a profile's data, if you are an Administrator or currently logged into the profile, you can change the profile's username.\n"
				+ "-The first profile created on the system will be an Administrator.\n"
				+ "-Select \"Clear All Data\" to erase all game data.", "User Menu Functions", JOptionPane.PLAIN_MESSAGE);
	}
}
