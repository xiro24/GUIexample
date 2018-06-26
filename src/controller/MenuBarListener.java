package controller;

/**
 * code written by Simon Lay
 * Student Number: s3658769
 */

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.DialogError;
import view.MainFrame;
import view.PlayerDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

import static javax.swing.JOptionPane.CLOSED_OPTION;

public class MenuBarListener implements ActionListener {

	private MainFrame frame;
	private Player player;
	private GameEngine engine;
	private PlayerDialog add_player;
	private int i = 1;

	public MenuBarListener(MainFrame frame, GameEngine engine) {
		this.frame = frame;
		this.engine = engine;
	}

	/**
	 * Here the actionEvent is used with actionCommand to enable the usage of only one listener.
	 * The menubar listener allows the addition of more players and checks if the player added obeys the rules
	 * of what a player should have. Here are also some instructions for the user who wants to play
	 * the game
	 *
	 * @param actionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getActionCommand().equals("add players")) {
			if (!createPlayer()) {
				return;
			} else if (engine.getAllPlayers().size() == 0) {
				engine.addPlayer(player);
				frame.getSplitPane().updateList(player);
			/*if it's the first player update*/
				frame.getStatus().updatePlayer(player);
			} else {
				engine.addPlayer(player);
				frame.getSplitPane().updateList(player);
			}
			frame.revalidate();
		} else if (actionEvent.getActionCommand().equals("instructions")) {
			new JOptionPane().showConfirmDialog(frame,
					"To play the game you first need to add the players\n"
							+ "You can add a player by clicking the add player button from the drop-down.\n"
							+ "MenuBar. You are then required to place a bet from the JToolBar located\n"
							+ "at the bottom middle of the program. Once you have completed those processes,\n"
							+ "you are then able to Roll the Dice. If you want to set a bet for a specific player,\n"
							+ "select them from the player list on the left hand side of the program then enter your bet\n"
							+ "You cannot:\n"
							+ "\t-roll without placing a bet (bets are reset every round)\n"
							+ "\t-place a negative value in either bets or points\n"
							+ "\t-insert anything other than alphabetical letters when adding a player name\n"
							+ "\t-roll with a bet of 0 (you will be skipped/ignored)\n"
							+ "Note: That the roll dice button rolls all the players that have placed a bet\n"
							+ "and the house rolls against all the players individually", "How to play", CLOSED_OPTION);
		} else if (actionEvent.getActionCommand().equals("exit")) {
			int returnValue = JOptionPane.showConfirmDialog(frame, "Exit Application?", "Confirm Dialog", JOptionPane.YES_NO_OPTION);
			System.out.println(returnValue == JOptionPane.YES_OPTION ? "YES" : "NO");
			if (returnValue == JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}

	/**
	 * Asks for a valid input, if an incorrect response that the game engine can't handle is inputted
	 * The method returns false and gives an appropriate error message, else returns true
	 * and continues to add the player
	 *
	 * @return
	 */
	public boolean createPlayer() {
		int playerInitPoints;
		DialogError errorCaller = new DialogError();
		String playerID = String.valueOf(i);
		i++;
		add_player = new PlayerDialog(frame).addingPlayerDialog();
		if (add_player.getName().equals("") || add_player.getPoints().equals("")) {
			errorCaller.errorMessage(frame, "A text area was left blank! Please fill this in again");
			return false;
		} else if (!add_player.getPoints().matches(".*\\d+.*")) {
			errorCaller.errorMessage(frame, "Points should only contain integers!");
			return false;
		} else if (!add_player.getName().matches("[a-zA-Z]+\\.?")) {
			errorCaller.errorMessage(frame, "Name should only contain Alphabets");
			return false;
		}
		for (Player player : engine.getAllPlayers()) {
			if (add_player.getName().equals(player.getPlayerName())) {
				errorCaller.errorMessage(frame, "Player name already exists, please retry");
				return false;
			}
		}
		if (add_player.getName().length() > 10) {
			errorCaller.errorMessage(frame, "Player name is too long!\n it must be 10 or less characters");
			return false;
		}
		if (add_player.getPoints().length() > 8) {
			errorCaller.errorMessage(frame, "bet is too large, please bet below 100,000,000");
			return false;
		}
		playerInitPoints = Integer.valueOf(add_player.getPoints());
		if (playerInitPoints < 0) {
			errorCaller.errorMessage(frame, "Initial Points cannot be negative!");
			return false;
		}
		player = new SimplePlayer(playerID, add_player.getName(), playerInitPoints);
		return true;
	}
}
