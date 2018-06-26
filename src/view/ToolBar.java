package view;

/**
 * code written by Simon Lay
 * Student Number: s3658769
 */

import controller.ToolBarListener;
import model.interfaces.GameEngine;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {

	private JLabel currentBetDisplay;
	private int bet = 0;
	private JButton BetButton = new JButton();
	private JButton RollButton = new JButton();

	/**
	 * Creates the buttons for place bet and roll dice, passing in the frame and gameEngine
	 *
	 * @param frame
	 * @param engine
	 */
	public ToolBar(MainFrame frame, GameEngine engine) {
		//create tool bar
		// add all buttons to tool bar
		JToolBar container = new JToolBar();
		BetButton = new JButton("PLACE BET");
		BetButton.setActionCommand("PLACE BET");
		BetButton.addActionListener(new ToolBarListener(frame, engine));
		RollButton = new JButton("ROLL DICE");
		RollButton.setActionCommand("ROLL DICE");
		RollButton.addActionListener(new ToolBarListener(frame, engine));
		currentBetDisplay = new JLabel("Current bet: " + bet);
		add(currentBetDisplay);
		container.add(BetButton);
		container.add(RollButton);
		container.setLayout(new FlowLayout(FlowLayout.CENTER));
		container.setFloatable(false);
		add(container);
		setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 5));
	}

	/**
	 * updates the bet on the tool bar to the bet passed though the parameter
	 *
	 * @param bet
	 */
	public void updateBet(int bet) {
		this.bet = bet;
		currentBetDisplay.setText("Current bet: " + bet);
	}

	/**
	 * @return returns a reference to RollButton to enable operations such as enabling and disabling the button
	 */
	public JButton getRollButton() {
		return RollButton;
	}

	/**
	 * @return returns a reference to BetButton to enable operations such as enabling and disabling the button
	 */
	public JButton getBetButton() {
		return BetButton;
	}

	/**
	 * @return returns the bet currently displayed to the user
	 */
	public int getBet() {
		return bet;
	}
}
