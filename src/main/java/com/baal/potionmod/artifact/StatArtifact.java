package com.baal.potionmod.artifact;


import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


import java.util.UUID;

public class StatArtifact extends Item {
    public StatArtifact() {
        super( new Properties().stacksTo( 1 ) );
    }

    public void onStackCreated(ItemStack itemStack, int count) {
        CompoundTag tag = itemStack.getOrCreateTag();
    }


}




