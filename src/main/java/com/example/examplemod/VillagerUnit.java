package com.example.examplemod;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.*;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.client.model.pipeline.BlockInfo;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;

public class VillagerUnit {
    public static final DeferredRegister<PointOfInterestType> POINT_OF_INTEREST_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, "seregamod");

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, "seregamod");

    public static final RegistryObject<PointOfInterestType> TINKOFF_BANK = POINT_OF_INTEREST_TYPES.register("tinkoff_bank",
            () -> new PointOfInterestType("tinkoff_bank", PointOfInterestType.getBlockStates(SeregaMod.TINKOFF_BLOCK.get()),1,1)
            );

    public static final RegistryObject<VillagerProfession> OLEG_TINKOFF = VILLAGER_PROFESSIONS.register("oleg_tinkoff",
            () -> new VillagerProfession("oleg_tinkoff", TINKOFF_BANK.get(),
                    ImmutableSet.of(),
                    ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER
            )
    );

    public static void registration() {
        try {
            ObfuscationReflectionHelper.findMethod(PointOfInterestType.class,
                    "registerBlockStates",
                    PointOfInterestType.class
            ).invoke(null,TINKOFF_BANK.get());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        VillagerTrades.ITrade[] list = new VillagerTrades.ITrade[] {
                new TinkoffTrade(() -> SeregaMod.PELMEN.get(), 20, 5, 1)
        };

        VillagerTrades.TRADES.put(OLEG_TINKOFF.get(), new Int2ObjectOpenHashMap<>(ImmutableMap.of(1,list,2,list,3,list,4,list,5,list)));
    }
}
