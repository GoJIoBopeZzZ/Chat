package Innopolis.Client;

import Innopolis.Client.Packets.OPacket;
import Innopolis.Client.Packets.PacketAuthorization;
import Innopolis.Client.Packets.PacketManager;
import Innopolis.Client.Packets.PacketMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by _red_ on 18.06.17.
 */
public class ClientLoader {
    private static Socket socket;
    private static boolean sentNickname = false;
    
    public static void main (String[] args) {
        connect();
        handle();
        end();
    }
    
    public static void sendPacket (OPacket packet) {
        try {
            DataOutputStream data = new DataOutputStream(socket.getOutputStream());
            data.writeShort(packet.getID());
            packet.write(data);
            data.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void connect() {
        try {
            socket = new Socket("localhost" , 1488);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void handle() {
        Thread handler = new Thread() {
    
            @Override
            public void run () {
                while (true) {
                    try {
                        DataInputStream data = new DataInputStream(socket.getInputStream());
                        if (data.available() <= 0) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        }
                        short id = data.readShort();
                        OPacket packet = PacketManager.getPacket(id);
                        packet.read(data);
                        packet.handle();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        handler.start();
        readChat();
    }
    
    public static void end() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    
    private static void readChat() {
        Scanner reader = new Scanner(System.in);
        while (true) {
            if (reader.hasNextLine()) {
                String message = reader.nextLine();
                if (message.equals("/END")) end();
                if (!sentNickname) {
                    sentNickname = true;
                    sendPacket(new PacketAuthorization(message));
                    continue;
                }
                sendPacket(new PacketMessage(null , message ));
            } else
                try {
                    
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
        }
    }
}
