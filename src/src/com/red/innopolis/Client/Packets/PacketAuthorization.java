package src.com.red.innopolis.Client.Packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by _red_ on 18.06.17.
 */
public class PacketAuthorization extends OPacket {
    private String nickname;
    
    public PacketAuthorization () {
        
    }
    
    public PacketAuthorization (String nickname) {
        this.nickname = nickname;
    }
    
    @Override
    public short getID () {
        return 1;
    }
    
    @Override
    public void write (DataOutputStream data) throws IOException {
        data.writeUTF(nickname);
    }
    
    @Override
    public void read (DataInputStream data) throws IOException {
        
    }
    
    @Override
    public void handle () {
        
    }
}
