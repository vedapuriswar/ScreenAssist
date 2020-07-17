package Client;

import java.awt.*;
import javax.swing.*;
import java.net.Socket;
import java.io.*;

class Screen extends Thread {
    String width, height;
    private JFrame frame = new JFrame();
    private JDesktopPane desktop = new JDesktopPane();
    private JInternalFrame internalFrame = new JInternalFrame("Server Screen", true, true, true);
    private JPanel panel = new JPanel();
    private Socket cSocket;

    public Screen(Socket cSocket, String width, String height) {
        this.width = width;
        this.height = height;
        this.cSocket = cSocket;
        start();
    }

    public void drawGUI() {
        frame.add(desktop, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.getContentPane().add(panel, BorderLayout.CENTER);
        internalFrame.setSize(100, 100);
        desktop.add(internalFrame);

        try {
            internalFrame.setMaximum(true);
        } catch (Exception e) {
            e.getMessage();
        }
        panel.setFocusable(true);
        internalFrame.setVisible(true);
    }

    public void run() {
        InputStream in = null;
        drawGUI();

        try {
            in = cSocket.getInputStream();
        } catch (Exception e) {
            e.getMessage();
        }

        new GetScreen(in, panel);
        new Events(cSocket, panel, width, height);
    }
}