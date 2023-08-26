package com.baal.potionmod.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.baal.potionmod.PotionMod;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, PotionMod.MOD_ID);
    public static final RegistryObject<Potion> RESISTANCE_THREE_POTION = POTIONS.register("resistance_three_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1000, 2)));
    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
