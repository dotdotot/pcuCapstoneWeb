package talkToServer;


import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class testClient {
    public static void main(String[] args) {
        try {
//            String serverIp = "127.0.0.1";
        	String serverIp = "203.250.133.171";
//            Socket socket = new Socket(serverIp, 7777);
        	Socket socket = new Socket(serverIp, 8000);
            System.out.println("서버에 연결됨");
            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);

            sender.start();
            receiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}