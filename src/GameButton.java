import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class GameButton extends JButton {
	
	public GameButton(String text) {
		this.setText(text);
		this.setFont(new Font("Arial", Font.BOLD, MainFrame.UNIT_SIZE/5));
		this.setBackground(new Color(102, 204, 255));
		this.setForeground(Color.BLACK);
	}
}
