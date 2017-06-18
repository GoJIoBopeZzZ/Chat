package src.com.red.innopolis.Server.Packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by _red_ on 18.06.17.
 */
public abstract class OPacket {
    private Socket socket;
    
    public Socket getSocket() {
        return socket;
    }
    
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    public abstract short getID();
    
    public abstract void write(DataOutputStream data) throws IOException;
    
    public abstract void read(DataInputStream data) throws IOException;
    
    public abstract void handle();
}
