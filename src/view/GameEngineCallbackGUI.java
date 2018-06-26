package view;

/**
 * code written by Simon Lay
 * Student Number: s3658769
 */

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import javax.swing.*;

/**
 * the gameEngineCallbackGUI which updates the GUI with the parameters provided
 * which is executed from the UI thread
 */
public class GameEngineCallbackGUI implements GameEngineCallback {
	private MainFrame frame;

	public GameEngineCallbackGUI(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.getGrid().setDiceOne(dicePair.getDice1());
				frame.getGrid().setDiceTwo(dicePair.getDice2());
				frame.getStatus().updateDice(dicePair);
				frame.revalidate();
			}
		});
	}

	@Override
	public void result(Player player, DicePair result, GameEngine gameEngine) {
		frame.getGrid().setDiceOne(result.getDice1());
		frame.getGrid().setDiceTwo(result.getDice2());
		frame.getStatus().updateDice(result);
		frame.revalidate();
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.getGrid().setDiceOne(dicePair.getDice1());
				frame.getGrid().setDiceTwo(dicePair.getDice2());
				frame.getStatus().updateHouse(dicePair);
				frame.revalidate();
			}
		});
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		frame.getGrid().setDiceOne(result.getDice1());
		frame.getGrid().setDiceTwo(result.getDice2());
		frame.getStatus().updateHouse(result);
		frame.revalidate();
	}
}