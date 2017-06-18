package src.com.red.innopolis.Server.Packets;

import com.red.innopolis.Server.ServerLoader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by _red_ on 18.06.17.
 */
public class PacketMessage extends OPacket {
    private String sender , message;
    
    public PacketMessage () {}
    
    public PacketMessage (String sender, String message) {
        this.sender = sender;
        this.message = message;
    }
    
    @Override
    public short getID () {
        return 2;
    }
    
    @Override
    public void write (DataOutputStream data) throws IOException {
        data.writeUTF(sender);
        data.writeUTF(message);
    }
    
    @Override
    public void read (DataInputStream data) throws IOException {
        message = data.readUTF();
    }
    
    @Override
    public void handle () {
        sender = ServerLoader.getHandler(getSocket()).getNickname();
        ServerLoader.handlers.keySet().forEach(s -> ServerLoader.sendPacket(s , this));
        System.out.println(String.format("[%s] %s", sender, message));
    }
}
