package src.com.red.innopolis.Server;

import com.red.innopolis.Old.Client;
import com.red.innopolis.Server.Packets.OPacket;
import com.red.innopolis.Server.Packets.PacketManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by _red_ on 18.06.17.
 */
public class ClientHandler extends Thread {
    private final Socket client;
    private String nickname = "Guest";
    
    public String getNickname () {
        return nickname;
    }
    
    public void setNickname (String nickname) {
        this.nickname = nickname;
    }
    
    public ClientHandler (Socket client) {
        this.client = client;
//        start();
    }
    
    @Override
    public void run() {
        while (true) {
            if (!readData())
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
    
    private boolean readData () {
        try {
            DataInputStream data = new DataInputStream(client.getInputStream());
            if (data.available() <= 0) return false;
            
            short id = data.readShort();
    
            OPacket packet = PacketManager.getPacket(id);
            packet.setSocket(client);
            packet.read(data);
            packet.handle();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    public void invalidate() {
        ServerLoader.invalidate(client);
    }
}
