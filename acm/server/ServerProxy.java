package acm.server;

import net.minecraftforge.common.MinecraftForge;
import acm.ACM;
import acm.network.ACMPacketInfluence;

public class ServerProxy {

	// All code here is executed for only the server
    public void registerRenderers() {
            // Nothing here as the server doesn't render graphics or entities!
    }
    
    public void registerEventHandlers()
    {
    	MinecraftForge.EVENT_BUS.register(new ServerAndClientEventHandler());
    	MinecraftForge.EVENT_BUS.register(new ServerEventHandler());
    }
}
