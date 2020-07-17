package Client;

import java.net.*;
import javax.swing.*;

public class Run {
    
    static int port = 3214;

    public void connection(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            System.out.println("Connecting to Server. Please Wait...");
            Authenticate frame = new Authenticate(socket);
            frame.setSize(300, 80);
            frame.setLocation(500, 300);
            frame.setVisible(true);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        String ip = JOptionPane.showInputDialog("Please Enter the IP address of the server:");
        new Run().connection(ip, port);
    }
}