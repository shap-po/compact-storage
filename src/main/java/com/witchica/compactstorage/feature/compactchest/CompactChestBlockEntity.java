package com.witchica.compactstorage.feature.compactchest;

import com.witchica.compactstorage.CompactStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CompactChestBlockEntity extends BlockEntity {
    public CompactChestBlockEntity(BlockPos pos, BlockState state) {
        super(CompactStorage.COMPACT_CHEST_BLOCK_ENTITY_TYPE.get(), pos, state);
    }
}
