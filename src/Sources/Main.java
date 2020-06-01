package Sources;

import java.awt.EventQueue;
import javax.swing.UIManager;


public class Main {
	public static String User;
	private static UIManager.LookAndFeelInfo[] lookFeel = null;
	
	
	public static void main(String[] args) throws Exception {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lookFeel = UIManager.getInstalledLookAndFeels();
					UIManager.setLookAndFeel(lookFeel[1].getClassName());
					Wallet window = new Wallet();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
