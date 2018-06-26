package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

public class SimplePlayer implements Player {

	private String playerID;
	private String playerName;
	private int Points;
	private int bet = 0;
	private DicePair rollResult;


	public SimplePlayer(String playerID, String playerName, int initPoints) {
		this.playerID = playerID;
		this.playerName = playerName;
		this.Points = initPoints;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		return Points;
	}

	@Override
	public void setPoints(int points) {
		this.Points = points;
	}

	@Override
	public String getPlayerId() {
		return playerID;
	}

	@Override
	public boolean placeBet(int bet) {
		if (bet >= 0 && bet <= Points) {
			this.bet = bet;
			return true;
		}
		return false;
	}

	@Override
	public int getBet() {
		return bet;
	}

	@Override
	public DicePair getRollResult() {
		return rollResult;
	}

	@Override
	public void setRollResult(DicePair rollResult) {
		this.rollResult = rollResult;
	}

	@Override
	public String toString() {
		return getPlayerName();
	}
}
