package Server;

import java.awt.Robot;
import java.net.Socket;
import java.util.Scanner;

public class GetEvents extends Thread{

  Socket socket;
  Robot robo;
  boolean loop;
  Scanner in;

  public GetEvents(Socket socket, Robot robo){
    this.socket = socket;
    this.robo = robo;
    this.loop = true;
    start();
  }
  public void run(){
    try{
      in = new Scanner(socket.getInputStream());
      while(loop){
        int action = in.nextInt();
				switch(action){
					case -1:
            robo.mousePress(in.nextInt());
            break;
					case -2:
            robo.mouseRelease(in.nextInt());
            break;
					case -3:
            robo.keyPress(in.nextInt());
            break;
					case -4:
            robo.keyRelease(in.nextInt());
            break;
					case -5:
            robo.mouseMove(in.nextInt(),in.nextInt());
            break;
				}
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}