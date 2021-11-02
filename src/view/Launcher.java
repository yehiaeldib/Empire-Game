package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import engine.Game;
import engine.Player;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;

import java.util.*;

public class Launcher extends Canvas implements ActionListener {
	public static Game game;
	public static JFrame mainF;

	public static JLabel playerLabel;
	public static JLabel foodLabel;
	public static JLabel turnsLabel;
	public static JLabel goldLabel;
	public static JPanel StatusPanel;

	public static void StartGame()
			throws IOException, BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException {
		JOptionPane.showMessageDialog(null, "Hello, Welcome to The Empire.");
		String name = JOptionPane.showInputDialog("Player 's name");
		String[] citiesString = { "Cairo", "Rome", "Sparta" };
		String city = (String) JOptionPane.showInputDialog(null, "choose a city between (Cairo or Rome or Sparta)",
				null, JOptionPane.QUESTION_MESSAGE, null, citiesString, citiesString[0]);

		game = new Game(name, city);

		game.loadArmy("Rome", "rome_army.csv");
		game.loadArmy("Cairo", "cairo_army.csv");
		game.loadArmy("Sparta", "sparta_army.csv");

		/*
		 * JPanel StatusPanel = new JPanel(); StatusPanel.setLayout(new FlowLayout());
		 * playerLabel = new JLabel("Player Name: " + game.getPlayer().getName());
		 * StatusPanel.add(playerLabel); foodLabel = new JLabel("  Current food: " +
		 * game.getPlayer().getFood()); StatusPanel.add(foodLabel); turnsLabel = new
		 * JLabel("  Current Turn: " + game.getCurrentTurnCount());
		 * StatusPanel.add(turnsLabel); game.getPlayer().setTreasury(5000); goldLabel =
		 * new JLabel("  Current Gold: " + game.getPlayer().getTreasury());
		 * StatusPanel.add(goldLabel);
		 */

		mainF = new JFrame();
		mainF.setLayout(new BorderLayout());
		mainF.setExtendedState(mainF.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		mainF.setVisible(true);
		mainF.setTitle("Player Name: " + game.getPlayer().getName() + "  Current food: " + game.getPlayer().getFood()
				+ "  Current Turn: " + game.getCurrentTurnCount() + "  Current Gold: "
				+ game.getPlayer().getTreasury());
	//	mainF.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		// f.add(m);
		// f.setSize(1650, 1080);
		// JLabel map = new JLabel(new ImageIcon("map.jpeg"));
		// map.setLayout(new BorderLayout());
		// map.setOpaque(true);
		// StatusPanel.setOpaque(false);

		mainF.add(new JLabel(new ImageIcon("map.jpeg")), BorderLayout.SOUTH);
		// mainF.add(StatusPanel, BorderLayout.NORTH);

		JPanel leftSide = new JPanel();
		mainF.add(leftSide, BorderLayout.NORTH);
		leftSide.setLayout(new FlowLayout());
		Launcher m = new Launcher();

		JButton EndTurn = new JButton("EndTurn");
		EndTurn.setBackground(Color.cyan);
		EndTurn.addActionListener(m);
		EndTurn.setActionCommand("EndTurn");
		leftSide.add(EndTurn);

		JButton myIdleArmies = new JButton(game.getPlayer().getName() + "'s idle Armies");
		myIdleArmies.addActionListener(m);
		myIdleArmies.setActionCommand("myIdleArmies");
		leftSide.add(myIdleArmies);

		JButton notMyIdleArmies = new JButton(game.getPlayer().getName() + "'s marching or besieging Armies");
		notMyIdleArmies.addActionListener(m);
		notMyIdleArmies.setActionCommand("notMyIdleArmies");
		leftSide.add(notMyIdleArmies);

		JButton controlledcities = new JButton("controlledcities");
		controlledcities.addActionListener(m);
		controlledcities.setActionCommand("controlledcities");
		leftSide.add(controlledcities);

		JButton Attack = new JButton("Attack");
		Attack.addActionListener(m);
		Attack.setActionCommand("Attack");
		Attack.setBackground(Color.RED);
		leftSide.add(Attack);

		mainF.repaint();
		mainF.revalidate();

	}

	public static Game getGame() {
		return game;
	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("myIdleArmies"))
			helper.myIdleArmies(game);
		else if (action.equals("notMyIdleArmies"))
			helper.notMyIdleArmies(game);
		else if (action.equals("controlledcities"))
			helper.myCities(game);
		else if (action.equals("Attack")) {
			BattleView.setAttackCityButton();
		} else if (action.equals("EndTurn")) {
			game.endTurn();
			if (game.getPlayer().getControlledCities().size() == 3
					|| game.getCurrentTurnCount() > game.getMaxTurnCount()) {
				game.isGameOver();
			}
			mainF.setTitle("Player Name: " + game.getPlayer().getName() + "  Current food: "
					+ game.getPlayer().getFood() + "  Current Turn: " + game.getCurrentTurnCount() + "  Current Gold: "
					+ game.getPlayer().getTreasury());
			/*
			 * playerLabel.setText("Player Name: " + game.getPlayer().getName());
			 * foodLabel.setText("  Current food: " + game.getPlayer().getFood());
			 * goldLabel.setText("  Current Gold: " + game.getPlayer().getTreasury());
			 * turnsLabel.setText("  Current Turn: " + game.getCurrentTurnCount());
			 */
		}
		mainF.setTitle("Player Name: " + game.getPlayer().getName() + "  Current food: " + game.getPlayer().getFood()
				+ "  Current Turn: " + game.getCurrentTurnCount() + "  Current Gold: "
				+ game.getPlayer().getTreasury());
		/*
		 * playerLabel.setText("Player Name: " + game.getPlayer().getName());
		 * foodLabel.setText("  Current food: " + game.getPlayer().getFood());
		 * goldLabel.setText("  Current Gold: " + game.getPlayer().getTreasury());
		 * turnsLabel.setText("  Current Turn: " + game.getCurrentTurnCount());
		 */
		for (int i = 0; i < game.getPlayer().getControlledArmies().size(); i++) {
			if (game.getPlayer().getControlledArmies().get(i).getUnits().size() == 0)
				game.getPlayer().getControlledArmies().remove(game.getPlayer().getControlledArmies().get(i));
		}

	}

	public static JLabel getLabel(String s) {

		JLabel x = new JLabel(s);
		return x;

	}

	public void paint(Graphics g) {

		Toolkit t = Toolkit.getDefaultToolkit();
		Image i = t.getImage("map.jpeg");
		g.drawImage(i, 0, 0, this);

	}

	public static void putmap() {
		Launcher m = new Launcher();
		JFrame f = new JFrame();
		// f.setTitle("Player Name: " + game.getPlayer().getName() + " Current food: " +
		// game.getPlayer().getFood() + " Current Turn: " + game.getCurrentTurnCount() +
		// " Current Gold: " + game.getPlayer().getTreasury());
		f.add(m);
		f.setSize(1650, 1080);
		f.setResizable(false);
		f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		f.setVisible(true);

	}

	public static void main(String[] args)
			throws IOException, BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException {

		StartGame();

	}
}
