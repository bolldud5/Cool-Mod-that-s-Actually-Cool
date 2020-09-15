
package net.mcreator.coolmodthatsactuallycool.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.coolmodthatsactuallycool.CoolModThatsActuallyCoolModElements;

import java.util.List;
import java.util.Collections;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class GreenMushroomBlockBlock extends CoolModThatsActuallyCoolModElements.ModElement {
	@ObjectHolder("cool_mod_thats_actually_cool:green_mushroom_block")
	public static final Block block = null;
	public GreenMushroomBlockBlock(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 26);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.LEAVES).sound(SoundType.WOOD).hardnessAndResistance(0.2f, 0.5f).lightValue(15));
			setRegistryName("green_mushroom_block");
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public boolean isEmissiveRendering(BlockState blockState) {
			return true;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(GreenMushroomBlock.block, (int) (1)));
		}
	}
}
