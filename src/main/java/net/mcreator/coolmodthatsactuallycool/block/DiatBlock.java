
package net.mcreator.coolmodthatsactuallycool.block;

import net.minecraft.block.material.Material;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class DiatBlock extends CoolModThatsActuallyCoolModElements.ModElement {

	@ObjectHolder("cool_mod_thats_actually_cool:diat")
	public static final Block block = null;

	public DiatBlock(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 18);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {

		public CustomBlock() {
			super(

					Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.5f, 10f).lightValue(0).harvestLevel(0)
							.harvestTool(ToolType.PICKAXE));

			setRegistryName("diat");
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
