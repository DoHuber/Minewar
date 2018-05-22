package de.elcpt.minewar.artillery;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EntityArtilleryShell extends EntityLargeFireball {

    private static final float DEVIATION_MAGNITUDE = 0.75f;
    private boolean hasDeviated = false;

    // Constructors ---------------

    public EntityArtilleryShell(World worldIn) {
        super(worldIn);

    }

    public EntityArtilleryShell(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(worldIn, x, y, z, accelX, accelY, accelZ);

    }

    public EntityArtilleryShell(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
    }


    @Override
    protected void onImpact(@Nonnull RayTraceResult result) {

        // Modified copy of the superclass method
        if (!this.world.isRemote)
        {
            Explosion e = this.world.newExplosion(null, this.posX, this.posY, this.posZ, (float)this.explosionPower, true, true);
            this.setDead();

            List<BlockPos> affectedBlocks = e.getAffectedBlockPositions();
            List<BlockPos> blocksToReplace = new ArrayList<>();

            // Iterate trough all affected blocks, check if the surrounding blocks aren't either affected or air
            for (BlockPos element : affectedBlocks) {

                checkAllSides(affectedBlocks, blocksToReplace, element);

            }

            for (BlockPos toReplace : blocksToReplace) {

                int randomInteger = ThreadLocalRandom.current().nextInt(0, 100);
                if (randomInteger > 25) {

                    replaceWithDebris(toReplace);

                }

            }

        }

    }

    private void replaceWithDebris(BlockPos toReplace) {
        if (Blocks.DIRT.equals(getBlock(toReplace)) || Blocks.GRASS.equals(getBlock(toReplace))) {

            world.setBlockState(toReplace, StartupCommon.blockScorchedDirt.getDefaultState());

        } else {

            int randomInteger = ThreadLocalRandom.current().nextInt(0, 100);
            if (randomInteger > 25) {

                world.setBlockState(toReplace, StartupCommon.blockScorchedDirt.getDefaultState());

            } else {

                world.setBlockState(toReplace, StartupCommon.blockDebris.getDefaultState());

            }

        }
    }

    private void checkAllSides(List<BlockPos> affectedBlocks, List<BlockPos> blocksToReplace, BlockPos element) {

        addIfNecessary(element.up(), affectedBlocks, blocksToReplace);
        addIfNecessary(element.down(), affectedBlocks, blocksToReplace);
        addIfNecessary(element.north(), affectedBlocks, blocksToReplace);
        addIfNecessary(element.south(), affectedBlocks, blocksToReplace);
        addIfNecessary(element.west(), affectedBlocks, blocksToReplace);
        addIfNecessary(element.east(), affectedBlocks, blocksToReplace);

    }

    private void addIfNecessary(BlockPos blockPosition, List<BlockPos> exclusionList, List<BlockPos> listToAdd) {

        if (!exclusionList.contains(blockPosition) && !Blocks.AIR.equals(getBlock(blockPosition))) {

            listToAdd.add(blockPosition);

        }

    }

    private Block getBlock(BlockPos blockPosition){

        IBlockState blockState = world.getBlockState(blockPosition);
        return blockState.getBlock();

    }

    private IBlockState getBlockState(BlockPos blockPosition, World world){

        return world.getBlockState(blockPosition);

    }


    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!hasDeviated) {

            addRandomFlightpathDeviation();
            hasDeviated = true;

        }

    }

    private void addRandomFlightpathDeviation() {
        this.motionX += getRandomDeviation();
        this.motionZ += getRandomDeviation();

        System.out.println("Exemplary random deviation: " + Float.toString(getRandomDeviation()));
    }

    private float getRandomDeviation() {

        float randomFloat = getRandomFloatBetween(-1.0f, 1.0f);
        return randomFloat * DEVIATION_MAGNITUDE;

    }

    private float getRandomFloatBetween(float min, float max) {

        Random rand = new Random();
        return rand.nextFloat() * (max - min) + min;

    }

}
