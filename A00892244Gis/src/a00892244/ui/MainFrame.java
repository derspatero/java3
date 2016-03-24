/**
 * Project: A00892244Lab10
 * File: MainFrame.java
 * Date: March 22, 2016
 * Time: 10:32:18 AM
 */

package a00892244.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
				// ListSelectionModel listSelectionModel = playerList.getSelectionModel();
				// listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				// PlayerListController listController = new PlayerListController();
				// playerList.addListSelectionListener(listController);
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
		players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Todo
			}
		});
		reports.add(totals);

		JCheckBoxMenuItem descending = new JCheckBoxMenuItem("Descending");
		personas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Todo
			}
		});
		reports.add(descending);

		JMenuItem byGame = new JMenuItem("By Game");
		scores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Todo
			}
		});
		reports.add(byGame);

		JMenuItem byCount = new JMenuItem("By Count");
		personas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Todo
			}
		});
		reports.add(byCount);

		JMenuItem gamertag = new JMenuItem("Gamertag");
		scores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Todo
			}
		});
		reports.add(gamertag);

		menuBar.add(reports);

		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainFrame.this, "Lab10\nby Edward Lambke A00892244", "About Lab10", JOptionPane.INFORMATION_MESSAGE);
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
			return personas.get(index).getId() + " " + personas.get(index).getGamerTag() + " " + personas.get(index).getPlayerId() + " " + personas.get(index).getPlatform();
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

	// private class PlayerListController implements ListSelectionListener {
	// @Override
	// public void valueChanged(ListSelectionEvent event) {
	// try {
	// dialog.displayPlayer(playerDao.getPlayersById(Integer.parseInt(playerList.getSelectedValue().toString().split(" ")[0])).get(0));
	// } catch (Exception e1) {
	// e1.printStackTrace();
	// }
	//
	// }
	// }

}
