package Client;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GetScreen extends Thread {

  private JPanel Panel;
  private boolean loop;
  InputStream in;
  Image image;

  public GetScreen(InputStream in, JPanel Panel) {
    this.loop = true;
    this.in = in;
    this.Panel = Panel;
    start();
  }

  public void run() {
    try {
      while (loop) {
        byte[] bytes = new byte[1024 * 1024];
        int count = 0;
        do {
          count += in.read(bytes, count, bytes.length - count);
        } while (!(count > 4 && bytes[count - 2] == (byte) -1 && bytes[count - 1] == (byte) -39));
        image = ImageIO.read(new ByteArrayInputStream(bytes));
        int width = Panel.getWidth();
        int height = Panel.getHeight();
        image = image.getScaledInstance(width, height, Image.SCALE_FAST);
        Graphics graphics = Panel.getGraphics();
        graphics.drawImage(image, 0, 0, width, height, Panel);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}