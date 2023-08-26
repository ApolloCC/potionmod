package com.baal.potionmod;


import com.baal.potionmod.potion.ModPotions;
import com.google.common.collect.Maps;
import com.mojang.logging.LogUtils;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;


import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("potionmod")
public class PotionMod {
    public static final String MOD_ID = "potionmod";
    // Directly reference a slf4j logger

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final UUID[] MODIFIERS = new UUID[]{
            UUID.fromString("3d8c1e3d-ea87-485f-b4fe-3597d884ee36"),
            //UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
            //UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
            //UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"),
            //UUID.fromString("4a88bc27-9563-4eeb-96d5-fe50917cc24f"),
            //UUID.fromString("fee48d8c-1b51-4c46-9f4b-c58162623a7a")
    };

    public static final Map<String, UUID> CURIO_MODIFIERS = Util.make(Maps.newHashMap(), (map) -> {
        map.put("artifact", UUID.fromString("fee58d8c-1b51-4c46-9f4b-c58262625a7b"));
    });

    public static final String NBT_SUBTAG_KEY = "ArtifactKey";
    public static final String NBT_SUBTAG_DATA_KEY = "ArtifactDataKey";


    //Items


    public static PotionMod instance;
    public PotionMod() {

        instance = this;
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);
        ItemInit.ITEMS.register(modEventBus);
        new ResourceLocation("tiered", "attribute_sync");
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onEnqueueIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        ModPotions.register(eventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
    }
    private void clientSetup(final FMLClientSetupEvent event) {
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // Some example code to dispatch IMC to another mod

        InterModComms.sendTo( PotionMod.MOD_ID, CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, ()->new SlotTypeMessage.Builder( "pocket" )
                .priority( 220 )
                .icon( ACCESSORY_SLOT_TEXTURE )
                .build()
        );

        String String = "artifact";

    }

    private static final ResourceLocation ACCESSORY_SLOT_TEXTURE = new ResourceLocation("item/empty_accessory_slot");
    private void onEnqueueIMC( InterModEnqueueEvent event ) {



    }

    private void processIMC(final InterModProcessEvent event) {
        // Some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // Register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
    public static boolean isPreferredEquipmentSlot(ItemStack stack, EquipmentSlot slot) {
        if(stack.getItem() instanceof ArmorItem) {
            ArmorItem item = (ArmorItem) stack.getItem();
            return item.getSlot().equals(slot);
        }

        if(stack.getItem() instanceof ShieldItem) {
            return slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND;
        }

        return slot == EquipmentSlot.MAINHAND;
    }



    public static boolean isPreferredCurioSlot(ItemStack stack, String slot) {
        return stack.is(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("curios", slot)));
    }







}

