package com.sganayon.tutorialmod.blocks.custom;

import com.sganayon.tutorialmod.items.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class BlueberryCropBlock extends CropBlock {

    private static final int MAX_AGE = 6;
    // Override CropBlock Age (0-7) to (0-6)
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);


    public BlueberryCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.BLUEBERRY_SEEDS.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(AGE);
    }
}
