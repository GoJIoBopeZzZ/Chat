package Innopolis.Server.Packets;

import Innopolis.Server.ServerLoader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by _red_ on 18.06.17.
 */
public class PacketAuthorization extends OPacket {
    private String nickname = "=0)";
    
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
    }
    
    @Override
    public void read (DataInputStream data) throws IOException {
        nickname = data.readUTF();
    }
    
    @Override
    public void handle () {
        ServerLoader.getHandler(getSocket()).setNickname(nickname);
        System.out.println("New Socked with - " + nickname);
    }
}
