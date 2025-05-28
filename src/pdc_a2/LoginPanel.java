/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author 64210
 */
public class LoginPanel extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private HotelView view;

    public LoginPanel(HotelView view) {
        this.view = view;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Welcome to Hotel Booking System");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");
        //bach button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> {
            HotelView newView = new HotelView();
            HotelModel model = new HotelModel(newView);
            HotelController controller = new HotelController(newView, model);
            newView.addActionListener(controller);
            newView.setVisible(true);
            SwingUtilities.getWindowAncestor(this).dispose(); // close login window
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(backButton, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(userLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(loginButton, gbc);
        gbc.gridx = 1;
        add(signUpButton, gbc);

        loginButton.addActionListener(e -> handleLogin());
        signUpButton.addActionListener(e -> handleSignUp());
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        UserDatabase db = new UserDatabase();
        String role = db.checkLogin(username, password);

        if (role == null || !role.equalsIgnoreCase("receptionist")) {
            JOptionPane.showMessageDialog(this, "Only receptionist login is allowed.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Logged in as receptionist
        JOptionPane.showMessageDialog(this, "Login successful. Welcome, receptionist!");

        //open a bookings view or new panel
        ReceptionistPanel receptionistPanel = new ReceptionistPanel();
        view.setContentPane(receptionistPanel);
        view.revalidate();
    }

    private void handleSignUp() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username and password.", "Sign Up Failed", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UserDatabase db = new UserDatabase();
        boolean success = db.createUser(username, password);

        if (success) {
            JOptionPane.showMessageDialog(this, "Sign-up successful! You can now log in.");
        } else {
            JOptionPane.showMessageDialog(this, "Username already exists or DB error.", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
