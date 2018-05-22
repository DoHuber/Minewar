package de.elcpt.minewar.artillery;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * User: The Grey Ghost
 * Date: 24/12/2014
 * <p>
 * BlockArtillery is a ordinary solid cube with the six faces numbered from 0 - 5.
 * For background information on blocks see here http://greyminecraftcoder.blogspot.com.au/2014/12/blocks-18.html
 * <p>
 * For a couple of the methods below the Forge guys have marked it as deprecated.  But you still need to override those
 * "deprecated" block methods.  What they mean is "when you want to find out if a block is (eg) isOpaqueCube(),
 * don't call block.isOpaqueCube(), call iBlockState.isOpaqueCube() instead".
 * If that doesn't make sense to you yet, don't worry.  Just ignore the "deprecated method" warning.
 */
@SuppressWarnings("deprecation")
public class BlockArtillery extends Block {
    public BlockArtillery() {

        super(Material.ROCK);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);   // the block will appear on the Blocks tab in creative
        this.setResistance(Float.MAX_VALUE);

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (worldIn.isRemote) {

            return true;

        } else {

            fireShell(worldIn, pos);

        }

        return true;

    }

    private void fireShell(World worldIn, BlockPos pos) {
        System.out.println("Attempting to shoot a shell");

        EntityArtilleryShell shell = new EntityArtilleryShell(worldIn, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 100);

        shell.setLocationAndAngles(pos.getX(), pos.getY() + 2, pos.getZ(), 0.0f, 0.0f);
        shell.setPosition(pos.getX(), pos.getY() + 2, pos.getZ());

        shell.accelerationY = -0.05000000074505806d; // the gravity value stolen from arrows
        shell.motionY = 0.75f;
        shell.explosionPower = 3;

        worldIn.spawnEntity(shell);
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {

        if (worldIn.isBlockPowered(pos) && !worldIn.isRemote) {

            fireShell(worldIn, pos);

        }

    }


    //---------------------------- Random methods from the original

    // the block will render in the SOLID layer.  See http://greyminecraftcoder.blogspot.co.at/2014/12/block-rendering-18.html for more information.
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    // used by the renderer to control lighting and visibility of other blocks.
    // set to true because this block is opaque and occupies the entire 1x1x1 space
    // not strictly required because the default (super method) is true
    @Override
    public boolean isOpaqueCube(IBlockState iBlockState) {
        return true;
    }

    // used by the renderer to control lighting and visibility of other blocks, also by
    // (eg) wall or fence to control whether the fence joins itself to this block
    // set to true because this block occupies the entire 1x1x1 space
    // not strictly required because the default (super method) is true
    @Override
    public boolean isFullCube(IBlockState iBlockState) {
        return true;
    }

    // render using a BakedModel (mbe01_block_simple.json --> mbe01_block_simple_model.json)
    // not strictly required because the default (super method) is MODEL.
    @Override
    public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
        return EnumBlockRenderType.MODEL;
    }
}
