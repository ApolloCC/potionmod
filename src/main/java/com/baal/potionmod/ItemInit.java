package com.baal.potionmod;

import com.baal.potionmod.artifact.ArtifactItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PotionMod.MOD_ID);

    public static final RegistryObject<Item> ARTIFACTITEM = ITEMS.register("artifact",
            () -> new ArtifactItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

}
