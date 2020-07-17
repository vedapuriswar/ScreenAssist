package Server;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Initialize {
  
  public static int port = 3214;

  ServerSocket socket;
  DataInputStream password;
  DataOutputStream validate;

  Initialize(String value){
    
    Robot robo;
    Rectangle rect;

    try{
      
      System.out.println("Waiting for connection ");
      socket = new ServerSocket(port);
      
      GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice gDev = gEnv.getDefaultScreenDevice();

      Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
      
      rect = new Rectangle(dimension);
      robo = new Robot(gDev);

      while(true){
        Socket s = socket.accept();
        password = new DataInputStream(s.getInputStream());
        validate = new DataOutputStream(s.getOutputStream());
        String pwd = password.readUTF();
        if(pwd.equals(value)){
          validate.writeUTF("Valid");
          validate.writeUTF(String.valueOf(dimension.getWidth()));
          validate.writeUTF(String.valueOf(dimension.getHeight()));
          new ShareScreen(s, robo, rect);
          new GetEvents(s, robo);
        }else{
          validate.writeUTF("Invalid");
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}