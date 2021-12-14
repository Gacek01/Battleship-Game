import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {

	public final int UNIT_SIZE = 50;
	JButton[] ships = new JButton[100];
	JLabel[] labels = new JLabel[10];
	Font mainFont = new Font("Arial", Font.BOLD, 25);

	MainFrame() {

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 10, 0, 0));
		topPanel.setBounds(15, 0, 500, 50);

		for (int i = 0; i < 10; i++) {
			labels[i] = new JLabel(String.valueOf(i + 1));
			labels[i].setFont(mainFont);
			topPanel.add(labels[i]);
		}

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 600, 500, 50);
		bottomPanel.setBackground(Color.BLUE);
		bottomPanel.setOpaque(true);

		JProgressBar progressBar = new JProgressBar(0, 20);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setFont(mainFont);
		progressBar.setPreferredSize(new Dimension(500, 50));
		bottomPanel.add(progressBar);

		JPanel centerPanel = new JPanel();

		centerPanel.setLayout(new GridLayout(10, 10, 0, 0));
		centerPanel.setBounds(0, 50, 500, 500);

		for (int i = 0; i < 100; i++) {

			ships[i] = new JButton(String.valueOf(i + 1));
			ships[i].addActionListener(this);
			centerPanel.add(ships[i]);
		}

		this.setSize(515, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("Battleship");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(topPanel);
		this.add(centerPanel);
		this.add(bottomPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < 100; i++) {
			if (e.getSource() == ships[i]) {
				ships[i].setText("X");
				ships[i].setEnabled(false);
				ships[i].setBackground(Color.lightGray);
				ships[i].setFocusable(false);
				System.out.println("Shot to " + (i + 1));
			}

		}
	}

}
