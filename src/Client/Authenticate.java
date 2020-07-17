package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class Authenticate extends JFrame implements ActionListener {
    private Socket socket;
    DataOutputStream password;
    DataInputStream validation;
    String validate, width, height;
    JLabel label;
    JTextField text;
    JButton submitButton;
    JPanel Panel;

    Authenticate(Socket socket) {

        label = new JLabel();
        label.setText("Enter Password:");
        text = new JTextField(20);

        this.socket = socket;

        submitButton = new JButton("SUBMIT");
        Panel = new JPanel(new GridLayout(2, 1));

        Panel.add(label);
        Panel.add(text);
        Panel.add(submitButton);

        add(Panel, BorderLayout.CENTER);
        submitButton.addActionListener(this);
        setTitle("Authentication");
    }

    public void actionPerformed(ActionEvent ae) {
        String pwd = text.getText();
        try {
            password = new DataOutputStream(socket.getOutputStream());
            validation = new DataInputStream(socket.getInputStream());
            password.writeUTF(pwd);
            validate = validation.readUTF();
        } catch (Exception e) {
            e.getMessage();
        }
        if (validate.equals("Valid")) {
            try {
                width = validation.readUTF();
                height = validation.readUTF();
            } catch (Exception e) {
                e.getMessage();
            }
            Screen screen = new Screen(socket, width, height);
            this.dispose();
        } else {
            System.out.println("Enter the Correct Password!");
            JOptionPane.showMessageDialog(this, "Incorrect Password!!", "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }
}