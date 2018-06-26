package client;

import model.GameEngineCallbackImpl;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;
import view.MainFrame;

import javax.swing.*;

public class Runner {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final GameEngine gameEngine = new GameEngineImpl();
				MainFrame frame = new MainFrame(gameEngine);
				gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
				gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(frame));
			}
		});
	}
}
