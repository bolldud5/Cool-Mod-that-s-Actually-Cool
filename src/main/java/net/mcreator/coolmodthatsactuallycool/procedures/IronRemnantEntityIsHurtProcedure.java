package net.mcreator.coolmodthatsactuallycool.procedures;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class IronRemnantEntityIsHurtProcedure extends CoolModThatsActuallyCoolModElements.ModElement {

	public IronRemnantEntityIsHurtProcedure(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 6);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure IronRemnantEntityIsHurt!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure IronRemnantEntityIsHurt!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure IronRemnantEntityIsHurt!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure IronRemnantEntityIsHurt!");
			return;
		}

		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");

		if (world instanceof ServerWorld) {
			((ServerWorld) world).spawnParticle(ParticleTypes.LARGE_SMOKE, x, y, z, (int) 15, 0, 0, 0, 0.5);
		}

	}

}
