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

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import a00892244.database.PlayerDao;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * @author Edward Lambke, A00892244
 *
 */

//@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrame(PlayerDao playerDao) {
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
				JOptionPane.showMessageDialog(MainFrame.this, "Lab9\nby Edward Lambke A00892244", "About Lab9", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		help.add(about);
		menuBar.add(help);

	}

}
