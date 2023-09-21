package com.sganayon.tutorialmod.thirst;

import net.minecraft.nbt.CompoundTag;

public class PlayerThirst {
    private int thirst;
    private static final int MIN_THIRST = 0;
    private static final int MAX_THIRST = 10;
    private static final String THIRST_NBT_NAME = "thirst";

    public int getThirst(){
        return thirst;
    }

    public void addThirst(int add){
        thirst = Math.min(thirst+add, MAX_THIRST);
    }

    public void subThirst(int sub){
        thirst = Math.max(thirst-sub, MIN_THIRST);
    }

    public void copyFrom(PlayerThirst playerThirst){
        thirst = playerThirst.thirst;
    }

    public void saveNBTData(CompoundTag compoundTag){
        compoundTag.putInt(THIRST_NBT_NAME, thirst);
    }

    public void loadNBTData(CompoundTag compoundTag){
        thirst = compoundTag.getInt(THIRST_NBT_NAME);
    }
}
