package view;

/**
 * code written by Simon Lay
 * Student Number: s3658769
 */

import model.interfaces.DicePair;
import model.interfaces.Player;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Status extends JPanel {
	public JLabel[] statusTable = new JLabel[5];
	private Player player;
	private Player player_turn;
	private int houseDice;

	/**
	 * Sets up the the status on the right side of the program. Default values are displayed until player
	 * is added by the user.
	 */
	public Status() {
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		setLayout(new GridLayout(5, 1));
		statusTable[0] = new JLabel("Please Roll the dice");
		statusTable[0].setBorder(new LineBorder(Color.BLACK));
		statusTable[1] = new JLabel("Dice Rolled: 0");
		statusTable[1].setBorder(new LineBorder(Color.BLACK));
		statusTable[2] = new JLabel("House Rolled: 0");
		statusTable[2].setBorder(new LineBorder(Color.BLACK));
		statusTable[3] = new JLabel("Selected Player: null");
		statusTable[3].setBorder(new LineBorder(Color.BLACK));
		statusTable[4] = new JLabel("Selected Player Points: 0");
		statusTable[4].setBorder(new LineBorder(Color.BLACK));
		for (int i = 0; i < statusTable.length; i++) {
			add(statusTable[i]);
		}
	}

	/**
	 * updates the player status according to the player passed though it's
	 * parameters in respect to the line wrapping "<html><div WIDTH=%d>%s</div><html>"
	 * which was sourced form this website:
	 * https://stackoverflow.com/questions/2420742/make-a-jlabel-wrap-its-text-by-setting-a-max-width
	 *
	 * @param player
	 */
	public void updatePlayer(Player player) {
		this.player = player;
		statusTable[3].setText(String.format("<html><div WIDTH=%d>%s</div><html>", 100, "Selected Player: "
				+ player.getPlayerName()));
		statusTable[4].setText(String.format("<html><div WIDTH=%d>%s</div><html>", 100, "Selected Player Points: "
				+ player.getPoints()));
	}

	/**
	 * updates the player turn to the player passed through the parameter
	 *
	 * @param player
	 */
	public void updatePlayerTurn(Player player) {
		this.player_turn = player;
		statusTable[0].setText(String.format("<html><div WIDTH=%d>%s</div><html>", 10, "Turn: "
				+ player.getPlayerName()));
	}

	/**
	 * changes the turn to the house
	 */
	public void updateHouseTurn() {
		this.player_turn = player;
		statusTable[0].setText("Turn: \nHouse");
	}

	/**
	 * if no players are left to roll, this method is called to prompt the user for a roll
	 */
	public void resetTurn() {
		statusTable[0].setText("Please Roll the dice");
	}

	/**
	 * returns the player who is currently shown as rolling
	 *
	 * @return
	 */
	public Player getPlayer() {
		return player_turn;
	}

	/**
	 * updates the status points foe the player that's passed through the parameters
	 *
	 * @param player
	 */
	public void updatePoints(Player player) {
		statusTable[4].setText(String.format("<html><div WIDTH=%d>%s</div><html>", 100, "Selected Player Points: "
				+ player.getPoints()));
	}

	/**
	 * updates the status for the Dice Value, which gives the sum of the two dices rolled by the player
	 *
	 * @param Dice
	 */
	public void updateDice(DicePair Dice) {
		int sum = Dice.getDice2() + Dice.getDice1();
		statusTable[1].setText("Dice Rolled: " + sum);
	}

	/**
	 * resets the Dice roll value so it doesn't confuse the user, since there is no one rolling.
	 */
	public void resetRolls() {
		statusTable[1].setText("Dice Rolled: " + 0);
		statusTable[2].setText("House Rolled: " + 0);
	}

	/**
	 * updates the House roll from the dicepair that's passed into the parameter
	 *
	 * @param Dice
	 */
	public void updateHouse(DicePair Dice) {
		houseDice = Dice.getDice1() + Dice.getDice2();
		statusTable[2].setText("House Roll: " + houseDice);
	}

	/**
	 * @return returns the current player that's being shown to the user
	 */
	public Player getCurrPlayer() {
		return player;
	}

	/**
	 * get's the house's total roll value
	 *
	 * @return
	 */
	public int getHouseDiceTotal() {
		return houseDice;
	}
}
