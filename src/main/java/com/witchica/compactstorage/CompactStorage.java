package com.witchica.compactstorage;

import com.witchica.compactstorage.feature.compactchest.CompactChestBlock;
import com.witchica.compactstorage.feature.compactchest.CompactChestBlockEntity;
import com.witchica.compactstorage.util.CompactStorageFunctionLibrary;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Arrays;

@Mod(CompactStorage.MOD_ID)
public class CompactStorage {
    public static final String MOD_ID = "compact_storage";

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.Blocks.createBlocks(MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.Items.createItems(MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static DeferredBlock<Block>[] COMPACT_CHEST_BLOCKS = new DeferredBlock[16];
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COMPACT_STORAGE_CREATIVE_MODE_TAB = CREATIVE_MODE_TABS.register("compact_storage", () ->
        CreativeModeTab.builder()
        .icon(() -> new ItemStack(Blocks.CHEST))
        .title(Component.translatable("itemGroup.compact_storage.general"))
        .displayItems((params, output) -> {
            for(int i = 0; i < 16; i++) {
                output.accept(COMPACT_CHEST_BLOCKS[i].get());
            }
        })
    .build());

    public static Block[] mapRegistryObjectsToBlocks(DeferredBlock<Block>[] blocks) {
        return (Block[]) Arrays.stream(blocks).map((block) -> block.get()).toArray();
    }

    public static DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> COMPACT_CHEST_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register("compact_chest", () -> BlockEntityType.Builder.of(CompactChestBlockEntity::new, mapRegistryObjectsToBlocks(COMPACT_CHEST_BLOCKS)).build(null));

    static {
        for(int i = 0; i < 16; i++) {
            COMPACT_CHEST_BLOCKS[i] = BLOCKS.register("compact_chest_" + CompactStorageFunctionLibrary.COLOURS[i], () -> new CompactChestBlock(BlockBehaviour.Properties.of().strength(3f).explosionResistance(5f).dynamicShape().sound(SoundType.METAL)));
            int finalI = i;
            ITEMS.register("compact_chest_" + CompactStorageFunctionLibrary.COLOURS[i], () -> new BlockItem(COMPACT_CHEST_BLOCKS[finalI].get(), new Item.Properties()));
        }
    }

    public CompactStorage(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        MENU_TYPES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
