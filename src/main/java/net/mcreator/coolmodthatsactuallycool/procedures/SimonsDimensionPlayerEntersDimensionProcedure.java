package net.mcreator.coolmodthatsactuallycool.procedures;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class SimonsDimensionPlayerEntersDimensionProcedure extends CoolModThatsActuallyCoolModElements.ModElement {

	public SimonsDimensionPlayerEntersDimensionProcedure(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 19);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SimonsDimensionPlayerEntersDimension!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SimonsDimensionPlayerEntersDimension!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SimonsDimensionPlayerEntersDimension!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SimonsDimensionPlayerEntersDimension!");
			return;
		}

		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");

		if (!world.getWorld().isRemote) {
			world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
							.getValue(new ResourceLocation("cool_mod_thats_actually_cool:block.s-portal.enter")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
		} else {
			world.getWorld().playSound(x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
							.getValue(new ResourceLocation("cool_mod_thats_actually_cool:block.s-portal.enter")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
		}

	}

}
