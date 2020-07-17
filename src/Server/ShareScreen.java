package Server;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class ShareScreen extends Thread {

  Socket socket;
  Robot robo;
  Rectangle rect;
  boolean loop;
  OutputStream outputStream;

  public ShareScreen(Socket socket, Robot robo, Rectangle rect) {
    this.loop = true;
    this.socket = socket;
    this.robo = robo;
    this.rect = rect;
    start();
  }

  public void run() {
    try {
      outputStream = socket.getOutputStream();
    } catch (Exception e) {
      e.printStackTrace();
    }
    while (loop) {
      BufferedImage image = robo.createScreenCapture(rect);
      try {
        ImageIO.write(image, "jpeg", outputStream);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        Thread.sleep(10);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}