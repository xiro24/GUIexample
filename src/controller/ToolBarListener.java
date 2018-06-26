package controller;

/**
 * code written by Simon Lay
 * Student Number: s3658769
 */

import model.interfaces.GameEngine;
import view.PlayerDialog;
import view.DialogError;
import view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarListener implements ActionListener {
	private MainFrame frame;
	private GameEngine engine;

	public ToolBarListener(MainFrame frame, GameEngine engine) {
		this.frame = frame;
		this.engine = engine;
	}

	/**
	 * checks if the accepted bet is valid and that a player exists before allowing the user to roll or place a bet.
	 * The roll dice button calls the thread which then calls the game engine to update the gui. The place bet button
	 * is used to communicate with the engine to update the current player with the specified bet. Errors are given
	 * if user tries to violate the game rules
	 *
	 * @param actionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (frame.getStatus().getCurrPlayer() != null) {
			if (actionEvent.getActionCommand().equals("PLACE BET")) {
				placeBet();
			} else if (actionEvent.getActionCommand().equals("ROLL DICE")) {
				if (frame.getStatus().getCurrPlayer().getBet() != 0) {
					rollDice();
				} else {
					new DialogError().errorMessage(frame, "You must set a bet greater than 0 to roll!");
				}
			}
		} else {
			new DialogError().errorMessage(frame, "There is no current Player");
		}
	}

	/**
	 * error checks if bet placed is a valid bet if not, it returns an error message
	 */
	private void placeBet() {
		String player_bet = new PlayerDialog(frame).getPlayerBet();
				/*updates bet*/
		if (!player_bet.matches(".*\\d+.*")) {
			new DialogError().errorMessage(frame, "bet cannot have Strings");
			return;
		} else if (player_bet.equals("")) {
			new DialogError().errorMessage(frame, "bet cannot be empty");
			return;
		} else if (Integer.valueOf(player_bet) < 0) {
			new DialogError().errorMessage(frame, "bet cannot be negative");
			return;
		}
		if (!engine.placeBet(frame.getStatus().getCurrPlayer(), Integer.valueOf(player_bet))) {
			new DialogError().errorMessage(frame, "Cannot Place bet!");
		} else {
			frame.getToolbar().updateBet(Integer.valueOf(player_bet));
			frame.revalidate();
		}
	}

	/**
	 * checks if the bet is large enough before executing the thread to roll the dice for both player
	 * and the house
	 */
	private void rollDice() {
		if (frame.getToolbar().getBet() > frame.getStatus().getCurrPlayer().getPoints()) {
			new DialogError().errorMessage(frame, "Bet cannot be greater than Points, please insert a new bet!");
		} else {
			if (frame.getStatus().getCurrPlayer() != null) {
				new ThreadGUI(frame, engine).start();
			} else {
				new DialogError().errorMessage(frame, "Player is null");
			}
		}
	}
}
