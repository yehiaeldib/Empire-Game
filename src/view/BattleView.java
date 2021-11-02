package view;

import java.awt.BorderLayout;
import static javax.swing.SwingUtilities.updateComponentTreeUI;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import engine.City;
import engine.Game;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.TargetNotReachedException;
import units.*;

public class BattleView implements ActionListener {

	public static String currcityName;
	public static Game game = Launcher.getGame();
	public static Army tempArmy;
	public static JButton Autoresolve;
	public static JButton AttackTheCity;
	public static Unit currUnit;
	private static JFrame z;
	private static JFrame q;
	private static Unit enemyUnit;
	

	public static void setAttackCityButton() {
		BattleView z = new BattleView();
		JFrame f = new JFrame();
		// f.setTitle("Player Name: " + game.getPlayer().getName() + " Current food: " +
		// game.getPlayer().getFood() + " Current Turn: " + game.getCurrentTurnCount() +
		// " Current Gold: " + game.getPlayer().getTreasury());

		f.setLayout(new FlowLayout());
		f.setSize(1600, 900);
		f.setLocation(0, 20);
		f.setResizable(false);
		// f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		f.setVisible(true);

		for (int i = 0; i < game.getAvailableCities().size(); i++) {
			if (!(game.getPlayer().getControlledCities().contains(game.getAvailableCities().get(i)))) {

				JButton b = new JButton(game.getAvailableCities().get(i).getName());
				b.addActionListener(z);
				b.setActionCommand(game.getAvailableCities().get(i).getName());
				f.add(b);

			}

		}

	}

	public static void getTargetArmy(JFrame f, City target) {
		// f.setLayout(new BorderLayout());
		f.setLayout(new FlowLayout());

		JLabel l = new JLabel(target.getName());
		// l.setBackground(Color.RED);
		f.add(l);
		// f.add(p, BorderLayout.NORTH);

		// JPanel p2 = new JPanel();
		// p.setLayout(new FlowLayout());

		String u = "";
		for (int i = 0; i < target.getDefendingArmy().getUnits().size(); i++) {
			JButton b;
			if (target.getDefendingArmy().getUnits().get(i) instanceof Archer) {
				u = " Archer  " + " level " + target.getDefendingArmy().getUnits().get(i).getLevel()
						+ " and its count is " + target.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
						+ " and its max count is " + target.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()
						+ "  ";
			} else if (target.getDefendingArmy().getUnits().get(i) instanceof Infantry) {
				u = " Infantry  " + " level " + target.getDefendingArmy().getUnits().get(i).getLevel()
						+ " and its count is " + target.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
						+ " and its max count is " + target.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()
						+ "  ";
				;
			} else {
				u = "Cavalry  " + " level " + target.getDefendingArmy().getUnits().get(i).getLevel()
						+ " and its count is " + target.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
						+ " and its max count is " + target.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()
						+ "  ";
			}
			b = new JButton(u);

			b.setBackground(Color.RED);
			f.add(b);

		}
		// f.add(p, BorderLayout.NORTH);
	}

	public static void getTargetArmy2(JFrame f, City target) {
		// f.setLayout(new BorderLayout());
		f.setLayout(new FlowLayout());

		JLabel l = new JLabel(target.getName());
		// l.setBackground(Color.RED);
		f.add(l);
		// f.add(p, BorderLayout.NORTH);

		// JPanel p2 = new JPanel();
		// p.setLayout(new FlowLayout());

		String u = "";
		for (int i = 0; i < target.getDefendingArmy().getUnits().size(); i++) {
			JButton b;
			if (target.getDefendingArmy().getUnits().get(i) instanceof Archer) {
				u = " Archer  " + " level " + target.getDefendingArmy().getUnits().get(i).getLevel()
						+ " and its count is " + target.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
						+ " and its max count is " + target.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()
						+ "  ";
			} else if (target.getDefendingArmy().getUnits().get(i) instanceof Infantry) {
				u = " Infantry  " + " level " + target.getDefendingArmy().getUnits().get(i).getLevel()
						+ " and its count is " + target.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
						+ " and its max count is " + target.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()
						+ "  ";
				;
			} else {
				u = "Cavalry  " + " level " + target.getDefendingArmy().getUnits().get(i).getLevel()
						+ " and its count is " + target.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
						+ " and its max count is " + target.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()
						+ "  ";
			}
			b = new JButton(u);
			b.setActionCommand("defending" + i);
			b.addActionListener(new BattleView());
			b.setBackground(Color.RED);
			f.add(b);

		}
		// f.add(p, BorderLayout.NORTH);
	}

	public static void getUnits(Army army, JFrame f) {
		JButton m = new JButton("Your Army");
		m.setBackground(Color.green);
		f.add(m);
		for (int j = 0; j < army.getUnits().size(); j++) {
			String u = "";
			if (army.getUnits().get(j) instanceof Archer) {
				u = "Archer  " + " level " + army.getUnits().get(j).getLevel() + "and its count is "
						+ army.getUnits().get(j).getCurrentSoldierCount() + "and its max count is "
						+ army.getUnits().get(j).getMaxSoldierCount() + "  ";
			} else if (army.getUnits().get(j) instanceof Infantry) {
				u = "Infantry  " + " level " + army.getUnits().get(j).getLevel() + "and its count is "
						+ army.getUnits().get(j).getCurrentSoldierCount() + "and its max count is "
						+ army.getUnits().get(j).getMaxSoldierCount() + "  ";
				;
			} else {
				u = "Cavalry  " + " level " + army.getUnits().get(j).getLevel() + "and its count is "
						+ army.getUnits().get(j).getCurrentSoldierCount() + "and its max count is "
						+ army.getUnits().get(j).getMaxSoldierCount() + "  ";
			}
			JButton v = new JButton(u);
			v.setBackground(Color.orange);
			v.setActionCommand("Unit" + j);
			v.addActionListener(new BattleView());
			f.add(v);
		}
	}

	public static void setAttackingArmy(JFrame f) {

		f.setLayout(new FlowLayout());

		for (int i = 0; i < game.getPlayer().getControlledArmies().size(); i++) {
			if (!(game.getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.MARCHING))) {
				int k = i + 1;
				JButton b = new JButton("Army" + k);
				b.addActionListener(new BattleView());
				b.setActionCommand("Army" + k);
				b.setBackground(Color.green);
				f.add(b);

				for (int j = 0; j < game.getPlayer().getControlledArmies().get(i).getUnits().size(); j++) {
					String u = "";
					if (game.getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Archer) {
						u = "Archer  " + " level "
								+ game.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()
								+ "and its count is "
								+ game.getPlayer().getControlledArmies().get(i).getUnits().get(j)
										.getCurrentSoldierCount()
								+ "and its max count is "
								+ game.getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount()
								+ "  ";
					} else if (game.getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Infantry) {
						u = "Infantry  " + " level "
								+ game.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()
								+ "and its count is "
								+ game.getPlayer().getControlledArmies().get(i).getUnits().get(j)
										.getCurrentSoldierCount()
								+ "and its max count is "
								+ game.getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount()
								+ "  ";
						;
					} else {
						u = "Cavalry  " + " level "
								+ game.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()
								+ "and its count is "
								+ game.getPlayer().getControlledArmies().get(i).getUnits().get(j)
										.getCurrentSoldierCount()
								+ "and its max count is "
								+ game.getPlayer().getControlledArmies().get(i).getUnits().get(j).getMaxSoldierCount()
								+ "  ";
					}
					JButton v = new JButton(u);
					v.setBackground(Color.orange);
					// v.setActionCommand(u);
					// v.addActionListener(new BattleView());
					f.add(v);
				}
			}
		}
		// f.add(p, BorderLayout.SOUTH);
	}

	public static City cityHelper(String cityName) {
		City c = new City("");
		for (int i = 0; i < game.getAvailableCities().size(); i++) {
			if (game.getAvailableCities().get(i).getName().equals(cityName))
				c = game.getAvailableCities().get(i);
		}
		return c;
	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("Rome")) {
			JFrame f = new JFrame();
			// f.setTitle("Player Name: " + game.getPlayer().getName() + " Current food: " +
			// game.getPlayer().getFood() + " Current Turn: " + game.getCurrentTurnCount() +
			// " Current Gold: " + game.getPlayer().getTreasury());

			f.setLayout(new FlowLayout());
			f.setSize(1600, 900);
			f.setLocation(0, 20);
			f.setResizable(false);
			f.setVisible(true);
			getTargetArmy(f, cityHelper("Rome"));
			setAttackingArmy(f);
			currcityName = "Rome";
		} else if (action.equals("Cairo")) {
			JFrame f = new JFrame();
			// f.setTitle("Player Name: " + game.getPlayer().getName() + " Current food: " +
			// game.getPlayer().getFood() + " Current Turn: " + game.getCurrentTurnCount() +
			// " Current Gold: " + game.getPlayer().getTreasury());

			f.setLayout(new FlowLayout());
			f.setSize(1600, 900);
			f.setLocation(0, 20);
			f.setResizable(false);
			// f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
			f.setVisible(true);

			getTargetArmy(f, cityHelper("Cairo"));
			setAttackingArmy(f);
			currcityName = "Cairo";
		} else if (action.equals("Sparta")) {
			JFrame f = new JFrame();
			// f.setTitle("Player Name: " + game.getPlayer().getName() + " Current food: " +
			// game.getPlayer().getFood() + " Current Turn: " + game.getCurrentTurnCount() +
			// " Current Gold: " + game.getPlayer().getTreasury());

			f.setLayout(new FlowLayout());
			f.setSize(1600, 900);
			f.setLocation(0, 20);
			f.setResizable(false);
			// f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
			f.setVisible(true);

			getTargetArmy(f, cityHelper("Sparta"));
			setAttackingArmy(f);
			currcityName = "Sparta";
		} else if (action.equals("Move Toward city")) {
			if (tempArmy.getDistancetoTarget() != 0) {
				game.endTurn();
				Launcher.mainF.setTitle("Player Name: " + game.getPlayer().getName() + "  Current food: "
						+ game.getPlayer().getFood() + "  Current Turn: " + game.getCurrentTurnCount()
						+ "  Current Gold: " + game.getPlayer().getTreasury());
			} else {
				JOptionPane.showMessageDialog(null, "you have already arrive to the city please choose another option");

			}
		} else if (action.equals("Autoresolve")) {
			if (tempArmy.getDistancetoTarget() == 0) {
				City temp = cityHelper(currcityName);
				try {
					game.autoResolve(tempArmy, temp.getDefendingArmy());
					if (tempArmy.getUnits().size() == 0) {
						JOptionPane.showMessageDialog(null, "you lost the battle ");
					} else {
						JOptionPane.showMessageDialog(null, "you win the battle ");
					}
				} catch (FriendlyFireException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "you try to attack one of your cities");
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"you didn't arrive to the city yet please press move button first until you arrive to the city");

			}
		} else if (action.equals("Siege the City")) {
			City tempcity = cityHelper(currcityName);
			try {
				game.getPlayer().laySiege(tempArmy, tempcity);
			} catch (TargetNotReachedException e1) {
				JOptionPane.showMessageDialog(null, "you didn't reach to the city yet");
				e1.printStackTrace();
			} catch (FriendlyCityException e1) {
				JOptionPane.showMessageDialog(null, "you try to lay your city under siege");
				e1.printStackTrace();
			}
		} else if (action.equals("Attack the City")) {
			if (tempArmy.getDistancetoTarget() == 0) {
				// q.setTitle("Player Name: " + game.getPlayer().getName() + " Current food: " +
				// game.getPlayer().getFood() + " Current Turn: " + game.getCurrentTurnCount() +
				// " Current Gold: " + game.getPlayer().getTreasury());
				q = new JFrame();
				q.setLocation(0, 20);
				q.setSize(1200, 800);
				q.setLayout(new FlowLayout());
				q.setVisible(true);
				getTargetArmy(q, cityHelper(currcityName));
				getUnits(tempArmy, q);
			} else {
				JOptionPane.showMessageDialog(null,
						"you didn't arrive to the city yet please press move button first until you arrive to the city");
			}
		} else if (action.equals("EndTurn")) {
			game.endTurn();
			if (game.getPlayer().getControlledCities().size() == 3
					|| game.getCurrentTurnCount() > game.getMaxTurnCount()) {
				game.isGameOver();
			}
			Launcher.mainF.setTitle("Player Name: " + game.getPlayer().getName() + "  Current food: "
					+ game.getPlayer().getFood() + "  Current Turn: " + game.getCurrentTurnCount() + "  Current Gold: "
					+ game.getPlayer().getTreasury());
			/*
			 * playerLabel.setText("Player Name: " + game.getPlayer().getName());
			 * foodLabel.setText("  Current food: " + game.getPlayer().getFood());
			 * goldLabel.setText("  Current Gold: " + game.getPlayer().getTreasury());
			 * turnsLabel.setText("  Current Turn: " + game.getCurrentTurnCount());
			 */
		}
		for (int w = 0; w < game.getPlayer().getControlledArmies().size(); w++) {
			int w1 = w + 1;
			if (action.equals("Army" + w1)) {
				game.targetCity(game.getPlayer().getControlledArmies().get(w), currcityName);
				tempArmy = game.getPlayer().getControlledArmies().get(w);
				JFrame ff = new JFrame();
				// ff.setTitle("Player Name: " + game.getPlayer().getName() + " Current food: "
				// + game.getPlayer().getFood() + " Current Turn: " + game.getCurrentTurnCount()
				// + " Current Gold: " + game.getPlayer().getTreasury());

				ff.setSize(1200, 800);
				ff.setLocation(0, 20);
				ff.setLayout(new FlowLayout());
				ff.setVisible(true);

				JButton EndTurn = new JButton("EndTurn");
				EndTurn.setBackground(Color.cyan);
				EndTurn.addActionListener(new helper());
				EndTurn.setActionCommand("EndTurn");

				ff.add(EndTurn);

				JButton move = new JButton("Move Toward city");
				move.addActionListener(new BattleView());
				move.setActionCommand("Move Toward city");
				ff.add(move);

				Autoresolve = new JButton("Autoresolve");
				Autoresolve.addActionListener(new BattleView());
				Autoresolve.setActionCommand("Autoresolve");
				ff.add(Autoresolve);

				JButton siege = new JButton("Siege the City");
				siege.addActionListener(new BattleView());
				siege.setActionCommand("Siege the City");
				ff.add(siege);

				AttackTheCity = new JButton("Attack the City");
				AttackTheCity.addActionListener(new BattleView());
				AttackTheCity.setActionCommand("Attack the City");
				ff.add(AttackTheCity);

				/*
				 * JButton AttackTheCity = new JButton("Attack the city");
				 * Autoresolve.addActionListener(new BattleView());
				 * Autoresolve.setActionCommand("Attack the city"); ff.add("Attack the city");
				 */
			}
		}
		for (int i = 0; i < tempArmy.getUnits().size(); i++) {
			if (action.equals("Unit" + i)) {
				// z.setTitle("Player Name: " + game.getPlayer().getName() + " Current food: " +
				// game.getPlayer().getFood() + " Current Turn: " + game.getCurrentTurnCount() +
				// " Current Gold: " + game.getPlayer().getTreasury());
				z = new JFrame();
				z.setLocation(0, 20);
				z.setSize(1200, 800);
				z.setLayout(new FlowLayout());
				z.setVisible(true);
				currUnit = tempArmy.getUnits().get(i);
				getTargetArmy2(z, cityHelper(currcityName));
				
			}
		}

		for (int i = 0; i < cityHelper(currcityName).getDefendingArmy().getUnits().size(); i++) {
			enemyUnit = cityHelper(currcityName).getDefendingArmy().getUnits().get(i);
			if (action.equals("defending" + i)) {
				try {
					currUnit.attack(enemyUnit);
					currUnit.attack(cityHelper(currcityName).getDefendingArmy().getUnits().get(i));						
					if (enemyUnit.getCurrentSoldierCount() == 0) {
						JOptionPane.showMessageDialog(null, "The Enemy Unit Died");
					}else {
						cityHelper(currcityName).getDefendingArmy().getUnits().get(i).attack(currUnit);
					}
					if (currUnit.getCurrentSoldierCount() == 0) {
						JOptionPane.showMessageDialog(null, "Your Unit Died");
					} 
					
					
					
										


					JOptionPane.showMessageDialog(null, "your unit 's count is "+currUnit.getCurrentSoldierCount() +" enemy unit 's count is "+enemyUnit.getCurrentSoldierCount());
					
			//		cityHelper(currcityName).getDefendingArmy().getUnits().get(i).setCurrentSoldierCount(enemyUnit.getCurrentSoldierCount());
					
					z.dispose();
					q.dispose();
					q = new JFrame();
					q.setLocation(0, 20);
					q.setSize(1200, 800);
					q.setLayout(new FlowLayout());
					q.setVisible(true);
					getTargetArmy(q, cityHelper(currcityName));
					getUnits(tempArmy, q);
					
					if(tempArmy.getUnits().size() == 0 || cityHelper(currcityName).getDefendingArmy().getUnits().size() == 0) {
						game.autoResolve(tempArmy, cityHelper(currcityName).getDefendingArmy());
					}
					if(game.getPlayer().getControlledCities().size() == 3)
						game.endTurn();
				/*	
					z = new JFrame();
					z.setLocation(0, 20);
					z.setSize(1200, 800);
					z.setLayout(new FlowLayout());
					z.setVisible(true);
					currUnit = tempArmy.getUnits().get(i);
					getTargetArmy2(z, cityHelper(currcityName));
				*/	
				} catch (FriendlyFireException e1) {
					JOptionPane.showMessageDialog(null, "Cannot attack a friendly unit");
					e1.printStackTrace();
				}
			}
		}

		/*
		 * for (int w = 0; w < game.getPlayer().getControlledArmies().size(); w++) { int
		 * w1 = w + 1; if (action.equals("Army" + w1)) {
		 * game.targetCity(game.getPlayer().getControlledArmies().get(w), currcityName);
		 * try { game.autoResolve(game.getPlayer().getControlledArmies().get(w),
		 * cityHelper(currcityName).getDefendingArmy()); } catch (FriendlyFireException
		 * e1) { JFrame n = new JFrame(); n.setLayout(new FlowLayout());
		 * n.setExtendedState(n.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		 * n.setVisible(true); JLabel m = new JLabel("Cannot attack a friendly unit");
		 * n.add(m); e1.printStackTrace(); } } }
		 */
		Launcher.playerLabel.setText("Player Name: " + game.getPlayer().getName());
		Launcher.foodLabel.setText("  Current food: " + game.getPlayer().getFood());
		Launcher.goldLabel.setText("  Current Gold: " + game.getPlayer().getTreasury());
		Launcher.turnsLabel.setText("  Current Turn: " + game.getCurrentTurnCount());

		Launcher.mainF.setTitle("Player Name: " + game.getPlayer().getName() + "  Current food: "
				+ game.getPlayer().getFood() + "  Current Turn: " + game.getCurrentTurnCount() + "  Current Gold: "
				+ game.getPlayer().getTreasury());

		for (int i = 0; i < game.getPlayer().getControlledArmies().size(); i++) {
			if (game.getPlayer().getControlledArmies().get(i).getUnits().size() == 0)
				game.getPlayer().getControlledArmies().remove(game.getPlayer().getControlledArmies().get(i));
		}
	}
	
}
