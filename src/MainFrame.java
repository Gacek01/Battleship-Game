import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {

	public final int UNIT_SIZE = 50;
	JButton[] ships = new JButton[100];
	JLabel[] labels = new JLabel[10];
	boolean[][] board = new boolean[10][10];
	Font mainFont = new Font("Arial", Font.BOLD, 25);
	JProgressBar progressBar = new JProgressBar(0, 20);
	byte shipsSunk = 0;

	MainFrame() {

		// Test data
		board[0][0] = true;
		board[9][9] = true;

		/* ------------Top axis setup-------------- */
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 10, 0, 0));
		topPanel.setBounds(15, 0, 500, 50);

		for (int i = 0; i < 10; i++) {
			labels[i] = new JLabel(String.valueOf(i + 1));
			labels[i].setFont(mainFont);
			topPanel.add(labels[i]);
		}

		/* ------------Bottom panel, progress info setup-------------- */
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 600, 500, 50);

		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setFont(mainFont);
		progressBar.setPreferredSize(new Dimension(500, 50));
		progressBar.setForeground(Color.red);
		bottomPanel.add(progressBar);

		/* ------------Main Game Board setup-------------- */
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(10, 10, 0, 0));
		centerPanel.setBounds(0, 50, 500, 500);

		for (int i = 0; i < 100; i++) {
			ships[i] = new JButton(String.valueOf(i + 1));
			ships[i].addActionListener(this);
			centerPanel.add(ships[i]);
		}

		/* ------------JFrame setup-------------- */
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

				ships[i].setEnabled(false);
				ships[i].setFocusable(false);
				System.out.print("Shot to " + ((int) i / 10 + 1) + " - " + ((int) i % 10 + 1) + " ... ");
				if (checkIfShipHit((int) i / 10, (int) i % 10)) {
					ships[i].setText("H");
					ships[i].setBackground(Color.red);
					System.out.println("Hit!");
					progressBar.setValue(progressBar.getValue() + 1);
				} else {
					ships[i].setText("X");
					ships[i].setBackground(Color.lightGray);
					System.out.println("Miss...");
				}
			}
		}
	}

	public boolean checkIfShipHit(int x, int y) {
		return board[x][y];
	}

	public boolean checkIfAllShipsSunk() {
		return shipsSunk == 20;
	}

}
