package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import view.DialogError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameEngineImpl implements GameEngine {

	/*used to store each player in the game*/
	private List<Player> players = new ArrayList<>();
	/*used to store the game engine call backs*/
	private List<GameEngineCallback> GECbList = new ArrayList<>();

	@Override
	public boolean placeBet(Player player, int bet) {
		if (player != null && player.getPoints() >= bet) {
			player.placeBet(bet);
			return true;
		}
		return false;
	}

	/*private method to roll dice*/
	private int roller(int D) {
		D = (int) (Math.random() * 6) + 1;
		return D;
	}

	/*removes player*/
	/*public boolean kickplayer(Player player){
	    if (player.getBet() ==0 || player.getPoints() < player.getBet()) {
            removePlayer(player);
            return true;
        }
        return false;
    }*/
	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
        /*if (kickplayer(player) == true) {
            return;
        }*/
		int D1 = 0;
		int D2 = 0;
        /*numface set to 6 as specification*/
		int numface = 6;
        /*dicepair is created with a new roll and then gets put into the dice condition method*/
		DicePair dicePair = new DicePairImpl(roller(D1), roller(D2), numface);
		for (GameEngineCallback obj : GECbList) {
			DiceCondition(initialDelay, finalDelay, player, D1, D2, numface, obj, delayIncrement);
            /*sets the player's toll result and is assed through the result parameters*/
			player.setRollResult(dicePair);
			obj.result(player, player.getRollResult(), this);
		}
        /*adds the game engine call back to the arraylist as specified by the specification*/
	}

	/*private method used to run the dice condition, it is modified to operate with both roll player
	* adn rollhouse. The way it distinguishes itself is whether the player is null or not
	* */
	private void DiceCondition(int initialDelay, int finalDelay, Player player, int D1, int D2, int numface, GameEngineCallback GECb, int delayIncrement) {
        /*increase the delay on that condition a new dice is shown on the dice*/
		for (; initialDelay < finalDelay; initialDelay += delayIncrement) {
            /*req3*/
            /*you need to implement sleep*/
			if (player != null) {
				GECb.intermediateResult(player, new DicePairImpl(roller(D1), roller(D2), numface), this);
			} else {
				GECb.intermediateHouseResult(new DicePairImpl(roller(D1), roller(D2), numface), this);
			}
			try {
				Thread.sleep(initialDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		int D1 = 0;
		int D2 = 0;
        /*numface set to 6 as specification*/
		int numface = 6;
		DicePair dicePair = new DicePairImpl(roller(D1), roller(D2), numface);
		for (GameEngineCallback obj : GECbList) {
			DiceCondition(initialDelay, finalDelay, null, D1, D2, numface, obj, delayIncrement);
            /*add players to new gameEngine object so then i can edit their points
            * this for loop also computes the bets and results/points for each player*/
			obj.houseResult(dicePair, this);
		}
		calculateScore(dicePair);
	}

	public void calculateScore(DicePair dicePair) {
		int houseSum = dicePair.getDice1() + dicePair.getDice2();
		for (Player player : players) {
			if (player.getRollResult() != null) {
				int playerSum = player.getRollResult().getDice2() + player.getRollResult().getDice1();
				if (playerSum > houseSum) {
					player.setPoints(player.getPoints() + player.getBet());
				} else if (playerSum < houseSum) {
					player.setPoints(player.getPoints() - player.getBet());
				}
			}
		}
	}

	@Override
	public void addPlayer(Player player) {
        /*removes the player if there is a duplicate and re-adds them to the game*/
		for (Player p : players) {
			if (p.getPlayerName().equals(player.getPlayerName())) {
				players.remove(p);
			}
		}
		players.add(player);
	}

	@Override
	public Player getPlayer(String id) {
        /*create a loop to check the string id*/
		for (int i = 0; i < players.size(); i++) {
			if (id.equals(players.get(i).getPlayerId().toString())) {
				return players.get(i);
			}
		}
		System.out.println("There is no such id, returned null");
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		return players.remove(player);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		GECbList.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return GECbList.remove(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers() {
        /*returns an unmodifiable collection of players in the game*/
		Collection playersCopy = new ArrayList(players);
		return playersCopy;
	}
}
