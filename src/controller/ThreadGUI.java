package controller;

/**
 * code written by Simon Lay
 * Student Number: s3658769
 */

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

public class ThreadGUI extends Thread {
	private MainFrame frame;
	private GameEngine engine;
	/**
	 * delays initialized here
	 */
	final private int initialDelay = 1;
	final private int finalDelay = 100;
	final private int IncrementDelay = 20;

	public ThreadGUI(MainFrame frame, GameEngine engine) {
		this.frame = frame;
		this.engine = engine;
	}

	/**
	 * A thread made to run the heavy calculations the game engine undergoes. It also updates
	 * the player UI according to which player has been selected but the main heavy calculations are done
	 * in the GameEngineCallBackGUI. I've also implemented a sleep thread so then the user will be able to see
	 * the dice roll value before it vanishes for the next player's roll. The program has been implemented as when the
	 * roll button is pressed players cannot roll or place a bet until the house has finished rolling against them
	 * dice values are set to 0 to reset the JPanel with a default screen.
	 */
	@Override
	public void run() {
		/*disable the buttons to prevent multiple rolls*/
		frame.getToolbar().getRollButton().setEnabled(false);
		frame.getToolbar().getBetButton().setEnabled(false);
		/*for loop to iterate over the collection players*/
		for (Player player : engine.getAllPlayers()) {
			if (player.getBet() != 0 && player.getPoints() != 0) {
				frame.getStatus().updatePlayerTurn(player);
				engine.rollPlayer(player, initialDelay, finalDelay, IncrementDelay);
				/*delays to enable user to view the dice result*/
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				frame.getStatus().updateHouseTurn();
				engine.rollHouse(initialDelay, finalDelay, IncrementDelay);
				/*if the player the user is looking at is the same as the player that's rolling
				* if it is then points are updated so the user can view its stats*/
				if (frame.getStatus().getCurrPlayer() == player) {
					frame.getStatus().updatePoints(player);
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				updateSplitPane(player);
			}
			/*set player's bet to 0*/
			player.placeBet(0);
			frame.getToolbar().updateBet(player.getBet());
		}
		/*reset's frame back to default screen and enables buttons to allow the user to roll again
		* after the user has placed new bets on each player*/
		frame.getGrid().setDiceOne(0);
		frame.getGrid().setDiceTwo(0);
		frame.getStatus().resetTurn();
		frame.getToolbar().getBetButton().setEnabled(true);
		frame.getToolbar().getRollButton().setEnabled(true);
	}

	/**
	 * updates the game log to present to the user what the result for each player roll is
	 * The old information log is appended to the end of the gameLog so the most updated
	 * version is readable. This is to avoid constant scrolling to find the most recent roll
	 * @param player ,player that's currently rolling
	 */
	private void updateSplitPane(Player player) {
		int playerSum = player.getRollResult().getDice1() + player.getRollResult().getDice2();
		String oldLogger = frame.getSplitPane().getGameLog().getText();
		frame.getSplitPane().getGameLog().setText("player " + player.getPlayerName()
				+ " rolled a total value of: " + playerSum
				+ "!\nHouse has rolled a total value of: " + frame.getStatus().getHouseDiceTotal());
		if (frame.getStatus().getHouseDiceTotal() > playerSum) {
			frame.getSplitPane().getGameLog().setText(frame.getSplitPane().getGameLog().getText()
					+ "!\nHouse won against player " + player.getPlayerName() + "!\n");
		} else if (frame.getStatus().getHouseDiceTotal() < playerSum) {
			frame.getSplitPane().getGameLog().setText(frame.getSplitPane().getGameLog().getText()
					+ "!\nPlayer " + player.getPlayerName() + " won against House!\n");
		} else {
			frame.getSplitPane().getGameLog().setText(frame.getSplitPane().getGameLog().getText()
					+ "!\nHouse and " + player.getPlayerName() + " had a draw!\n");
		}
		frame.getSplitPane().getGameLog().setText(frame.getSplitPane().getGameLog().getText()
				+ "Player " + player.getPlayerName() + " score: " + player.getPoints() + "\n\n" + oldLogger);
	}
}
