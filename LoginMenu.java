package ie.gmit.proskills.Menu;

import ie.gmit.proskills.Processes.Login;
import ie.gmit.proskills.Processes.Validator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

/**
 * This class is responsible for loading the Login Page. <br>
 * The class allows the user to enter a username and password, and login.
 * 
 * @author Cian Gannon
 * @author Danielis Joni�kis
 * @author Eddie Eldridge
 */

// A LoginMenu class which utilises a JFrame to allow the user to Login to the
// MainMenu.
public class LoginMenu extends JFrame {

	private static final long serialVersionUID = 3819995849104340705L;
	private JPanel contentPane;
	private JTextField usernameInput;
	private JPasswordField passwordInput;

	/**
	 * This class displays a Login Page to the user. From here they can enter a
	 * valid username and password, allowing them to successful login and access
	 * the Main Menu. The user can also choose to go back to the Landing Page.
	 * 
	 * @param x
	 *            The x coordinates of the JFrame
	 * @param y
	 *            The y coordinates of the JFrame
	 */
	public static void main(int x, int y) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMenu frame = new LoginMenu(x, y);
					frame.setVisible(true);

					// Change the icon image for the frame
					Image img = new ImageIcon(this.getClass().getResource("/img/logo.png")).getImage();
					frame.setIconImage(img);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param x
	 *            y position of frame
	 * @param y
	 *            y position of frame
	 */
	public LoginMenu(int x, int y) {

		// Set jFrame icon to be the StoreCompare logo
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("/img/logo.png"));

		// Frame settings
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, 550, 410);
		setTitle("StoreCompare - Login");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Panel settings
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(251, 0, 293, 381);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel coverPanel = new JPanel();
		coverPanel.setBackground(Color.DARK_GRAY);
		coverPanel.setBounds(0, 199, 250, 182);
		contentPane.add(coverPanel);

		// A label for the usename input
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(11, 57, 200, 14);
		panel.add(lblNewLabel);

		// A label for the password input
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPassword.setBounds(11, 113, 200, 14);
		panel.add(lblPassword);

		// A page header label
		JLabel lblHeader = new JLabel("Login");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Jokerman", Font.PLAIN, 28));
		lblHeader.setBounds(11, 11, 272, 43);
		panel.add(lblHeader);

		// An Image label for the logo
		JLabel logoLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/img/logo.png")).getImage();
		logoLabel.setIcon(new ImageIcon(img));
		logoLabel.setBounds(0, 0, 250, 200);

		contentPane.add(logoLabel);

		// A text field for the username input
		usernameInput = new JTextField();
		usernameInput.setBounds(11, 82, 272, 20);
		panel.add(usernameInput);
		usernameInput.setColumns(10);

		// A text field for the password input
		passwordInput = new JPasswordField();
		passwordInput.setBounds(10, 137, 273, 20);
		panel.add(passwordInput);
		passwordInput.setColumns(10);

		// Login button which is handled by an action listener
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(11, 215, 272, 88);
		panel.add(loginButton);

		// A button which takes you back to the landing page
		JButton backButton = new JButton("Back");
		backButton.setBounds(11, 347, 89, 23);
		panel.add(backButton);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LandingPage.main((int) Math.round(contentPane.getLocationOnScreen().getX()),
						(int) Math.round(contentPane.getLocationOnScreen().getY()));
				CloseFrame();
			}
		});

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String username = usernameInput.getText();
				String password = new String(passwordInput.getPassword());

				// Input validation for register details
				boolean usernameValidationCheck = Validator.validateUsername(username);
				boolean passwordValidationCheck = Validator.validatePassword(password);

				boolean loginCheck = Login.main(username, password);

				// If the username details are valid
				if (usernameValidationCheck == true) {
					// If the password details are valid
					if (passwordValidationCheck == true) {
						// If the user logs in successfully, send them to the
						// main landing page of the program
						if (loginCheck) {
							MainMenu.main((int) Math.round(contentPane.getLocationOnScreen().getX()),
									(int) Math.round(contentPane.getLocationOnScreen().getY()), username);
							CloseFrame();
						} else {
							JOptionPane.showMessageDialog(null, "Username/Password are incorrect.");
							usernameInput.setText("");
							passwordInput.setText("");
						}
					} else if (passwordValidationCheck == false) {
						// Display a prompt to let the user know their username
						// is invalid
						JOptionPane.showMessageDialog(null, "Please enter a valid password. \n -No spaces allowed");

						// Set all text boxes to default
						passwordInput.setText("");
					}

					// If the validated login details DON'T match those in the
					// database
					else if (usernameValidationCheck == false) {
						// Set all text boxes to default
						usernameInput.setText("");
						passwordInput.setText("");

						// Debug
						// System.out.printf("Username %s is invalid",
						// username);

						// Display a prompt to let the user know their username
						// is invalid
						JOptionPane.showMessageDialog(null,
								"Please enter a valid username. \n - Between 3-15 characters \n - Numbers (0-9) \n -Symbols not accepted");
					}

				}
			}
		});

	}

	// A function which closes the frame
	public void CloseFrame() {
		super.dispose();
	}
}
