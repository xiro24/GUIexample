package view;

/**
 * code written by Simon Lay
 * Student Number: s3658769
 */

import javax.swing.*;
import java.awt.*;

public class DisplayDice extends JPanel {

	private String diceOne = "zero";
	private String diceTwo = "zero";
	private String[] dice = {"zero", "one", "two", "three", "four", "five", "six"};
	private JLabel cellLabel[] = new JLabel[2];
	final private int diceDimension = 80;

	/**
	 * Displays the dice with the values of diceOne and diceTwo, images are resized to fit in each cell
	 * and is added to a container so then i would be able to position both of them in the center using
	 * GridBagLayout.
	 */
	public DisplayDice() {
		/*gridbag constraints allow me to put it in the middle*/
		setLayout(new GridBagLayout());
		JPanel container = new JPanel();
		cellLabel[0] = new JLabel();
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("images/" + diceOne + ".png").getImage().getScaledInstance(diceDimension, diceDimension, Image.SCALE_DEFAULT));
		cellLabel[0].setIcon(imageIcon);
		cellLabel[0].setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
		container.add(cellLabel[0]);
		cellLabel[1] = new JLabel();
		imageIcon = new ImageIcon(new ImageIcon("images/" + diceTwo + ".png").getImage().getScaledInstance(diceDimension, diceDimension, Image.SCALE_DEFAULT));
		cellLabel[1].setIcon(imageIcon);
		cellLabel[1].setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
		container.add(cellLabel[1]);
		add(container, new GridBagConstraints());
	}

	/**
	 * Sets the dice with the string at index num from the private array dice,
	 * which would update the dices shown to the user
	 *
	 * @param num
	 */
	public void setDiceOne(int num) {
		if (dice[num] != null) {
			this.diceOne = dice[num];
			cellLabel[0].setIcon(new ImageIcon(new ImageIcon("images/" + diceOne + ".png").getImage().getScaledInstance(diceDimension, diceDimension, Image.SCALE_DEFAULT)));
		}
	}

	public void setDiceTwo(int num) {
		if (dice[num] != null) {
			this.diceTwo = dice[num];
			cellLabel[1].setIcon(new ImageIcon(new ImageIcon("images/" + diceTwo + ".png").getImage().getScaledInstance(diceDimension, diceDimension, Image.SCALE_DEFAULT)));
		}
	}
}