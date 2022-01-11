import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainLabel extends JLabel {

	Font labelFont = new Font("Arial", Font.BOLD, 25);

	MainLabel(String text) {

		this.setText(text);
		this.setFont(labelFont);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBackground(Color.black);
		this.setForeground(Color.white);
		this.setOpaque(true);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setVerticalAlignment(SwingConstants.CENTER);
	}
}
