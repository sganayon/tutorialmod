package com.sganayon.tutorialmod.networking.packet;

import com.sganayon.tutorialmod.networking.ModMessages;
import com.sganayon.tutorialmod.thirst.PlayerThirstProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DrinkWaterC2SPacket {
    private static final String MESSAGE_DRINK_WATER = "message.tutorialmod.drink_water";
    private static final String MESSAGE_NO_WATER = "message.tutorialmod.no_water";
    public DrinkWaterC2SPacket() {
    }

    public DrinkWaterC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier){
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // Here we are on the server
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            if(hasWaterAround(player, level, 2)){
                player.sendSystemMessage(
                        Component.translatable(MESSAGE_DRINK_WATER)
                        .withStyle(ChatFormatting.DARK_GRAY)
                );

                level.playSound(null, player.getOnPos(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);

                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(playerThirst -> {
                    playerThirst.addThirst(1);
                    player.sendSystemMessage(
                            Component.literal("Current thirst %s".formatted(playerThirst.getThirst()))
                                    .withStyle(ChatFormatting.DARK_GRAY)
                    );
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(playerThirst.getThirst()), player);
                });
            } else {
                player.sendSystemMessage(
                        Component.translatable(MESSAGE_NO_WATER)
                                .withStyle(ChatFormatting.RED)
                );
            }
        });
        return true;
    }

    private boolean hasWaterAround(ServerPlayer player, ServerLevel level, int size) {
        Entity entity = player.getCamera();
        HitResult result = entity.pick(size, 1, true);
        if (result.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = new BlockPos(result.getLocation());
            BlockState state = level.getBlockState(pos);
            return state.is(Blocks.WATER);
        }
        return false;
    }
}
