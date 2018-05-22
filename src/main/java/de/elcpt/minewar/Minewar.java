package de.elcpt.minewar;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = Minewar.MODID, name = Minewar.NAME, version = Minewar.VERSION)
public class Minewar {

    public static final String MODID = "minewar";
    public static final String NAME = "The Great Minewar";
    public static final String VERSION = "1.0";

    private static Logger logger;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide = "de.elcpt.minewar.ClientOnlyProxy", serverSide = "de.elcpt.minewar.DedicatedServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        proxy.preInit();

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        proxy.init();

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        proxy.postInit();

    }


}
