package com.example.examplemod;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("seregamod")
public class SeregaMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    private static DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS,"seregamod");

    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, "seregamod");

    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, "seregamod");

    private static RegistryObject<Item> TESTO = items.register("testo",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD))
    );

    public static RegistryObject<Item> TINKOFF_AKZII = items.register("tinkoff_akzii",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD))
    );

    public static final RegistryObject<Block> TINKOFF_BLOCK = registerBlock("tinkoff_block",
            () -> new Block(AbstractBlock.Properties.of(Material.STONE))
    );

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        items.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ItemGroup.TAB_FOOD)));
    }

    public static RegistryObject<Item> PELMEN = items.register("pelmen",
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
        BLOCKS.register(bus);

        VillagerUnit.VILLAGER_PROFESSIONS.register(bus);
        VillagerUnit.POINT_OF_INTEREST_TYPES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);


    }

    private void setup(final FMLCommonSetupEvent event)
    {
        VillagerUnit.registration();
    }
}
