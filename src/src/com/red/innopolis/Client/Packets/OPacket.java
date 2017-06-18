package src.com.red.innopolis.Client.Packets;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;

/**
 * Created by _red_ on 18.06.17.
 */
public abstract class OPacket {
    public abstract short getID();
    
    public abstract void write(DataOutputStream data) throws IOException;
    
    public abstract void read(DataInputStream data) throws IOException;
    
    public abstract void handle();
}
