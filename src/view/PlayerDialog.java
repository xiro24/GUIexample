package view;

/**
 * code written by Simon Lay
 * Student Number: s3658769
 */

import javax.swing.*;
import java.awt.*;

public class PlayerDialog {
	private JTextField username;
	private JTextField initialPoints;
	private MainFrame frame;

	public PlayerDialog(MainFrame frame) {
		this.frame = frame;
	}

	/**
	 * Dialog that is displayed when the user selects add player from the menu bar.
	 * It receives two strings from the user and assigns them to the private String variables
	 * so then the program is able to use those variables as getters for other classes to use.
	 */
	public PlayerDialog addingPlayerDialog() {
		JPanel container = new JPanel(new BorderLayout(5, 5));
		JPanel section = new JPanel(new GridLayout(0, 1, 2, 2));
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));

		section.add(new JLabel("Player Name", SwingConstants.RIGHT));
		section.add(new JLabel("Points", SwingConstants.RIGHT));
		container.add(section, BorderLayout.WEST);
		username = new JTextField();
		controls.add(username);
		initialPoints = new JTextField();
		initialPoints.getText();
		controls.add(initialPoints);
		container.add(controls, BorderLayout.CENTER);
		JOptionPane.showMessageDialog(
				frame, container, "Add Player", JOptionPane.QUESTION_MESSAGE);
		return this;
	}

	/**
	 * @return , returns the player bet that user has inserted in the dialog
	 */
	public String getPlayerBet() {
		String player_bet = JOptionPane.showInputDialog(frame, "Enter Bet amount", "betting",
				JOptionPane.QUESTION_MESSAGE);
		return player_bet;
	}

	/**
	 * @return , returns the username inserted in the dialog by the user
	 */
	public String getName() {
		return username.getText();
	}

	/**
	 * @return , returns the initialPoints inserted in the dialog by the user
	 */
	public String getPoints() {
		return initialPoints.getText();
	}
}
