package Client;

import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;

public class Events implements KeyListener, MouseMotionListener, MouseListener {
    private Socket cSocket;
    private JPanel panel;
    private PrintWriter writer;
    String width, height;
    double w, h;

    Events(Socket sckt, JPanel panel, String width, String height) {
        cSocket = sckt;
        this.panel = panel;
        this.width = width;
        this.height = height;
        w = Double.valueOf(width.trim()).doubleValue();
        h = Double.valueOf(width.trim()).doubleValue();

        panel.addKeyListener(this);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);

        try {
            writer = new PrintWriter(cSocket.getOutputStream());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void mouseMoved(MouseEvent e) {
        double xScale = (double) w / panel.getWidth();
        double yScale = (double) h / panel.getHeight();
        writer.println(Actions.MOVE_MOUSE.getCode());
        writer.println((int) (e.getX() * xScale));
        writer.println((int) (e.getY() * yScale));
        writer.flush();
    }

    public void mousePressed(MouseEvent e) {
        writer.println(Actions.PRESS_MOUSE.getCode());
        int button = e.getButton();
        int xButton = 16;
        if (button == 3) {
            xButton = 4;
        }
        writer.println(xButton);
        writer.flush();
    }

    public void mouseReleased(MouseEvent e) {
        writer.println(Actions.RELEASE_MOUSE.getCode());
        int button = e.getButton();
        int xButton = 16;
        if (button == 3) {
            xButton = 4;
        }
        writer.println(xButton);
        writer.flush();
    }

    public void keyPressed(KeyEvent e) {
        writer.println(Actions.PRESS_KEY.getCode());
        writer.println(e.getKeyCode());
        writer.flush();
    }

    public void keyReleased(KeyEvent e) {
        writer.println(Actions.RELEASE_KEY.getCode());
        writer.println(e.getKeyCode());
        writer.flush();
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}