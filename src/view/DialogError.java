package view;

/**
 * code written by Simon Lay
 * Student Number: s3658769
 */

import javax.swing.*;

public class DialogError extends JOptionPane {

	/**
	 * displays a dialog box with the error message from the parameter
	 *
	 * @param frame
	 * @param Message
	 */
	public void errorMessage(MainFrame frame, String Message) {
		showConfirmDialog(frame, Message, "Error Message", CLOSED_OPTION);
	}
}
