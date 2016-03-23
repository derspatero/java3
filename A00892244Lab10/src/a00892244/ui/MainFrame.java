/**
 * Project: A00892244Lab9
 * File: MainFrame.java
 * Date: March 16, 2016
 * Time: 10:32:18 AM
 */

package a00892244.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a00892244.data.Player;
import a00892244.database.PlayerDao;

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
	private PlayerDao playerDao;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 * @throws SQLException
	 */
	public MainFrame(PlayerDao playerDao) throws SQLException, Exception {
		this.playerDao = playerDao;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JMenu file = new JMenu("File");
		JMenuItem player = new JMenuItem("Player");
		JMenuItem exit = new JMenuItem("Exit");

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PlayerDialog dialog = new PlayerDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.displayPlayer(playerDao.getRandomPlayer());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		file.add(player);
		file.add(exit);
		menuBar.add(file);

		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainFrame.this, "Lab10\nby Edward Lambke A00892244", "About Lab10", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		help.add(about);
		menuBar.add(help);

		playerList = new JList<String>(new PlayerListModel());
		ListSelectionModel listSelectionModel = playerList.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		PlayerListController listController = new PlayerListController();
		playerList.addListSelectionListener(listController);
		contentPane.add(playerList, BorderLayout.CENTER);

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
					+ players.get(index).getEmailAddress() + " " + players.get(index).getGamerTag() + " " + players.get(index).getBirthdate().toString();
		}

	}

	private class PlayerListController implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			try {
				PlayerDialog dialog = new PlayerDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				dialog.displayPlayer(playerDao.getPlayerByID(playerList.getSelectedValue().toString().split(" ")[0]));
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
	}

}
