
package net.mcreator.coolmodthatsactuallycool.block;

import net.minecraft.block.material.Material;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class DiamondEncrustedObsidianBlock extends CoolModThatsActuallyCoolModElements.ModElement {

	@ObjectHolder("cool_mod_thats_actually_cool:diamond_encrusted_obsidian")
	public static final Block block = null;

	public DiamondEncrustedObsidianBlock(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 23);

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

					Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2f, 64000f).lightValue(0).harvestLevel(3)
							.harvestTool(ToolType.PICKAXE));

			setRegistryName("diamond_encrusted_obsidian");
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
			return Collections.singletonList(new ItemStack(Items.DIAMOND, (int) (1)));
		}

	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {

			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, new OreFeature(OreFeatureConfig::deserialize) {
				@Override
				public boolean place(IWorld world, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
					DimensionType dimensionType = world.getDimension().getType();
					boolean dimensionCriteria = false;

					if (dimensionType == SimonsDimensionDimension.type)
						dimensionCriteria = true;

					if (!dimensionCriteria)
						return false;

					return super.place(world, generator, rand, pos, config);
				}
			}.withConfiguration(new OreFeatureConfig(
					OreFeatureConfig.FillerBlockType.create("diamond_encrusted_obsidian", "diamond_encrusted_obsidian", blockAt -> {
						boolean blockCriteria = false;
						if (blockAt.getBlock() == SoulStoneBlock.block.getDefaultState().getBlock())
							blockCriteria = true;
						return blockCriteria;
					}), block.getDefaultState(), 16)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 80))));
		}
	}

}
