import javax.swing.SwingUtilities;

import Core.ProBotGame;

public class ProBot{
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ProBotGame();
			}
		});
	}

}
