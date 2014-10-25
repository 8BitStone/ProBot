import javax.swing.SwingUtilities;

public class ProBotGame{
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Core.ProBotGame();
			}
		});
	}

}
