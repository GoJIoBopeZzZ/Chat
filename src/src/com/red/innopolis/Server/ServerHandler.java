package src.com.red.innopolis.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by _red_ on 18.06.17.
 */
public class ServerHandler extends Thread {
    private final ServerSocket serverSocket;
    
    ServerHandler(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    
    @Override
    public void run() {
        while (true) {
//            Обработка клиентов
            try {
                Socket client = serverSocket.accept();
                ClientHandler handler = new ClientHandler(client);
                handler.start();
                ServerLoader.handlers.put(client , handler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
