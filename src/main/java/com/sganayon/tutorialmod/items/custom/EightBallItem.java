package com.sganayon.tutorialmod.items.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EightBallItem extends Item {
    public EightBallItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND){
            chatMessage(player, "Your number is: %s".formatted(getRandomNumber()));
            player.getCooldowns().addCooldown(this, 20);
        }

        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()){
            components.add(
                    Component.literal("Right clic to get a random number from 0 to 9 ?!")
                            .withStyle(ChatFormatting.GREEN)
            );
            components.add(
                    Component.keybind("item.tutorialmod.eight_ball.hovertext")
                            .withStyle(ChatFormatting.RED)
            );
        } else {
            components.add(
                    Component.literal("Press SHIFT for more info")
                            .withStyle(ChatFormatting.YELLOW)
            );
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    private int getRandomNumber(){
        return RandomSource.createNewThreadLocalInstance().nextInt(10);
    }

    private void chatMessage(Player player, String message){
        player.sendSystemMessage(Component.literal(message));
    }
}
