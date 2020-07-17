package Server;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CreatePassword extends JFrame implements ActionListener{

  JButton submitButton;
  JPanel panel;
  JLabel label;
  JTextField pwd;
  String value;
  
  CreatePassword(){

    label = new JLabel();
    label.setText("Create Password");
    pwd = new JTextField(15);
    
    this.setLayout(new BorderLayout());

    submitButton = new JButton("SUBMIT");

    panel = new JPanel(new GridLayout(2, 1));
    panel.add(label);
    panel.add(pwd);
    panel.add(submitButton);

    add(panel, BorderLayout.CENTER);
    submitButton.addActionListener(this);
    setTitle("Create a Password to authenticate the Client");

  }

  public void actionPerformed(ActionEvent ae){

    value = pwd.getText();
    dispose();
    new Initialize(value);

  }

}