package view;

/**
 * code written by Simon Lay
 * Student Number: s3658769
 */

import controller.*;
import model.interfaces.GameEngine;

import javax.swing.*;

public class MenuBar extends JMenuBar {

	/**
	 * Adds items to the MenuBar which allows user to add players, view instructions and exit the program
	 * each listener requires the passed in frame from the constructor and the gameEngine
	 *
	 * @param frame
	 * @param engine
	 */
	public MenuBar(MainFrame frame, GameEngine engine) {
		JMenu Menu = new JMenu("Options");
		this.add(Menu);
		JMenuItem add_player = new JMenuItem("add players");
		add_player.setActionCommand("add players");
		add_player.addActionListener(new MenuBarListener(frame, engine));
		JMenuItem instructions = new JMenuItem("instructions");
		instructions.setActionCommand("instructions");
		instructions.addActionListener(new MenuBarListener(frame, engine));
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setActionCommand("exit");
		exitItem.addActionListener(new MenuBarListener(frame, engine));

		Menu.add(add_player);
		Menu.add(instructions);
		Menu.add(exitItem);
	}
}
