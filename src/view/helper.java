package view;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;

import engine.City;
import engine.Game;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxCapacityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;

import units.Archer;
import units.Army;
import units.Infantry;
import units.Status;

public class helper implements ActionListener {
	public static City currcity;
	public static Game currgame = Launcher.getGame();
	public static MilitaryBuilding currbuild;
	public static EconomicBuilding ecocurrbuild;
	public static Choice c;
	public static Army currarmy;
	public static boolean tempflag = false;

	public static void myIdleArmies(Game game) {
		JFrame f = new JFrame();
		// f.setTitle("Player Name: " + currgame.getPlayer().getName() + " Current food:
		// " + currgame.getPlayer().getFood() + " Current Turn: " +
		// currgame.getCurrentTurnCount() + " Current Gold: " +
		// currgame.getPlayer().getTreasury());

		f.setLayout(new FlowLayout());
		f.setSize(1600, 900);
		f.setLocation(0, 20);
		f.setResizable(false);
		f.setVisible(true);

		for (int i = 0; i < game.getPlayer().getControlledArmies().size(); i++) {
			if (game.getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.IDLE)) {
				int p = i + 1;
				JButton myArmy = new JButton("Army" + p);
				myArmy.setBackground(Color.LIGHT_GRAY);
				f.add(myArmy);
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
						;
					}
					f.add(new JLabel(u));
				}
			}
		}
	}

	public static void myCities(Game game) {
		// currgame = game;
		JFrame f = new JFrame();
//		f.setTitle("Player Name: " + currgame.getPlayer().getName() + "  Current food: " + currgame.getPlayer().getFood() + "  Current Turn: " + currgame.getCurrentTurnCount() + "  Current Gold: " + currgame.getPlayer().getTreasury());

		f.setVisible(true);
		f.setLayout(new FlowLayout());
		f.setSize(1600, 900);
		f.setLocation(0, 20);
		f.setResizable(false);

		for (int i = 0; i < game.getPlayer().getControlledCities().size(); i++) {
			// currcity = game.getPlayer().getControlledCities().get(i);
			JButton b = new JButton(game.getPlayer().getControlledCities().get(i).getName());
			b.addActionListener(new helper());
			b.setActionCommand(game.getPlayer().getControlledCities().get(i).getName());
			f.add(b);

		}

	}

	public static void insideMyCity(City x) {
		currcity = x;
		helper z = new helper();
		JButton controlledArmiesInCity = new JButton("controlledArmiesInCity");
		controlledArmiesInCity.addActionListener(z);
		controlledArmiesInCity.setActionCommand("controlledArmiesInCity");
		JButton economicalBuildings = new JButton("economicalBuildings");
		economicalBuildings.addActionListener(z);
		economicalBuildings.setActionCommand("economicalBuildings");
		JButton militaryBuildings = new JButton("militaryBuildings");
		militaryBuildings.addActionListener(z);
		militaryBuildings.setActionCommand("militaryBuildings");
		JButton defendingArmy = new JButton("defendingArmy");
		defendingArmy.addActionListener(z);
		defendingArmy.setActionCommand("defendingArmy");
		JLabel turnsUnderSiege = new JLabel("turnsUnderSiege = " + x.getTurnsUnderSiege());
		JLabel underSiege = new JLabel("underSiege is " + x.isUnderSiege());

		JButton EndTurn = new JButton("EndTurn");
		EndTurn.setBackground(Color.cyan);
		EndTurn.addActionListener(new helper());
		EndTurn.setActionCommand("EndTurn");

		JFrame f = new JFrame();
		// f.setTitle("Player Name: " + currgame.getPlayer().getName() + " Current food:
		// " + currgame.getPlayer().getFood() + " Current Turn: " +
		// currgame.getCurrentTurnCount() + " Current Gold: " +
		// currgame.getPlayer().getTreasury());

		f.setVisible(true);
		f.setLayout(new FlowLayout());
		f.setSize(1600, 900);
		f.setLocation(0, 20);
		f.setResizable(false);
		// f.setResizable(false);
		f.add(EndTurn);
		f.add(economicalBuildings);
		f.add(militaryBuildings);
		f.add(defendingArmy);
		f.add(turnsUnderSiege);
		f.add(underSiege);
		f.add(controlledArmiesInCity);
		c = new Choice();
		c.setBounds(100, 100, 75, 75);
		c.add("Build a Market");
		c.add("Build a Farm");
		c.add("Build an ArcheryRange");
		c.add("Build a Barracks");
		c.add("Build a Stable");
		f.add(c);
		JButton Build = new JButton("Build");
		Build.addActionListener(z);
		Build.setActionCommand("Build");
		f.add(Build);

		JButton iniateArmy = new JButton("Initiate an Army");
		iniateArmy.addActionListener(z);
		iniateArmy.setActionCommand("Initiate an Army");
		iniateArmy.setBackground(Color.ORANGE);
		f.add(iniateArmy);

		if (x.getName().equals("Cairo")) {
			f.add(new JLabel(new ImageIcon("Cairo.jpeg")), BorderLayout.SOUTH);
		} else if (x.getName().equals("Rome")) {
			f.add(new JLabel(new ImageIcon("Rome.jpeg")), BorderLayout.SOUTH);

		} else if (x.getName().equals("Sparta")) {
			f.add(new JLabel(new ImageIcon("Sparta.jpeg")), BorderLayout.SOUTH);

		}
		f.repaint();
		f.revalidate();

	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("Rome")) {

			insideMyCity(cityHelper("Rome"));

		} else if (action.equals("Cairo")) {
			insideMyCity(cityHelper("Cairo"));
		} else if (action.equals("Sparta")) {
			insideMyCity(cityHelper("Sparta"));
		} else if (action.equals("controlledArmiesInCity")) {
			getMyArmiesInCity(currcity, currgame);
		} else if (action.equals("Build")) {
			if (c.getSelectedIndex() == 0) {
				try {
					currgame.getPlayer().build("Market", currcity.getName());
				} catch (NotEnoughGoldException e1) {
					JOptionPane.showMessageDialog(null, "Not enough gold");
					e1.printStackTrace();
				}
			} else if (c.getSelectedIndex() == 1) {
				try {
					currgame.getPlayer().build("Farm", currcity.getName());
				} catch (NotEnoughGoldException e1) {
					JOptionPane.showMessageDialog(null, "Not enough gold");
					e1.printStackTrace();
				}

			} else if (c.getSelectedIndex() == 2) {
				try {
					currgame.getPlayer().build("ArcheryRange", currcity.getName());
				} catch (NotEnoughGoldException e1) {
					JOptionPane.showMessageDialog(null, "Not enough gold");
					e1.printStackTrace();
				}
			} else if (c.getSelectedIndex() == 3) {
				try {
					currgame.getPlayer().build("Barracks", currcity.getName());
				} catch (NotEnoughGoldException e1) {

					JOptionPane.showMessageDialog(null, "Not enough gold");
					e1.printStackTrace();
				}
			} else if (c.getSelectedIndex() == 4) {
				try {
					currgame.getPlayer().build("Stable", currcity.getName());
				} catch (NotEnoughGoldException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Not enough gold");
					e1.printStackTrace();
				}
			}
		}

		else if (action.equals("economicalBuildings")) {
			JFrame f = new JFrame();
			// f.setTitle("Player Name: " + currgame.getPlayer().getName() + " Current food:
			// " + currgame.getPlayer().getFood() + " Current Turn: " +
			// currgame.getCurrentTurnCount() + " Current Gold: " +
			// currgame.getPlayer().getTreasury());

			f.setVisible(true);
			f.setLayout(new FlowLayout());
			f.setSize(1600, 900);
			f.setLocation(0, 20);
			f.setResizable(false);
			// f.setResizable(false);
			JButton EndTurn = new JButton("EndTurn");
			EndTurn.setBackground(Color.cyan);
			EndTurn.addActionListener(new helper());
			EndTurn.setActionCommand("EndTurn");
			f.add(EndTurn);
			for (int i = 0; i < currcity.getEconomicalBuildings().size(); i++) {
				JButton Upgrade = null;
				if (currcity.getEconomicalBuildings().get(i) instanceof Market) {
					f.add(new JLabel("type: Market, Level:  " + currcity.getEconomicalBuildings().get(i).getLevel()
							+ "  UpgradeCost: " + currcity.getEconomicalBuildings().get(i).getUpgradeCost()));
					Upgrade = new JButton("Upgrade Market");
					Upgrade.addActionListener(new helper());
					Upgrade.setActionCommand("Upgrade Market");
					ecocurrbuild = currcity.getEconomicalBuildings().get(i);
				} else if (currcity.getEconomicalBuildings().get(i) instanceof Farm) {
					f.add(new JLabel("type: Farm, Level:  " + currcity.getEconomicalBuildings().get(i).getLevel()
							+ "  UpgradeCost: " + currcity.getEconomicalBuildings().get(i).getUpgradeCost()));
					Upgrade = new JButton("Upgrade Farm");
					Upgrade.addActionListener(new helper());
					Upgrade.setActionCommand("Upgrade Farm");
					ecocurrbuild = currcity.getEconomicalBuildings().get(i);
				}
				f.add(Upgrade);
			}
		} else if (action.equals("militaryBuildings")) {
			JFrame f = new JFrame();
			// f.setTitle("Player Name: " + currgame.getPlayer().getName() + " Current food:
			// " + currgame.getPlayer().getFood() + " Current Turn: " +
			// currgame.getCurrentTurnCount() + " Current Gold: " +
			// currgame.getPlayer().getTreasury());

			f.setVisible(true);
			f.setLayout(new FlowLayout());
			f.setSize(1600, 900);
			f.setLocation(0, 20);
			f.setResizable(false);
			// f.setResizable(false);
			JButton EndTurn = new JButton("EndTurn");
			EndTurn.setBackground(Color.cyan);
			EndTurn.addActionListener(new helper());
			EndTurn.setActionCommand("EndTurn");
			f.add(EndTurn);
			for (int i = 0; i < currcity.getMilitaryBuildings().size(); i++) {
				JButton Recruit = null;

				JButton Upgrade = null;
				String s = "";

				if (currcity.getMilitaryBuildings().get(i) instanceof ArcheryRange) {
					f.add(new JLabel("type: ArcheryRange, Level: " + currcity.getMilitaryBuildings().get(i).getLevel()
							+ "  UpgradeCost: " + currcity.getMilitaryBuildings().get(i).getUpgradeCost()
							+ "  RecruitmentCost: " + currcity.getMilitaryBuildings().get(i).getRecruitmentCost()));

					Recruit = new JButton("ArcheryRange Recruit");
					Recruit.addActionListener(new helper());
					Recruit.setActionCommand("ArcheryRange Recruit");

					Upgrade = new JButton("Upgrade ArcheryRange");
					Upgrade.addActionListener(new helper());
					Upgrade.setActionCommand("Upgrade ArcheryRange");
					currbuild = currcity.getMilitaryBuildings().get(i);
				} else if (currcity.getMilitaryBuildings().get(i) instanceof Barracks) {
					f.add(new JLabel("type: Barracks, Level:  " + currcity.getMilitaryBuildings().get(i).getLevel()
							+ "  UpgradeCost: " + currcity.getMilitaryBuildings().get(i).getUpgradeCost()
							+ "  RecruitmentCost: " + currcity.getMilitaryBuildings().get(i).getRecruitmentCost()));

					Recruit = new JButton("Barracks Recruit");
					Recruit.addActionListener(new helper());
					Recruit.setActionCommand("Barracks Recruit");

					Upgrade = new JButton("Upgrade Barracks");
					Upgrade.addActionListener(new helper());
					Upgrade.setActionCommand("Upgrade Barracks");
					currbuild = currcity.getMilitaryBuildings().get(i);

				} else if (currcity.getMilitaryBuildings().get(i) instanceof Stable) {
					f.add(new JLabel("type: Stable, Level:  " + currcity.getMilitaryBuildings().get(i).getLevel()
							+ "  UpgradeCost: " + currcity.getMilitaryBuildings().get(i).getUpgradeCost()
							+ "  RecruitmentCost: " + currcity.getMilitaryBuildings().get(i).getRecruitmentCost()));

					Recruit = new JButton("Stable Recruit");
					Recruit.addActionListener(new helper());
					Recruit.setActionCommand("Stable Recruit");

					Upgrade = new JButton("Upgrade Stable");
					Upgrade.addActionListener(new helper());
					Upgrade.setActionCommand("Upgrade Stable");
					currbuild = currcity.getMilitaryBuildings().get(i);
				}
				f.add(Upgrade);

				f.add(Recruit);
			}
		} else if (action.equals("defendingArmy")) {
			JFrame f = new JFrame();
			// f.setTitle("Player Name: " + currgame.getPlayer().getName() + " Current food:
			// " + currgame.getPlayer().getFood() + " Current Turn: " +
			// currgame.getCurrentTurnCount() + " Current Gold: " +
			// currgame.getPlayer().getTreasury());

			f.setVisible(true);
			f.setLayout(new FlowLayout());
			f.setSize(1600, 900);
			f.setLocation(0, 20);
			f.setResizable(false);
			String u = "";
			for (int i = 0; i < currcity.getDefendingArmy().getUnits().size(); i++) {

				if (currcity.getDefendingArmy().getUnits().get(i) instanceof Archer) {
					u = " Archer  " + " level " + currcity.getDefendingArmy().getUnits().get(i).getLevel()
							+ " and its count is "
							+ currcity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
							+ " and its max count is "
							+ currcity.getDefendingArmy().getUnits().get(i).getMaxSoldierCount() + "  ";
				} else if (currcity.getDefendingArmy().getUnits().get(i) instanceof Infantry) {
					u = " Infantry  " + " level " + currcity.getDefendingArmy().getUnits().get(i).getLevel()
							+ " and its count is "
							+ currcity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
							+ " and its max count is "
							+ currcity.getDefendingArmy().getUnits().get(i).getMaxSoldierCount() + "  ";
					;
				} else {
					u = "Cavalry  " + " level " + currcity.getDefendingArmy().getUnits().get(i).getLevel()
							+ " and its count is "
							+ currcity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
							+ " and its max count is "
							+ currcity.getDefendingArmy().getUnits().get(i).getMaxSoldierCount() + "  ";
				}
				f.add(new JLabel(u));

			}

		} else if (action.equals("Upgrade Market")) {

			try {
				for (int i = 0; i < currcity.getEconomicalBuildings().size(); i++) {
					if (currcity.getEconomicalBuildings().get(i) instanceof Market) {
						if (currgame.getPlayer().getTreasury()
								- currcity.getEconomicalBuildings().get(i).getUpgradeCost() >= 0) {
							currgame.getPlayer().setTreasury(currgame.getPlayer().getTreasury()
									- currcity.getEconomicalBuildings().get(i).getUpgradeCost());
							currcity.getEconomicalBuildings().get(i).upgrade();
						} else {
							JOptionPane.showMessageDialog(null, "Not enough gold");
						}
					}
				}

			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(null, "Building is in cool down. Wait for the next turn ");
				e1.printStackTrace();
			} catch (MaxLevelException e1) {
				JOptionPane.showMessageDialog(null, "Maximum level reached!!");
				e1.printStackTrace();
			}

		} else if (action.equals("Upgrade Farm")) {
			try {
				for (int i = 0; i < currcity.getEconomicalBuildings().size(); i++) {
					if (currcity.getEconomicalBuildings().get(i) instanceof Farm) {
						if (currgame.getPlayer().getTreasury()
								- currcity.getEconomicalBuildings().get(i).getUpgradeCost() >= 0) {
							currgame.getPlayer().setTreasury(currgame.getPlayer().getTreasury()
									- currcity.getEconomicalBuildings().get(i).getUpgradeCost());
							currcity.getEconomicalBuildings().get(i).upgrade();
						} else {
							JOptionPane.showMessageDialog(null, "Not enough gold");
						}
					}
				}
			} catch (BuildingInCoolDownException e1) {

				JOptionPane.showMessageDialog(null, "Building is in cool down. Wait for the next turn ");
				e1.printStackTrace();
			} catch (MaxLevelException e1) {

				JOptionPane.showMessageDialog(null, "Maximum level reached!!");
				e1.printStackTrace();
			}

		}

		else if (action.equals("Upgrade ArcheryRange")) {

			try {
				for (int i = 0; i < currcity.getMilitaryBuildings().size(); i++) {
					if (currcity.getMilitaryBuildings().get(i) instanceof ArcheryRange) {
						if (currgame.getPlayer().getTreasury()
								- currcity.getMilitaryBuildings().get(i).getUpgradeCost() >= 0) {
							currgame.getPlayer().setTreasury(currgame.getPlayer().getTreasury()
									- currcity.getMilitaryBuildings().get(i).getUpgradeCost());
							currcity.getMilitaryBuildings().get(i).upgrade();
						} else {
							JOptionPane.showMessageDialog(null, "Not enough gold");
						}

					}
				}
			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(null, "Building is in cool down. Wait for the next turn ");
				e1.printStackTrace();
			} catch (MaxLevelException e1) {

				JOptionPane.showMessageDialog(null, "Maximum level reached!!");
				e1.printStackTrace();
			}

		} else if (action.equals("Upgrade Barracks")) {

			try {
				for (int i = 0; i < currcity.getMilitaryBuildings().size(); i++) {
					if (currcity.getMilitaryBuildings().get(i) instanceof Barracks) {
						if (currgame.getPlayer().getTreasury()
								- currcity.getMilitaryBuildings().get(i).getUpgradeCost() >= 0) {
							currgame.getPlayer().setTreasury(currgame.getPlayer().getTreasury()
									- currcity.getMilitaryBuildings().get(i).getUpgradeCost());
							currcity.getMilitaryBuildings().get(i).upgrade();
						} else {
							JOptionPane.showMessageDialog(null, "Not enough gold");

						}
					}
				}
			} catch (BuildingInCoolDownException e1) {

				JOptionPane.showMessageDialog(null, "Building is in cool down. Wait for the next turn ");

				e1.printStackTrace();
			} catch (MaxLevelException e1) {

				JOptionPane.showMessageDialog(null, "Maximum level reached!!");

				e1.printStackTrace();
			}

		} else if (action.equals("Upgrade Stable")) {

			try {
				for (int i = 0; i < currcity.getMilitaryBuildings().size(); i++) {
					if (currcity.getMilitaryBuildings().get(i) instanceof Stable) {
						if (currgame.getPlayer().getTreasury()
								- currcity.getMilitaryBuildings().get(i).getUpgradeCost() >= 0) {
							currgame.getPlayer().setTreasury(currgame.getPlayer().getTreasury()
									- currcity.getMilitaryBuildings().get(i).getUpgradeCost());
							currcity.getMilitaryBuildings().get(i).upgrade();
						} else {

							JOptionPane.showMessageDialog(null, "Not enough gold");

						}
					}
				}
			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(null, "Building is in cool down. Wait for the next turn ");
				e1.printStackTrace();
			} catch (MaxLevelException e1) {
				JOptionPane.showMessageDialog(null, "Maximum level reached!!");
				e1.printStackTrace();
			}

		} else if (action.equals("ArcheryRange Recruit")) {

			try {
				for (int i = 0; i < currcity.getMilitaryBuildings().size(); i++) {
					if (currcity.getMilitaryBuildings().get(i) instanceof ArcheryRange) {

						currgame.getPlayer().recruitUnit("archer", currcity.getName());
					}
				}
			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(null, "Building is in cool down. Wait for the next turn ");
				e1.printStackTrace();
			} catch (MaxRecruitedException e1) {
				JOptionPane.showMessageDialog(null, "Max recruited units reached, please wait till next turn. ");
				e1.printStackTrace();
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "Not enough gold");

				e1.printStackTrace();
			}

		} else if (action.equals("Stable Recruit")) {

			try {
				for (int i = 0; i < currcity.getMilitaryBuildings().size(); i++) {
					if (currcity.getMilitaryBuildings().get(i) instanceof Stable) {

						currgame.getPlayer().recruitUnit("cavalry", currcity.getName());
					}
				}
			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(null, "Building is in cool down. Wait for the next turn ");
				e1.printStackTrace();
			} catch (MaxRecruitedException e1) {
				JOptionPane.showMessageDialog(null, "Max recruited units reached, please wait till next turn. ");
				e1.printStackTrace();
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "Not enough gold");
				e1.printStackTrace();
			}

		} else if (action.equals("Barracks Recruit")) {

			try {
				for (int i = 0; i < currcity.getMilitaryBuildings().size(); i++) {
					if (currcity.getMilitaryBuildings().get(i) instanceof Barracks) {

						currgame.getPlayer().recruitUnit("infantry", currcity.getName());
					}
				}
			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(null, "Building is in cool down. Wait for the next turn ");
				e1.printStackTrace();
			} catch (MaxRecruitedException e1) {
				JOptionPane.showMessageDialog(null, "Max recruited units reached, please wait till next turn. ");
				e1.printStackTrace();
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "Not enough gold");
				e1.printStackTrace();
			}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		} else if (action.equals("GAME OVER")) {
			java.awt.Window win[] = java.awt.Window.getWindows();
			for (int i = 0; i < win.length; i++) {
				win[i].dispose();
			}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			

		} else if (action.equals("EndTurn")) {
			currgame.endTurn();
			if (currgame.getPlayer().getControlledCities().size() == 3
					|| currgame.getCurrentTurnCount() > currgame.getMaxTurnCount()) {
				currgame.isGameOver();
			}
			Launcher.mainF.setTitle("Player Name: " + currgame.getPlayer().getName() + "  Current food: "
					+ currgame.getPlayer().getFood() + "  Current Turn: " + currgame.getCurrentTurnCount()
					+ "  Current Gold: " + currgame.getPlayer().getTreasury());
			/*
			 * playerLabel.setText("Player Name: " + game.getPlayer().getName());
			 * foodLabel.setText("  Current food: " + game.getPlayer().getFood());
			 * goldLabel.setText("  Current Gold: " + game.getPlayer().getTreasury());
			 * turnsLabel.setText("  Current Turn: " + game.getCurrentTurnCount());
			 */
		} else if (action.equals("Initiate an Army")) {

			JFrame f = new JFrame();
			// f.setTitle("Player Name: " + currgame.getPlayer().getName() + " Current food:
			// " + currgame.getPlayer().getFood() + " Current Turn: " +
			// currgame.getCurrentTurnCount() + " Current Gold: " +
			// currgame.getPlayer().getTreasury());

			f.setVisible(true);
			f.setLayout(new FlowLayout());
			f.setSize(1600, 900);
			f.setLocation(0, 20);
			f.setResizable(false);
			f.setLayout(new BorderLayout());
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			JLabel l = new JLabel(currcity.getName());
			// l.setBackground(Color.RED);
			p.add(l);
			f.add(p, BorderLayout.NORTH);

			JPanel p2 = new JPanel();
			p2.setLayout(new FlowLayout());
			for (int i = 0; i < currcity.getDefendingArmy().getUnits().size(); i++) {
				JButton b;
				if (currcity.getDefendingArmy().getUnits().get(i) instanceof Archer) {
					b = new JButton(" Archer  " + " level " + currcity.getDefendingArmy().getUnits().get(i).getLevel()
							+ " and its count is "
							+ currcity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
							+ " and its max count is "
							+ currcity.getDefendingArmy().getUnits().get(i).getMaxSoldierCount() + "  ");
					b.addActionListener(new helper());
					b.setActionCommand("" + i);
					f.add(b);
				} else if (currcity.getDefendingArmy().getUnits().get(i) instanceof Infantry) {
					b = new JButton(" Infantry  " + " level " + currcity.getDefendingArmy().getUnits().get(i).getLevel()
							+ " and its count is "
							+ currcity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
							+ " and its max count is "
							+ currcity.getDefendingArmy().getUnits().get(i).getMaxSoldierCount() + "  ");
					b.addActionListener(new helper());
					b.setActionCommand("" + i);
					f.add(b);

				} else {
					b = new JButton("Cavalry  " + " level " + currcity.getDefendingArmy().getUnits().get(i).getLevel()
							+ " and its count is "
							+ currcity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
							+ " and its max count is "
							+ currcity.getDefendingArmy().getUnits().get(i).getMaxSoldierCount() + "  ");
					b.addActionListener(new helper());
					b.setActionCommand("" + i);
					f.add(b);
				}

				b.setBackground(Color.orange);
				p2.add(b);

			}
			f.add(p2, BorderLayout.CENTER);

		}
		for (int i = 0; i < currgame.getPlayer().getControlledArmies().size(); i++) {
			int k = i + 1;
			if (action.equals("Army" + k)) {
				JFrame f = new JFrame();
				// f.setTitle("Player Name: " + currgame.getPlayer().getName() + " Current food:
				// " + currgame.getPlayer().getFood() + " Current Turn: " +
				// currgame.getCurrentTurnCount() + " Current Gold: " +
				// currgame.getPlayer().getTreasury());

				f.setLayout(new FlowLayout());
				f.setVisible(true);
				f.setSize(1600, 900);
				f.setLocation(0, 20);
				f.setResizable(false);
				JButton b = new JButton("Relocate");
				b.addActionListener(new helper());
				b.setActionCommand("Relocate" + i);
				f.add(b);
				getUnits(currcity, currgame.getPlayer().getControlledArmies().get(i), f);
				currarmy = currgame.getPlayer().getControlledArmies().get(i);
			}
		}

		for (int i = 0; i < currgame.getPlayer().getControlledArmies().size(); i++) {
			if (action.equals("Relocate" + i)) {
				JFrame f = new JFrame();
				// f.setTitle("Player Name: " + currgame.getPlayer().getName() + " Current food:
				// " + currgame.getPlayer().getFood() + " Current Turn: " +
				// currgame.getCurrentTurnCount() + " Current Gold: " +
				// currgame.getPlayer().getTreasury());

				f.setLayout(new FlowLayout());
				f.setVisible(true);
				f.setSize(1600, 900);
				f.setLocation(0, 20);
				f.setResizable(false);
				getAllArmies(currcity, currgame, f, currarmy);

			}
		}

		for (int i = 0; i < currcity.getDefendingArmy().getUnits().size(); i++) {
			String u = "";
			if (currcity.getDefendingArmy().getUnits().get(i) instanceof Archer) {
				u = "Archer  " + " level " + currcity.getDefendingArmy().getUnits().get(i).getLevel()
						+ "and its count is " + currcity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
						+ "and its max count is " + currcity.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()
						+ "  ";
			} else if (currcity.getDefendingArmy().getUnits().get(i) instanceof Infantry) {
				u = "Infantry  " + " level " + currcity.getDefendingArmy().getUnits().get(i).getLevel()
						+ "and its count is " + currcity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
						+ "and its max count is " + currcity.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()
						+ "  ";

			} else {
				u = "Cavalry  " + " level " + currcity.getDefendingArmy().getUnits().get(i).getLevel()
						+ "and its count is " + currcity.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
						+ "and its max count is " + currcity.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()
						+ "  ";

			}
			if (action.equals("DefendingUnit" + i)) {
				try {
					currarmy.relocateUnit(currcity.getDefendingArmy().getUnits().get(i));
				} catch (MaxCapacityException e1) {
					JOptionPane.showMessageDialog(null, "Maximum capacity reached");
					e1.printStackTrace();
				}
			}
		}
		for (int i = 0; i < currgame.getPlayer().getControlledArmies().size(); i++) {

			if (!(currgame.getPlayer().getControlledArmies().get(i).equals(currarmy))) {

				for (int j = 0; j < currgame.getPlayer().getControlledArmies().get(i).getUnits().size(); j++) {
					String u = "";
					if (currgame.getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof Archer) {
						u = "Archer  " + " level "
								+ currgame.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()
								+ "and its count is "
								+ currgame.getPlayer().getControlledArmies().get(i).getUnits().get(j)
										.getCurrentSoldierCount()
								+ "and its max count is " + currgame.getPlayer().getControlledArmies().get(i).getUnits()
										.get(j).getMaxSoldierCount()
								+ "  ";
					} else if (currgame.getPlayer().getControlledArmies().get(i).getUnits()
							.get(j) instanceof Infantry) {
						u = "Infantry  " + " level "
								+ currgame.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()
								+ "and its count is "
								+ currgame.getPlayer().getControlledArmies().get(i).getUnits().get(j)
										.getCurrentSoldierCount()
								+ "and its max count is " + currgame.getPlayer().getControlledArmies().get(i).getUnits()
										.get(j).getMaxSoldierCount()
								+ "  ";

					} else {
						u = "Cavalry  " + " level "
								+ currgame.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel()
								+ "and its count is "
								+ currgame.getPlayer().getControlledArmies().get(i).getUnits().get(j)
										.getCurrentSoldierCount()
								+ "and its max count is " + currgame.getPlayer().getControlledArmies().get(i).getUnits()
										.get(j).getMaxSoldierCount()
								+ "  ";

					}
					if (action.equals("Army" + i + "Unit" + j)) {
						try {
							currarmy.relocateUnit(currgame.getPlayer().getControlledArmies().get(i).getUnits().get(j));

						} catch (MaxCapacityException e1) {
							JOptionPane.showMessageDialog(null, "Maximum capacity reached");
							e1.printStackTrace();
						}
					}
				}
			}
		}

		for (int i = 0; i < currcity.getDefendingArmy().getUnits().size(); i++) {
			if (action.equals("" + i)) {
				currgame.getPlayer().initiateArmy(currcity, currcity.getDefendingArmy().getUnits().get(i));

			}

		}
		/*
		 * Launcher.playerLabel.setText("Player Name: " +
		 * currgame.getPlayer().getName());
		 * Launcher.foodLabel.setText("  Current food: " +
		 * currgame.getPlayer().getFood());
		 * Launcher.goldLabel.setText("  Current Gold: " +
		 * currgame.getPlayer().getTreasury());
		 * Launcher.turnsLabel.setText("  Current Turn: " +
		 * currgame.getCurrentTurnCount());
		 */
		Launcher.mainF.setTitle("Player Name: " + currgame.getPlayer().getName() + "  Current food: "
				+ currgame.getPlayer().getFood() + "  Current Turn: " + currgame.getCurrentTurnCount()
				+ "  Current Gold: " + currgame.getPlayer().getTreasury());

		for (int i = 0; i < currgame.getPlayer().getControlledArmies().size(); i++) {
			if (currgame.getPlayer().getControlledArmies().get(i).getUnits().size() == 0)
				currgame.getPlayer().getControlledArmies().remove(currgame.getPlayer().getControlledArmies().get(i));
		}
	}

	public static City cityHelper(String cityName) {
		City c = new City("");
		for (int i = 0; i < currgame.getPlayer().getControlledCities().size(); i++) {
			if (currgame.getPlayer().getControlledCities().get(i).getName().equals(cityName))
				c = currgame.getPlayer().getControlledCities().get(i);
		}
		return c;
	}

	public static void getAllArmies(City city, Game game, JFrame f, Army now) {

		f.setVisible(true);
		f.setLayout(new FlowLayout());
		f.setSize(1600, 900);
		f.setLocation(0, 20);
		f.setResizable(false);

		JButton myDefending = new JButton("Defending Army");
		myDefending.setBackground(Color.magenta);
		f.add(myDefending);
		for (int i = 0; i < city.getDefendingArmy().getUnits().size(); i++) {
			String u = "";
			if (city.getDefendingArmy().getUnits().get(i) instanceof Archer) {
				u = "Archer  " + " level " + city.getDefendingArmy().getUnits().get(i).getLevel() + "and its count is "
						+ city.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount() + "and its max count is "
						+ city.getDefendingArmy().getUnits().get(i).getMaxSoldierCount() + "  ";
			} else if (city.getDefendingArmy().getUnits().get(i) instanceof Infantry) {
				u = "Infantry  " + " level " + city.getDefendingArmy().getUnits().get(i).getLevel()
						+ "and its count is " + city.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount()
						+ "and its max count is " + city.getDefendingArmy().getUnits().get(i).getMaxSoldierCount()
						+ "  ";

			} else {
				u = "Cavalry  " + " level " + city.getDefendingArmy().getUnits().get(i).getLevel() + "and its count is "
						+ city.getDefendingArmy().getUnits().get(i).getCurrentSoldierCount() + "and its max count is "
						+ city.getDefendingArmy().getUnits().get(i).getMaxSoldierCount() + "  ";

			}
			JButton b = new JButton(u);
			b.addActionListener(new helper());
			b.setActionCommand("DefendingUnit" + i);
			b.setBackground(Color.orange);
			f.add(b);
		}

		for (int i = 0; i < game.getPlayer().getControlledArmies().size(); i++) {
			int k = i + 1;
			if (!(game.getPlayer().getControlledArmies().get(i).equals(now))) {

				JButton myArmy = new JButton("Army" + k);
				myArmy.setBackground(Color.LIGHT_GRAY);
				f.add(myArmy);
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
					JButton b = new JButton(u);
					b.addActionListener(new helper());
					b.setActionCommand("Army" + i + "Unit" + j);
					b.setBackground(Color.orange);
					f.add(b);
				}
			}
		}
	}

	public static void getUnits(City city, Army army, JFrame f) {
		// if (army.getCurrentLocation().equals(city.getName())) {

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

			} else {
				u = "Cavalry  " + " level " + army.getUnits().get(j).getLevel() + "and its count is "
						+ army.getUnits().get(j).getCurrentSoldierCount() + "and its max count is "
						+ army.getUnits().get(j).getMaxSoldierCount() + "  ";

			}
			f.add(new JLabel(u));
		}

	}

	public static void getMyArmiesInCity(City city, Game game) {
		JFrame f = new JFrame();
		// f.setTitle("Player Name: " + currgame.getPlayer().getName() + " Current food:
		// " + currgame.getPlayer().getFood() + " Current Turn: " +
		// currgame.getCurrentTurnCount() + " Current Gold: " +
		// currgame.getPlayer().getTreasury());

		f.setVisible(true);
		f.setLayout(new FlowLayout());
		f.setSize(1600, 900);
		f.setLocation(0, 20);
		f.setResizable(false);
		// String u;
		for (int i = 0; i < game.getPlayer().getControlledArmies().size(); i++) {
			int k = i + 1;
			JButton myArmy = new JButton("Army" + k);
			myArmy.addActionListener(new helper());
			myArmy.setBackground(Color.LIGHT_GRAY);
			myArmy.setActionCommand("Army" + k);
			f.add(myArmy);

			/*
			 * if
			 * (game.getPlayer().getControlledArmies().get(i).getCurrentLocation().equals(
			 * city.getName())) {
			 * 
			 * for (int j = 0; j <
			 * game.getPlayer().getControlledArmies().get(i).getUnits().size(); j++) {
			 * String u = ""; if
			 * (game.getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof
			 * Archer) { u = "Archer  " + " level " +
			 * game.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel() +
			 * "and its count is " +
			 * game.getPlayer().getControlledArmies().get(i).getUnits().get(j)
			 * .getCurrentSoldierCount() + "and its max count is " +
			 * game.getPlayer().getControlledArmies().get(i).getUnits().get(j).
			 * getMaxSoldierCount() + "  "; } else if
			 * (game.getPlayer().getControlledArmies().get(i).getUnits().get(j) instanceof
			 * Infantry) { u = "Infantry  " + " level " +
			 * game.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel() +
			 * "and its count is " +
			 * game.getPlayer().getControlledArmies().get(i).getUnits().get(j)
			 * .getCurrentSoldierCount() + "and its max count is " +
			 * game.getPlayer().getControlledArmies().get(i).getUnits().get(j).
			 * getMaxSoldierCount() + "  "; ; } else { u = "Cavalry  " + " level " +
			 * game.getPlayer().getControlledArmies().get(i).getUnits().get(j).getLevel() +
			 * "and its count is " +
			 * game.getPlayer().getControlledArmies().get(i).getUnits().get(j)
			 * .getCurrentSoldierCount() + "and its max count is " +
			 * game.getPlayer().getControlledArmies().get(i).getUnits().get(j).
			 * getMaxSoldierCount() + "  "; ; }
			 * 
			 * // f.add(new JLabel(u)); } }
			 */
		}

	}

	public static void notMyIdleArmies(Game game) {
		JFrame f = new JFrame();
		// f.setTitle("Player Name: " + currgame.getPlayer().getName() + " Current food:
		// " + currgame.getPlayer().getFood() + " Current Turn: " +
		// currgame.getCurrentTurnCount() + " Current Gold: " +
		// currgame.getPlayer().getTreasury());

		f.setLayout(new FlowLayout());
		f.setSize(1600, 900);
		f.setLocation(0, 20);
		f.setResizable(false);
		f.setVisible(true);

		for (int i = 0; i < game.getPlayer().getControlledArmies().size(); i++) {

			if (!(game.getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.IDLE))) {
				String s;
				int p = i + 1;
				JButton myArmy = new JButton("Army" + p);
				myArmy.setBackground(Color.LIGHT_GRAY);
				f.add(myArmy);
				if (game.getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.MARCHING)) {
					s = "the army 's target city is " + game.getPlayer().getControlledArmies().get(i).getTarget()
							+ "the distance left for reaching the target is "
							+ game.getPlayer().getControlledArmies().get(i).getDistancetoTarget();
				} else {
					int sk = 0;
					for (int k = 0; k < game.getAvailableCities().size(); k++) {
						if (game.getAvailableCities().get(k).getName()
								.equals(game.getPlayer().getControlledArmies().get(i).getCurrentLocation()))
							sk = game.getAvailableCities().get(k).getTurnsUnderSiege();

					}
					s = "the besieging city is " + game.getPlayer().getControlledArmies().get(i).getCurrentLocation()
							+ "  and the turns under siege are " + sk;
				}

				for (int j = 0; j < game.getPlayer().getControlledArmies().get(i).getUnits().size(); j++) {
					String u = "type: ";
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
						;
					}
					f.add(new JLabel(s + u));
				}
			}
		}
	}
}
