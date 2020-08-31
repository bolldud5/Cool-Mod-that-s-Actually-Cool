
package net.mcreator.coolmodthatsactuallycool.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.IWorldReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.PushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.coolmodthatsactuallycool.CoolModThatsActuallyCoolModElements;

import java.util.List;
import java.util.Collections;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class AngeredObsidianBricksBlock extends CoolModThatsActuallyCoolModElements.ModElement {
	@ObjectHolder("cool_mod_thats_actually_cool:angered_obsidian_bricks")
	public static final Block block = null;
	public AngeredObsidianBricksBlock(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 16);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1f, 64000f).lightValue(11).harvestLevel(3)
					.harvestTool(ToolType.PICKAXE));
			setRegistryName("angered_obsidian_bricks");
		}

		@Override
		public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
			return 10f;
		}

		@Override
		public PushReaction getPushReaction(BlockState state) {
			return PushReaction.BLOCK;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
