package com.momosensei.all_things_tinkers.register;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.momosensei.all_things_tinkers.AllThingsTinkers.MOD_ID;

public class ATTBlock {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final RegistryObject<Block> Laomo_block = BLOCK.register("laomo_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLUE_WOOL)));

}
