package src.com.red.innopolis.Server;

import com.red.innopolis.Client.Packets.OPacket;
import com.red.innopolis.Server.Packets.PacketMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by _red_ on 18.06.17.
 */
public class ServerLoader {
    private static final int PORT = 1488;
    private static ServerSocket serverSocket;
    private static ServerHandler handler;
    public static Map<Socket , ClientHandler> handlers = new HashMap<>();
    
    public static void main (String[] args) {
        start();
        handle();
        end();
    }
    
    private static void start() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void handle() {
        handler = new ServerHandler(serverSocket);
        handler.start();
        readChat();
    }
    
    public static void sendPacket(Socket receiver, PacketMessage packet) {
        try {
            DataOutputStream data = new DataOutputStream(receiver.getOutputStream());
            data.writeShort(packet.getID());
            packet.write(data);
            data.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void readChat() {
        Scanner reader = new Scanner(System.in);
        while (true) {
            if (reader.hasNextLine()) {
                String str = reader.nextLine();
//                System.out.println(str);
                if (str.equals("/END")) end();
                else System.out.println("Unknown command!!! Try again...");
            } else
                try {
                    
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
        }
    }
    
    public static ServerHandler getServerHandler () {
        return handler;
    }
    
    private static void end() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    
    public static ClientHandler getHandler (Socket socket) {
        return handlers.get(socket);
    }
    
    public static void invalidate (Socket socket) {
        handlers.remove(socket);
    }
}
