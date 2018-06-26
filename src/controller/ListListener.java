package controller;

/**
 * code written by Simon Lay
 * Student Number: s3658769
 */

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class ListListener implements ListSelectionListener {
	private JList<String> list;
	private MainFrame frame;
	private GameEngine engine;

	public ListListener(MainFrame frame, JList<String> list, GameEngine engine) {
		this.list = list;
		this.frame = frame;
		this.engine = engine;
	}

	/**
	 * gets the selected player from the Jlist, then reformat it to retrieve the name of the player
	 * which is then gone into a for loop to search for the selected player.
	 * Once the method finds a match, it immediately updates the view to the selected player containing
	 * the player's status and rolls.
	 *
	 * @param listSelectionEvent
	 */

	@Override
	public void valueChanged(ListSelectionEvent listSelectionEvent) {
		if (!listSelectionEvent.getValueIsAdjusting()) {
			final List<String> selectedValuesList = list.getSelectedValuesList();
			/*regular expression source:
			* https://stackoverflow.com/questions/25852961/how-to-remove-brackets-character-in-string-java*/
			for (Player p : engine.getAllPlayers()) {
				if (p.getPlayerName().equals(String.valueOf(selectedValuesList).replaceAll("[\\[\\](){}]", ""))) {
					/*update for this player*/
					if (p.getPoints() >= 0) {
						frame.getStatus().updatePoints(p);
					} else {
						p.setPoints(0);
						frame.getStatus().updatePoints(p);
					}
					frame.getStatus().updatePlayer(p);
					if (p.getRollResult() != null) {
						frame.getStatus().updateDice(p.getRollResult());
					}
					frame.getToolbar().updateBet(p.getBet());
					frame.revalidate();
					break;
				}
			}
		}
	}
}
