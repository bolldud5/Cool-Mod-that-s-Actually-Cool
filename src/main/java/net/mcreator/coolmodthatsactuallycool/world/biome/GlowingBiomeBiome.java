
package net.mcreator.coolmodthatsactuallycool.world.biome;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.entity.EntityClassification;
import net.minecraft.block.Blocks;

import net.mcreator.coolmodthatsactuallycool.entity.BloteEntity;
import net.mcreator.coolmodthatsactuallycool.block.PurpleGrassBlock;
import net.mcreator.coolmodthatsactuallycool.CoolModThatsActuallyCoolModElements;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class GlowingBiomeBiome extends CoolModThatsActuallyCoolModElements.ModElement {
	@ObjectHolder("cool_mod_thats_actually_cool:glowing_biome")
	public static final CustomBiome biome = null;
	public GlowingBiomeBiome(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 31);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new CustomBiome());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}
	static class CustomBiome extends Biome {
		public CustomBiome() {
			super(new Biome.Builder().downfall(0f).depth(0.1f).scale(0.1f).temperature(0.7999999999999999f).precipitation(Biome.RainType.NONE)
					.category(Biome.Category.NONE).waterColor(-16776961).waterFogColor(-16776961)
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(PurpleGrassBlock.block.getDefaultState(),
							Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState())));
			setRegistryName("glowing_biome");
			DefaultBiomeFeatures.addCarvers(this);
			DefaultBiomeFeatures.addStructures(this);
			DefaultBiomeFeatures.addMonsterRooms(this);
			DefaultBiomeFeatures.addOres(this);
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.GRASS_CONFIG)
					.withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(6))));
			this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(BloteEntity.entity, 20, 4, 4));
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getGrassColor(double posX, double posZ) {
			return -13421569;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getFoliageColor() {
			return -13421569;
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public int getSkyColor() {
			return -16777216;
		}
	}
}
