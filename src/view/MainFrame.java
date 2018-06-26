package view;

/**
 * code written by Simon Lay
 * Student Number: s3658769
 */

import model.interfaces.GameEngine;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

	private JTextArea text;
	private Status status = new Status();
	private SplitPane SplitPane;
	private ToolBar toolbar;
	private DisplayDice grid;
	/**
	 * Sets up the frame for the user to see, adds the components to display game requirements
	 * adds them with specified layouts. Dimensions are set and has a minimal size to avoid squishing components
	 * Components are also resizable and max screen can also be avhieved when pressed the enlarge button
	 * (top right corner)
	 *
	 * @param engine
	 */
	public MainFrame(GameEngine engine) {
		setTitle("SADI Assignment 2");
		setBounds(250, 250, 600, 500);
		setJMenuBar(new MenuBar(this, engine));
		toolbar = new ToolBar(this, engine);
		grid = new DisplayDice();
		SplitPane = new SplitPane(this, engine);
		add(status, BorderLayout.EAST);
		add(SplitPane, BorderLayout.WEST);
		add(grid);
		add(toolbar, BorderLayout.PAGE_END);
		setMinimumSize(new Dimension(500, 400));
		setMaximizedBounds(new Rectangle(0, 0, 1000, 660));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * @return returns the Status object so listeners can get the currently displayed status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @return returns the SplitPane object so listeners can update the required fields (e.g: game log, jList)
	 */
	public SplitPane getSplitPane() {
		return SplitPane;
	}

	/**
	 * @return returns the Toolbar object so listeners can update current bet and execute roll methods in threads
	 */
	public ToolBar getToolbar() {
		return toolbar;
	}

	/**
	 * @return returns the Grid object so program can update the dice rolls
	 */
	public DisplayDice getGrid() {
		return grid;
	}
}
