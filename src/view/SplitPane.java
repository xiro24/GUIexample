package view;

/**
 * code written by Simon Lay
 * Student Number: s3658769
 */

import controller.ListListener;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Collection;

import static com.sun.java.accessibility.util.SwingEventMonitor.addListSelectionListener;

public class SplitPane extends JPanel {
	private DefaultListModel<String> listModel;
	private JTextArea gameLog;
	private MainFrame frame;

	/**
	 * This constructor is doing two processes:
	 * 1. constructing a player JList
	 * 2. constructing a gameLogger
	 * <p>
	 * Both of them are within a container to enable a split pane which displays both the player list
	 * and the game log. The game log show the result to the user for each roll
	 * and the player Jlist displays the current players in the game which enable the user
	 * to switch to them.
	 *
	 * @param frame
	 * @param engine
	 */
	public SplitPane(MainFrame frame, GameEngine engine) {
		this.frame = frame;
		setLayout(new BorderLayout());
		JPanel container = new JPanel();
		listModel = new DefaultListModel<>();
		JPanel logPanel = new JPanel();

		container.setBorder(new TitledBorder("List of Players"));
		container.setLayout(new BorderLayout());

		JList<String> player_list = new JList<>(listModel);
		player_list.setFixedCellWidth(100);
		player_list.setFixedCellHeight(20);
		player_list.addListSelectionListener((new ListListener(frame, player_list, engine)));
		container.add(new JScrollPane(player_list));

		logPanel.setLayout(new BorderLayout());
		logPanel.setBorder(new TitledBorder("Game Log:"));
		gameLog = new JTextArea();
		gameLog.setLineWrap(true);
		gameLog.setWrapStyleWord(true);
		gameLog.setEditable(false);
		logPanel.add(new JScrollPane(gameLog));
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				container, logPanel);
		add(splitPane);
	}

	/**
	 * updates the list with player
	 *
	 * @param player
	 */
	public void updateList(Player player) {
		listModel.addElement(player.toString());
	}

	/**
	 * @return returns the gameLog used to update the logger
	 */
	public JTextArea getGameLog() {
		return gameLog;
	}
}
