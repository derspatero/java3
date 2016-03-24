/**
 * Project: A00892244Lab10
 * File: PlayerDialog.java
 * Date: March 22, 2016
 * Time: 10:32:18 AM
 */

package a00892244.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import a00892244.data.Player;
import a00892244.database.PersonaDao;
import a00892244.database.PlayerDao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class PlayerDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private PlayerDao playerDao;
	private PersonaDao personaDao;
	private String persona;

	/**
	 * Create the dialog.
	 */
	public PlayerDialog(String title) {
		super.setTitle(title);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblId = new JLabel("ID", SwingConstants.RIGHT);
		lblId.setBounds(98, 33, 19, 16);
		contentPanel.add(lblId);

		textField = new JTextField();
		textField.setBounds(120, 28, 200, 26);
		contentPanel.add(textField);
		textField.setColumns(10);
		textField.setEnabled(false);

		JLabel lblNewLabel = new JLabel("First Name", SwingConstants.RIGHT);
		lblNewLabel.setBounds(45, 61, 72, 16);
		contentPanel.add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setBounds(120, 56, 200, 26);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name", SwingConstants.RIGHT);
		lblLastName.setBounds(45, 89, 72, 16);
		contentPanel.add(lblLastName);

		textField_2 = new JTextField();
		textField_2.setBounds(120, 84, 200, 26);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblEmailAddress = new JLabel("Email Address", SwingConstants.RIGHT);
		lblEmailAddress.setBounds(28, 117, 89, 16);
		contentPanel.add(lblEmailAddress);

		textField_3 = new JTextField();
		textField_3.setBounds(120, 112, 200, 26);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblGamertag = new JLabel("Gamertag", SwingConstants.RIGHT);
		lblGamertag.setBounds(56, 145, 61, 20);
		contentPanel.add(lblGamertag);

		textField_4 = new JTextField();
		textField_4.setBounds(120, 142, 200, 26);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblBirthdate = new JLabel("Birthdate", SwingConstants.RIGHT);
		lblBirthdate.setBounds(56, 177, 61, 16);
		contentPanel.add(lblBirthdate);

		textField_5 = new JTextField();
		textField_5.setBounds(120, 172, 200, 26);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						playerDao = new PlayerDao();
						personaDao = new PersonaDao();
						Player player = new Player();
						player.setIdentifier(Integer.parseInt(textField.getText()));
						player.setFirstName(textField_1.getText());
						player.setLastName(textField_2.getText());
						player.setEmailAddress(textField_3.getText());
						player.setBirthdate(LocalDate.parse(textField_5.getText()));
						try {
							playerDao.update(player);
							personaDao.changeGamertag(persona, textField_4.getText());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						dispose();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});

			}
		}
	}

	public void displayPlayer(String persona, Player player) {
		this.persona = persona;
		textField.setText(player.getIdentifier() + "");
		textField_1.setText(player.getFirstName());
		textField_2.setText(player.getLastName());
		textField_3.setText(player.getEmailAddress());
		textField_4.setText(persona);
		textField_5.setText(player.getBirthdate().toString());
		setVisible(true);
	}

}
