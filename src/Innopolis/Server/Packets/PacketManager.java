package Innopolis.Server.Packets;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by _red_ on 18.06.17.
 */
public class PacketManager {
    private static Map<Short , Class <? extends OPacket>> packets = new HashMap<>();
    
    static {
        packets.put((short) 1 , PacketAuthorization.class);
        packets.put((short) 2 , PacketMessage.class);
//        packets.put((short) 3 , PacketAuthorization.class);
    }
    
    public static OPacket getPacket (short id) {
        try {
            return packets.get(id).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    
//    public void read (short id, DataInputStream data) {
//        try {
//            OPacket packet = packets.get(id).newInstance();
//            packet.read(data);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
