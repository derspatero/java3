/**
 * Project: A00892244Lab10
 * File: MainFrame.java
 * Date: March 22, 2016
 * Time: 10:32:18 AM
 */

package a00892244.ui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a00892244.data.Persona;
import a00892244.data.Player;
import a00892244.data.Score;
import a00892244.database.LeaderReportDao;
import a00892244.database.PersonaDao;
import a00892244.database.PlayerDao;
import a00892244.database.ScoresDao;
import a00892244.utils.LeaderBoardReportEntry;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;

/**
 * @author Edward Lambke, A00892244
 *
 */

// @SuppressWarnings("serial")
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> playerList;
	private JList<String> scoresList;
	private JList<String> personaList;
	private JList<String> reportList;
	private PlayerDao playerDao;
	private JDialog dialog;
	private ScoresDao scoresDao;
	private PersonaDao personaDao;
	private LeaderReportDao reportDao;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public MainFrame() throws SQLException, Exception {
		playerDao = new PlayerDao();
		scoresDao = new ScoresDao();
		personaDao = new PersonaDao();
		reportDao = new LeaderReportDao();

		dialog = new JDialog();
		dialog.setVisible(false);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JMenu file = new JMenu("File");
		JMenuItem exit = new JMenuItem("Quit");

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(exit);
		menuBar.add(file);

		JMenu lists = new JMenu("Lists");
		JMenuItem players = new JMenuItem("Players");
		players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					playerList = new JList<String>(new PlayerListModel());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dialog.dispose();
				dialog = new ListDialog("Players");
				dialog.add(playerList, BorderLayout.CENTER);
				dialog.setVisible(true);
			}
		});
		lists.add(players);

		JMenuItem personas = new JMenuItem("Personas");
		personas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					personaList = new JList<String>(new PersonaListModel());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dialog.dispose();
				dialog = new ListDialog("Personas");
				ListSelectionModel listSelectionModel = personaList.getSelectionModel();
				listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				PersonaListController listController = new PersonaListController();
				personaList.addListSelectionListener(listController);
				dialog.add(personaList, BorderLayout.CENTER);
				dialog.setVisible(true);
			}
		});
		lists.add(personas);

		JMenuItem scores = new JMenuItem("Scores");
		scores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					scoresList = new JList<String>(new ScoresListModel());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dialog.dispose();
				dialog = new ListDialog("Scores");
				dialog.add(scoresList, BorderLayout.CENTER);
				dialog.setVisible(true);
			}

		});
		lists.add(scores);

		menuBar.add(lists);

		JMenu reports = new JMenu("Reports");
		JMenuItem totals = new JMenuItem("Totals");
		totals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(MainFrame.this, reportDao.getGameTotals(), "Game Totals", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		reports.add(totals);

		JCheckBoxMenuItem descending = new JCheckBoxMenuItem("Descending");
		reports.add(descending);

		JMenuItem byGame = new JMenuItem("By Game");
		byGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<String> args = new ArrayList<String>();
					args.add("by_game");
					if (descending.isSelected()) {
						args.add("desc");
					}
					reportList = new JList<String>(new ReportListModel(args));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dialog.dispose();
				dialog = new ListDialog("Report");
				dialog.add(reportList, BorderLayout.CENTER);
				dialog.setVisible(true);
			}
		});
		reports.add(byGame);

		JMenuItem byCount = new JMenuItem("By Count");
		byCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<String> args = new ArrayList<String>();
					args.add("by_count");
					if (descending.isSelected()) {
						args.add("desc");
					}
					reportList = new JList<String>(new ReportListModel(args));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dialog.dispose();
				dialog = new ListDialog("Report");
				dialog.add(reportList, BorderLayout.CENTER);
				dialog.setVisible(true);
			}

		});
		reports.add(byCount);

		JMenuItem gamertag = new JMenuItem("Gamertag");
		gamertag.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				List<String> args = new ArrayList<String>();
				args.add("by_gamertag");
				String gamertag = JOptionPane.showInputDialog("enter gamertag:");
				if (gamertag != null) {
					args.add("gamertag=" + gamertag);
				}
				try {
					if (descending.isSelected()) {
						args.add("desc");
					}
					reportList = new JList<String>(new ReportListModel(args));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dialog.dispose();
				if (reportList.getModel().getSize() > 0) {
					dialog = new ListDialog("Report");
					dialog.add(reportList, BorderLayout.CENTER);
					dialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(MainFrame.this, "Gamertag '" + gamertag + "' not found", "Error", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		reports.add(gamertag);

		menuBar.add(reports);

		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainFrame.this, "Assignment 2\nby Edward Lambke A00892244", "About Assignment 2", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		help.add(about);
		menuBar.add(help);

	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private class PlayerListModel extends AbstractListModel {
		List<Player> players;

		PlayerListModel() throws SQLException, Exception {
			players = playerDao.selectAll();
		}

		@Override
		public int getSize() {
			return players.size();
		}

		@Override
		public Object getElementAt(int index) {
			return players.get(index).getIdentifier() + " " + players.get(index).getFirstName() + " " + players.get(index).getLastName() + " "
					+ players.get(index).getEmailAddress() + " " + players.get(index).getBirthdate().toString();
		}

	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private class PersonaListModel extends AbstractListModel {
		List<Persona> personas;

		PersonaListModel() throws SQLException, Exception {
			personas = personaDao.selectAll();
			System.out.println("Personas: " + personas.toString());
		}

		@Override
		public int getSize() {
			return personas.size();
		}

		@Override
		public Object getElementAt(int index) {
			return personas.get(index).getPlayerId() + " " + personas.get(index).getGamerTag() + " " + personas.get(index).getPlatform();
		}

	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private class ScoresListModel extends AbstractListModel {

		List<Score> scores;

		ScoresListModel() throws SQLException, Exception {
			scores = scoresDao.selectAll();
		}

		@Override
		public int getSize() {
			return scores.size();
		}

		@Override
		public Object getElementAt(int index) {
			return scores.get(index).getGameId() + " " + scores.get(index).getPersonaId() + " " + scores.get(index).getWin();
		}

	}

	private class ReportListModel extends AbstractListModel {

		List<LeaderBoardReportEntry> reportEntries;

		ReportListModel(List<String> args) throws SQLException, Exception {
			System.out.println("here we are: " + args.toString());
			reportEntries = reportDao.getLeaderBoardReportEntriesByQuery(args);
			System.out.println(reportEntries.toString());
		}

		@Override
		public int getSize() {
			return reportEntries.size();
		}

		@Override
		public Object getElementAt(int index) {
			return reportEntries.get(index).getWinLoss() + " " + reportEntries.get(index).getGameName() + " " + reportEntries.get(index).getGamerTag() + " "
					+ reportEntries.get(index).getPlatform();
		}

	}

	private class PersonaListController implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			dialog.dispose();
			dialog = new PlayerDialog("Persona");
			try {
				String selectedPersona = personaList.getSelectedValue().toString().split(" ")[1];
				int playerID = Integer.parseInt(personaList.getSelectedValue().toString().split(" ")[0]);
				System.out.println("Player ID " + playerID + " selected:  " + selectedPersona);
				((PlayerDialog) dialog).displayPlayer(selectedPersona, playerDao.getPlayersById(playerID).get(0));
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}
}
