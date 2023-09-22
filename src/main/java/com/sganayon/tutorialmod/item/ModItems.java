package com.sganayon.tutorialmod.item;

import com.sganayon.tutorialmod.TutorialMod;
import com.sganayon.tutorialmod.block.ModBlocks;
import com.sganayon.tutorialmod.fluid.ModFluids;
import com.sganayon.tutorialmod.item.custom.EightBallItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MODID);

    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
    public static final RegistryObject<Item> EIGHT_BALL = ITEMS.register("eight_ball", () -> new EightBallItem(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB).stacksTo(1)));
    public static final RegistryObject<Item> BLUEBERRY_SEEDS = ITEMS.register("blueberry_seeds", () -> new ItemNameBlockItem(ModBlocks.BLUEBERRY_CROP.get(), new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB).stacksTo(64)));
    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry", () -> new Item(
        new Item.Properties()
                .tab(ModCreativeModeTab.TUTORIAL_TAB)
                .stacksTo(64)
                .food(new FoodProperties.Builder().nutrition(2).saturationMod(2).build())
    ));

    public static final RegistryObject<Item> SOAP_WATER_BUCKET = ITEMS.register("soap_water_bucket", () -> new BucketItem(
            ModFluids.SOURCE_SOAP_WATER,
            new Item.Properties()
                    .tab(ModCreativeModeTab.TUTORIAL_TAB)
                    .craftRemainder(Items.BUCKET)
                    .stacksTo(1)
    ));

    public static final RegistryObject<Item> KAUPENSWORD = ITEMS.register("kaupensword", () -> new SwordItem(Tiers.DIAMOND, 10, 5f, new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));




    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
