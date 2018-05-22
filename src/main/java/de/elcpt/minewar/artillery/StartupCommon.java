package de.elcpt.minewar.artillery;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

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
public class StartupCommon
{
  public static BlockArtillery blockArtillery;  // this holds the unique instance of your block
  public static ItemBlock itemBlockSimple;  // this holds the unique instance of the ItemBlock corresponding to your block

  public static BlockDebris blockDebris;
  public static BlockScorchedDirt blockScorchedDirt;

  public static ItemBlock itemBlockDebris;
  public static ItemBlock itemBlockScorchedDirt;

  public static void preInitCommon()
  {
    // each instance of your block should have two names:
    // 1) a registry name that is used to uniquely identify this block.  Should be unique within your mod.  use lower case.
    // 2) an 'unlocalised name' that is used to retrieve the text name of your block in the player's language.  For example-
    //    the unlocalised name might be "water", which is printed on the user's screen as "Wasser" in German or
    //    "aqua" in Italian.
    //
    //    Multiple blocks can have the same unlocalised name - for example
    //  +----RegistryName----+---UnlocalisedName----+
    //  |  flowing_water     +       water          |
    //  |  stationary_water  +       water          |
    //  +--------------------+----------------------+
    //
    blockArtillery = (BlockArtillery)(new BlockArtillery().setUnlocalizedName("mbe01_block_simple_unlocalised_name"));
    blockArtillery.setRegistryName("mbe01_block_simple_registry_name");
    ForgeRegistries.BLOCKS.register(blockArtillery);

    // We also need to create and register an ItemBlock for this block otherwise it won't appear in the inventory
    itemBlockSimple = new ItemBlock(blockArtillery);
    itemBlockSimple.setRegistryName("mbe01_block_simple_registry_name");
    ForgeRegistries.ITEMS.register(itemBlockSimple);

    blockDebris = new BlockDebris();
    registerBlock(blockDebris, "elcpt_block_debris", "block_debris_unlocalised_name");
    itemBlockDebris = new ItemBlock(blockDebris);
    registerItemBlock(itemBlockDebris, "elcpt_block_debris");

    blockScorchedDirt = new BlockScorchedDirt();
    registerBlock(blockScorchedDirt, "elcpt_block_scorcheddirt", "block_scorcheddirt_unlocalised_name");
    itemBlockScorchedDirt = new ItemBlock(blockScorchedDirt);
    registerItemBlock(itemBlockScorchedDirt, "elcpt_block_scorcheddirt");

  }

  private static void registerBlock(Block block, String registryName, String unlocalizedName) {

    block.setUnlocalizedName(unlocalizedName);
    block.setRegistryName(registryName);
    ForgeRegistries.BLOCKS.register(block);

  }

  private static void registerItemBlock(ItemBlock itemBlock, String registryName) {

    itemBlock.setRegistryName(registryName);
    ForgeRegistries.ITEMS.register(itemBlock);

  }



  public static void initCommon()
  {
  }

  public static void postInitCommon()
  {
  }

}
