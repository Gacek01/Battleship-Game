import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {

	public final int UNIT_SIZE = 50;
	byte[][] board = new byte[10][10];
	JButton[] ships = new JButton[100];
	JLabel[] labels = new JLabel[10];
	JLabel shotsLabel = new JLabel("0");
	Font mainFont = new Font("Arial", Font.BOLD, 25);
	JProgressBar progressBar = new JProgressBar(0, 20);
	byte shipsSunk = 0;
	byte shotsFired = 0;

	MainFrame() {

		/* ------------Top axis setup-------------- */
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 10, 0, 0));
		topPanel.setBounds(15, 0, 500, 50);

		for (int i = 0; i < 10; i++) {
			labels[i] = new JLabel(String.valueOf((char) (i + 65)));
			labels[i].setFont(mainFont);
			topPanel.add(labels[i]);
		}

		/* ------------Bottom panel, progress info setup-------------- */
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 600, 500, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setFont(mainFont);
		progressBar.setPreferredSize(new Dimension(500, 100));
		progressBar.setForeground(Color.red);
		progressBar.setBackground(Color.blue);
		progressBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		shotsLabel.setFont(mainFont);
		bottomPanel.add(new JLabel("Shots fired: ")).setFont(mainFont);
		bottomPanel.add(shotsLabel);
		bottomPanel.add(progressBar);

		/* ------------Main Game Board setup-------------- */
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(10, 10, 0, 0));
		centerPanel.setBounds(0, 50, 500, 500);

		for (int i = 0; i < 100; i++) {
			ships[i] = new JButton(String.valueOf(i + 1));
			ships[i].addActionListener(this);
			ships[i].setFocusable(false);
			centerPanel.add(ships[i]);
		}
		placeShipsOnBoard();
		drawTestBoard(board);

		/* ------------JFrame setup-------------- */
		this.setIconImage(new ImageIcon("src//ship.png").getImage());
		this.setSize(515, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("+++Battleship+++");
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
				shotsFired++;
				ships[i].setEnabled(false);
				shotsLabel.setText(String.valueOf(shotsFired));
				System.out.print("Shot to " + ((int) i / 10 + 1) + "-" + (char) ((int) i % 10 + 65) + " ... ");
				if (checkIfShipHit((int) i / 10, (int) i % 10) > 0) {
					switch (checkIfShipHit((int) i / 10, (int) i % 10)) {
					case 4:
						ships[i].setBackground(Color.pink);
						ships[i].setText("IV");
						break;
					case 3:
						ships[i].setBackground(Color.yellow);
						ships[i].setText("III");
						break;
					case 2:
						ships[i].setBackground(Color.orange);
						ships[i].setText("II");
						break;
					case 1:
						ships[i].setBackground(Color.green);
						ships[i].setText("I");
						break;
					}
					System.out.println("Hit!");
					shipsSunk++;
					progressBar.setValue(progressBar.getValue() + 1);
				} else {
					ships[i].setText("X");
					ships[i].setBackground(Color.lightGray);
					System.out.println("Miss...");
				}
			}
		}
		if (checkIfAllShipsSunk()) {
			System.out.println("Victory! All ships sank with " + shotsFired + " shots");
			int exitOption = JOptionPane.showOptionDialog(null,
					"Glorious Victory!\nAll ships sank with " + shotsFired + " shots\nPlay again?", "Game Over",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
			if (exitOption == 0)
				new MainFrame();
			else
				System.exit(0);
			this.dispose();
		}
	}

	public byte checkIfShipHit(int x, int y) {
		return board[x][y]; // 0-miss, 1,2,3,4-hit
	}

	public boolean checkIfAllShipsSunk() {
		return shipsSunk == 20;
	}

	public void drawTestBoard(byte[][] board) {
		// print battleship board to console
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public boolean checkIfNoOtherShips(int x, int y) {
		// check if all adjacent fields are free
		try {
			if (board[x][y] == 0 & board[x + 1][y] == 0 & board[x][y + 1] == 0 & board[x + 1][y + 1] == 0
					& board[x - 1][y] == 0 & board[x][y - 1] == 0 & board[x - 1][y - 1] == 0 & board[x + 1][y - 1] == 0
					& board[x - 1][y + 1] == 0) {
				return true;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		return false;
	}

	public void placeShipsOnBoard() {
		Random random = new Random();
		int x, y, counter = 0;
		boolean dir;

		/* place 1x 4-square ship */
		dir = random.nextBoolean(); // 0-place to right, 1-place down
		if (dir) {
			x = random.nextInt(10);
			y = random.nextInt(7);
			board[x][y] = 4;
			board[x][y + 1] = 4;
			board[x][y + 2] = 4;
			board[x][y + 3] = 4;
		} else {
			x = random.nextInt(7);
			y = random.nextInt(10);
			board[x][y] = 4;
			board[x + 1][y] = 4;
			board[x + 2][y] = 4;
			board[x + 3][y] = 4;
		}

		counter = 0;
		/* place 2x 3-square ship */
		while (counter < 2) {
			dir = random.nextBoolean(); // 0-place to right, 1-place down
			if (dir) {
				x = random.nextInt(10);
				y = random.nextInt(8);
				if (checkIfNoOtherShips(x, y) && checkIfNoOtherShips(x, y + 1) && checkIfNoOtherShips(x, y + 2)) {
					board[x][y] = 3;
					board[x][y + 1] = 3;
					board[x][y + 2] = 3;
					counter++;
				}
			} else {
				x = random.nextInt(8);
				y = random.nextInt(10);
				if (checkIfNoOtherShips(x, y) && checkIfNoOtherShips(x + 1, y) && checkIfNoOtherShips(x + 2, y)) {
					board[x][y] = 3;
					board[x + 1][y] = 3;
					board[x + 2][y] = 3;
					counter++;
				}
			}
		}

		counter = 0;
		/* place 3x 2-square ship */
		while (counter < 3) {
			dir = random.nextBoolean(); // 0-place to right, 1-place down
			if (dir) {
				x = random.nextInt(10);
				y = random.nextInt(9);
				if (checkIfNoOtherShips(x, y) && checkIfNoOtherShips(x, y + 1)) {
					board[x][y] = 2;
					board[x][y + 1] = 2;
					counter++;
				}
			} else {
				x = random.nextInt(9);
				y = random.nextInt(10);
				if (checkIfNoOtherShips(x, y) && checkIfNoOtherShips(x + 1, y)) {
					board[x][y] = 2;
					board[x + 1][y] = 2;
					counter++;
				}
			}
		}

		counter = 0;
		/* place 1x 4-square ship */
		while (counter < 4) {
			x = random.nextInt(10);
			y = random.nextInt(10);
			if (checkIfNoOtherShips(x, y)) {
				board[x][y] = 1;
				counter++;
			}
		}
	}
}
