package de.elcpt.minewar.artillery;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

/**
 * User: The Grey Ghost
 * Date: 24/12/2014
 *
 * The Startup classes for this example are called during startup, in the following order:
 *  preInitCommon
 *  preInitClientOnly
 *  initCommon
 *  initClientOnly
 *  postInitCommon
 *  postInitClientOnly
 *  See MinecraftByExample class for more information
 */
public class StartupClientOnly
{
  public static void preInitClientOnly()
  {
    // This step is necessary in order to make your block render properly when it is an item (i.e. in the inventory
    //   or in your hand or thrown on the ground).
    // It must be done on client only, and must be done after the block has been created in Common.preinit().
    final int DEFAULT_ITEM_SUBTYPE = 0;
    ModelResourceLocation simpleResourceLocation = new ModelResourceLocation("minewar:mbe01_block_simple", "inventory");
    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemBlockSimple, DEFAULT_ITEM_SUBTYPE, simpleResourceLocation);

    ModelResourceLocation debrisModelResourceLocation = new ModelResourceLocation("minewar:elcpt_block_debris", "inventory");
    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemBlockDebris, DEFAULT_ITEM_SUBTYPE, debrisModelResourceLocation);

    ModelResourceLocation scorchedDirtResourceLocation = new ModelResourceLocation("minewar:elcpt_block_scorcheddirt", "inventory");
    ModelLoader.setCustomModelResourceLocation(StartupCommon.itemBlockScorchedDirt, DEFAULT_ITEM_SUBTYPE, scorchedDirtResourceLocation);

  }

  public static void initClientOnly()
  {
  }

  public static void postInitClientOnly()
  {
  }
}
