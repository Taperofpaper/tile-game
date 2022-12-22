package main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import mainPackage.TileGameBackup.NumTile;

import javax.swing.*;

import java.util.Arrays;
import java.util.Random;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TileGame implements KeyListener {
	
	int[][] unchangedLayout = new int[4][4];
	
  ImageIcon emptyTile = new ImageIcon("res/EmptyTile.png");
  ImageIcon tier1Tile = new ImageIcon("res/Tile1.png");
  ImageIcon tier2Tile = new ImageIcon("res/Tile2.png");
  ImageIcon tier3Tile = new ImageIcon("res/Tile3.png");
  ImageIcon tier4Tile = new ImageIcon("res/Tile4.png");
  ImageIcon tier5Tile = new ImageIcon("res/Tile5.png");
  ImageIcon tier6Tile = new ImageIcon("res/Tile6.png");
  ImageIcon tier7Tile = new ImageIcon("res/Tile7.png");
  ImageIcon tier8Tile = new ImageIcon("res/Tile8.png");
  ImageIcon tier9Tile = new ImageIcon("res/Tile9.png");
  ImageIcon tier10Tile = new ImageIcon("res/Tile10.png");
    
	private JFrame frame;
	private JLabel scoreLabel;
	private JPanel gameOverPanel;
	private JLabel gameOverLabel;
	private JLabel gameOverRestartLabel;
	
	JLabel[][] placeholderLayout = new JLabel[4][4];
    int[][] tileLayout = new int[4][4];
    
	
	public JLabel[][] getPlaceholderLayout() {
		return this.placeholderLayout;
	}
	
	public int[][] getTileLayout() {
		return this.tileLayout;
	}
	
	public int score = 0;
	public int genValue1 = 2;
	public int genValue2 = 4;
	public double value1To2Ratio = 0.5;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TileGame window = new TileGame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public TileGame() {
		
		initialize();
		updateBoard(tileLayout);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame("2048");

		frame.getContentPane().addKeyListener(this);
		frame.getContentPane().setFocusable(true);
		
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(177, 205, 430, 430);
		frame.getContentPane().add(panel);
	    panel.setLayout(null);
		
		gameOverPanel = new JPanel();
		gameOverPanel.setBounds(15, 150, 400, 130);
		gameOverPanel.setBackground(new Color(255, 255, 255));
		panel.add(gameOverPanel);
		gameOverPanel.setVisible(false);
		
		gameOverLabel = new JLabel("Game Over!");
		gameOverLabel.setFont(new Font("Tohoma", Font.BOLD, 50));
		gameOverLabel.setBounds(0, 0, 400, 60);
		gameOverPanel.add(gameOverLabel);
		
		gameOverRestartLabel = new JLabel("Press R to restart");
		gameOverRestartLabel.setFont(new Font("Tohoma", Font.PLAIN, 30));
		gameOverPanel.add(gameOverRestartLabel);
		
		JLabel resetInfoLabel = new JLabel("Press R to restart");
		resetInfoLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		resetInfoLabel.setBounds(180, 640, 192, 45);
		frame.getContentPane().add(resetInfoLabel);
		
		scoreLabel = new JLabel("Score: 0");
		scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		scoreLabel.setBounds(180, 100, 400, 50);
		frame.getContentPane().add(scoreLabel);
		
		initializeTileLayout(unchangedLayout);
		fillPlaceholderLayout(placeholderLayout);
		
		tileLayout[0][0] = 0; tileLayout[0][1] = 0; tileLayout[0][2] = 0; tileLayout[0][3] = 0;
		tileLayout[1][0] = 0; tileLayout[1][1] = 0; tileLayout[1][2] = 0; tileLayout[1][3] = 0;
		tileLayout[2][0] = 0; tileLayout[2][1] = 0; tileLayout[2][2] = 0; tileLayout[2][3] = 0;
		tileLayout[3][0] = 0; tileLayout[3][1] = 0; tileLayout[3][2] = 0; tileLayout[3][3] = 0;
		
		insertValue(tileLayout, genValue1, genValue2, value1To2Ratio);
		
		printLayout(tileLayout);
		
		//create placeholder layout
		placeholderLayout[0][0].setBounds(0, 0, 100, 100);
		panel.add(placeholderLayout[0][0]);
		
		placeholderLayout[0][1].setBounds(110, 0, 100, 100);
		panel.add(placeholderLayout[0][1]);
		
		placeholderLayout[0][2].setBounds(220, 0, 100, 100);
		panel.add(placeholderLayout[0][2]);
		
		placeholderLayout[0][3].setBounds(330, 0, 100, 100);
		panel.add(placeholderLayout[0][3]);
		
		
		placeholderLayout[1][0].setBounds(0, 110, 100, 100);
		panel.add(placeholderLayout[1][0]);
		
		placeholderLayout[1][1].setBounds(110, 110, 100, 100);
		panel.add(placeholderLayout[1][1]);
		
		placeholderLayout[1][2].setBounds(220, 110, 100, 100);
		panel.add(placeholderLayout[1][2]);
		
		placeholderLayout[1][3].setBounds(330, 110, 100, 100);
		panel.add(placeholderLayout[1][3]);
		
		
		placeholderLayout[2][0].setBounds(0, 220, 100, 100);
		panel.add(placeholderLayout[2][0]);
		
		placeholderLayout[2][1].setBounds(110, 220, 100, 100);
		panel.add(placeholderLayout[2][1]);
		
		placeholderLayout[2][2].setBounds(220, 220, 100, 100);
		panel.add(placeholderLayout[2][2]);
		
		placeholderLayout[2][3].setBounds(330, 220, 100, 100);
		panel.add(placeholderLayout[2][3]);
		
		
		placeholderLayout[3][0].setBounds(0, 330, 100, 100);
		panel.add(placeholderLayout[3][0]);
		
		placeholderLayout[3][1].setBounds(110, 330, 100, 100);
		panel.add(placeholderLayout[3][1]);
		
		placeholderLayout[3][2].setBounds(220, 330, 100, 100);
		panel.add(placeholderLayout[3][2]);
		
		placeholderLayout[3][3].setBounds(330, 330, 100, 100);
		panel.add(placeholderLayout[3][3]);
	}

	public void invokeGameOver() {
		System.out.println("Game Over");
		gameOverPanel.setVisible(true);
	}
	
	public void updateScore(int newTileTier) {
		score = score + newTileTier;
		scoreLabel.setText("Score: " + score);
	}
	
	public void resetLayout(int[][] tileLayout) {
		for(int i=0;i<tileLayout.length;i++) {
			for(int j=0;j<tileLayout.length;j++) {
				tileLayout[i][j] = 0;
			}
		}
	}
	
	public void updateBoard(int[][] tileLayout) {
		for(int i=0;i<tileLayout.length;i++) {
			for(int j=0;j<tileLayout.length;j++) {
				setTile(tileLayout[i][j], i, j);
			}
		}
	}
	
	public boolean hasEmptyTiles(int[][] tileLayout) {
		for(int i=0;i<tileLayout.length;i++) {
			for(int j=0;j<tileLayout.length;j++) {
				if(tileLayout[i][j] == 0) {
					return true;
				}
			}
		}
		return false;
	}

	public void insertValue(int[][] layout, int value1, int value2, double value1To2Ratio) { 
		Random rand1 = new Random();
		int max = layout.length;
		int val1 = rand1.nextInt(max);
		int val2 = rand1.nextInt(max);
		Random rand3 = new Random();
		double val3 = rand3.nextDouble();
		if(hasEmptyTiles(layout)) {
		mainLoop:
			while(true) {
				if(layout[val1][val2] == 0) {
					if(val3 < value1To2Ratio) {
						//System.out.println(rand3.nextDouble());
						System.out.println(value1);
						layout[val1][val2] = value1;
						break mainLoop;
					}
					else if(val3 > value1To2Ratio) {
						//System.out.println(rand3.nextDouble());
						System.out.println(value2);
						layout[val1][val2] = value2;
						break mainLoop;
					}
					else {
						val3 = rand3.nextDouble();
					}
				}
				else {
					val1 = rand1.nextInt(max);
					val2 = rand1.nextInt(max);
				}
			}
		}
	}
	
	public int[][] copy2DArray(int[][] layout1, int[][] layout2) {
		for(int i=0;i<layout1.length;i++) {
			for(int j=0;j<layout1.length;j++) {
				layout2[i][j] = layout1[i][j];
			}
		}
		return layout2;
	}
	
	public boolean checkIfMoved(String direction) {
		if(direction.equals("up")) {
			if(moveUp(true) != null) {
				return true;
			}
			else {
				return false;
			}
		}
		if(direction.equals("down")) {
			if(moveDown(true) != null) {
				return true;
			}
			else {
				return false;
			}
		}
		if(direction.equals("left")) {
			if(moveLeft(true) != null) {
				return true;
			}
			else {
				return false;
			}
		}
		if(direction.equals("right")) {
			if(moveRight(true) != null) {
				return true;
			}
			else {
				return false;
			}
		}
		if(direction.equals("all")) {
			if(moveUp(true) != null | moveDown(true) != null | moveLeft(true) != null | moveRight(true) != null) {
				return true;
				}
			else {
				return false;
			}
		}
		return false;
	}
	
	public int[][] moveUp(boolean checkIfMoved) {
		int[][] newTileLayout = new int[4][4];
		initializeTileLayout(newTileLayout);
		newTileLayout = copy2DArray(tileLayout, newTileLayout);
		for(int o=0;o<tileLayout.length-1;o++) {
			for(int i=0;i<tileLayout.length;i++) {
				for(int j=tileLayout.length-1;j>0;j--) {
					
					//merge
					if(newTileLayout[j][i] == newTileLayout[j-1][i] && newTileLayout[j][i] != 0) {
						newTileLayout[j-1][i] *= 2;
						newTileLayout[j][i] = 0;
						if(checkIfMoved == false) {
							updateScore(newTileLayout[j-1][i]);
						}
					}
					//shift
					else if(newTileLayout[j-1][i] == 0 && newTileLayout[j][i] != 0) {
						newTileLayout[j-1][i] = newTileLayout[j][i];
						newTileLayout[j][i] = 0;
					}
				}
			}
		}
		if(checkIfMoved == true) {
			if(arraysAreEqual(tileLayout, newTileLayout) == true) {
				return null;
			}
			else {
				return newTileLayout;
			}
		}
		return newTileLayout;
	}
	
	public int[][] moveDown(boolean checkIfMoved) {
		int[][] newTileLayout = new int[4][4];
		initializeTileLayout(newTileLayout);
		newTileLayout = copy2DArray(tileLayout, newTileLayout);
		for(int o=0;o<tileLayout.length-1;o++) {
			for(int i=0;i<tileLayout.length;i++) {
				for(int j=0;j<tileLayout.length-1;j++) {

					//merge
					if(newTileLayout[j][i] == newTileLayout[j+1][i] && newTileLayout[j][i] != 0) {
						newTileLayout[j+1][i] *= 2;
						newTileLayout[j][i] = 0;
						if(checkIfMoved == false) {
							updateScore(newTileLayout[j+1][i]);
						}
					}
					//shift
					else if(newTileLayout[j+1][i] == 0 && newTileLayout[j][i] != 0) {
						newTileLayout[j+1][i] = newTileLayout[j][i];
						newTileLayout[j][i] = 0;
					}
				}
			}
		}
		if(checkIfMoved == true) {
			if(arraysAreEqual(tileLayout, newTileLayout) == true) {
				return null;
			}
			else {
				return newTileLayout;
			}
		}
		return newTileLayout;
	}
	
	public int[][] moveLeft(boolean checkIfMoved) {
		int[][] newTileLayout = new int[4][4];
		initializeTileLayout(newTileLayout);
		newTileLayout = copy2DArray(tileLayout, newTileLayout);
		for(int o=0;o<tileLayout.length-1;o++) {
			for(int i=0;i<tileLayout.length;i++) {
				for(int j=tileLayout.length-1;j>0;j--) {
					
//					int currentTile = newTileLayout[j][i];
//					int leftTile = newTileLayout[j-1][i];
					
					//merge
					if(newTileLayout[i][j] == newTileLayout[i][j-1] && newTileLayout[i][j] != 0) {
						newTileLayout[i][j-1] *= 2;
						newTileLayout[i][j] = 0;
						if(checkIfMoved == false) {
							updateScore(newTileLayout[i][j-1]);
						}
					}
					//shift
					else if(newTileLayout[i][j-1] == 0 && newTileLayout[i][j] != 0) {
						newTileLayout[i][j-1] = newTileLayout[i][j];
						newTileLayout[i][j] = 0;
					}
				}
			}
		}
		if(checkIfMoved == true) {
			if(arraysAreEqual(tileLayout, newTileLayout) == true) {
				return null;
			}
			else {
				return newTileLayout;
			}
		}
		return newTileLayout;
	}
	
	public int[][] moveRight(boolean checkIfMoved) {
		int[][] newTileLayout = new int[4][4];
		initializeTileLayout(newTileLayout);
		newTileLayout = copy2DArray(tileLayout, newTileLayout);
		for(int o=0;o<tileLayout.length-1;o++) {
			for(int i=0;i<tileLayout.length;i++) {
				for(int j=0;j<tileLayout.length-1;j++) {

					//merge
					if(newTileLayout[i][j] == newTileLayout[i][j+1] && newTileLayout[i][j] != 0) {
						newTileLayout[i][j+1] *= 2;
						newTileLayout[i][j] = 0;
						if(checkIfMoved == false) {
							updateScore(newTileLayout[i][j+1]);
						}
					}
					//shift
					else if(newTileLayout[i][j+1] == 0 && newTileLayout[i][j] != 0) {
						newTileLayout[i][j+1] = newTileLayout[i][j];
						newTileLayout[i][j] = 0;
					}
				}
			}
		}
		if(checkIfMoved == true) {
			if(arraysAreEqual(tileLayout, newTileLayout) == true) {
				return null;
			}
			else {
				return newTileLayout;
			}
		}
		return newTileLayout;
	}
	
	public void setTile(int tile, int i, int j) {
		
		if(tile == 0) {
			placeholderLayout[i][j].setIcon(emptyTile);
		}
		if(tile == Math.pow(2,1)) {
			placeholderLayout[i][j].setIcon(tier1Tile);
		}
		if(tile == Math.pow(2,2)) {
			placeholderLayout[i][j].setIcon(tier2Tile);
		}
		if(tile == Math.pow(2,3)) {
			placeholderLayout[i][j].setIcon(tier3Tile);
		}
		if(tile == Math.pow(2,4)) {
			placeholderLayout[i][j].setIcon(tier4Tile);
		}
		if(tile == Math.pow(2,5)) {
			placeholderLayout[i][j].setIcon(tier5Tile);
		}
		if(tile == Math.pow(2,6)) {
			placeholderLayout[i][j].setIcon(tier6Tile);
		}
		if(tile == Math.pow(2,7)) {
			placeholderLayout[i][j].setIcon(tier7Tile);
		}
		if(tile == Math.pow(2,8)) {
			placeholderLayout[i][j].setIcon(tier8Tile);
		}
		if(tile == Math.pow(2,9)) {
			placeholderLayout[i][j].setIcon(tier9Tile);
		}
		if(tile == Math.pow(2,10)) {
			placeholderLayout[i][j].setIcon(tier10Tile);
		}
 	}
	
	public static void printLayout(int[][] layout) {
		for(int i=0;i<layout.length;i++) {
			for(int j=0;j<layout.length;j++) {
				System.out.print(layout[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void fillPlaceholderLayout(JLabel[][] layout) {
		for(int i=0;i<layout.length;i++) {
			for(int j=0;j<layout.length;j++) {
				placeholderLayout[i][j] = new JLabel();
			}
		}
	}
	
	public void initializeTileLayout(int[][] layout) {
		for(int i=0;i<layout.length;i++) {
			for(int j=0;j<layout.length;j++) {
				layout[i][j] = 0;
			}
		}
	}
	
	public boolean arraysAreEqual(int[][] layout1, int[][] layout2) {
		for(int i=0;i<layout1.length;i++) {
			for(int j=0;j<layout1.length;j++) {
				if(layout1[i][j] == layout2[i][j]) {}
				else {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				
				if(checkIfMoved("all") == false) {
					invokeGameOver();
				}
				else if(checkIfMoved("up") == false) {}
				else {
					tileLayout = moveUp(false);		
					insertValue(tileLayout, genValue1, genValue2, value1To2Ratio);
					updateBoard(tileLayout);
						
					}
				}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				
				if(checkIfMoved("all") == false) {
					invokeGameOver();
				}
				else if(checkIfMoved("down") == false) {}
				else {
					tileLayout = moveDown(false);
					insertValue(tileLayout, genValue1, genValue2, value1To2Ratio);
					updateBoard(tileLayout);	
					}
				}
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				
				if(checkIfMoved("all") == false) {
					invokeGameOver();
				}
				else if(checkIfMoved("left") == false) {}
				else {
					tileLayout = moveLeft(false);
					insertValue(tileLayout, genValue1, genValue2, value1To2Ratio);
					updateBoard(tileLayout);	
					}
				}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				
				if(checkIfMoved("all") == false) {
					invokeGameOver();
				}
				else if(checkIfMoved("right") == false) {}
				else {
					tileLayout = moveRight(false);
					insertValue(tileLayout, genValue1, genValue2, value1To2Ratio);
					updateBoard(tileLayout);
					}
				}
			if(e.getKeyCode() == KeyEvent.VK_R) {
				resetLayout(tileLayout);
				insertValue(tileLayout, genValue1, genValue2, value1To2Ratio);
				updateBoard(tileLayout);
				score = 0;
				scoreLabel.setText("Score: " + score);
				if(gameOverPanel.isVisible()) {
					gameOverPanel.setVisible(false);
				}
			}
		}

	@Override
	public void keyReleased(KeyEvent e) {}
}
