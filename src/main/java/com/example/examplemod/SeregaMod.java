package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("seregamod")
public class SeregaMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    private static DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS,"seregamod");

    private static RegistryObject<Item> PELMEN = items.register("pelmen",
            () -> new Item(
                    new Item.Properties()
                            .tab(ItemGroup.TAB_FOOD)
                            .stacksTo(999)
                            .food(
                                    new Food.Builder()
                                            .alwaysEat()
                                            .fast()
                                            .meat()
                                            .nutrition(20)
                                            .effect(
                                                    () -> new EffectInstance(Effects.HEAL, 10000), 100
                                            )
                                            .effect(
                                                    () -> new EffectInstance(Effects.DAMAGE_RESISTANCE, 10000), 100
                                            )
                                            .effect(
                                                    () -> new EffectInstance(Effects.DAMAGE_BOOST, 10000), 100
                                            )
                                            .effect(
                                                    () -> new EffectInstance(Effects.HEALTH_BOOST, 10000), 100
                                            )
                                            .effect(
                                                    () -> new EffectInstance(Effects.LUCK, 10000), 100
                                            )
                                            .effect(
                                                    () -> new EffectInstance(Effects.MOVEMENT_SPEED, 10000), 100
                                            )
                                            .effect(
                                                    () -> new EffectInstance(Effects.NIGHT_VISION, 10000), 100
                                            )
                                            .effect(
                                                    () -> new EffectInstance(Effects.REGENERATION, 10000), 100
                                            )
                                            .build()
                            )
            ));
    public SeregaMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        // Register ourselves for server and other game events we are interested in
        items.register(bus);

        MinecraftForge.EVENT_BUS.register(this);

    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }
}
