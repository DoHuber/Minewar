package de.elcpt.minewar;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = Minewar.MODID, name = Minewar.NAME, version = Minewar.VERSION)
public class Minewar {

    public static final String MODID = "de.elcpt.minewar";
    public static final String NAME = "The Great Minewar";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {


    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

    }


}
