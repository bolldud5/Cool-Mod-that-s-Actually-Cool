package net.mcreator.coolmodthatsactuallycool.procedures;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class HamsterPelletItemIsCraftedsmeltedProcedure extends CoolModThatsActuallyCoolModElements.ModElement {

	public HamsterPelletItemIsCraftedsmeltedProcedure(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 9);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure HamsterPelletItemIsCraftedsmelted!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure HamsterPelletItemIsCraftedsmelted!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure HamsterPelletItemIsCraftedsmelted!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure HamsterPelletItemIsCraftedsmelted!");
			return;
		}

		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");

		if ((Math.random() < 0.03125)) {
			if (world instanceof World && !world.getWorld().isRemote) {
				Entity entityToSpawn = new HamsterEntity.CustomEntity(HamsterEntity.entity, world.getWorld());
				entityToSpawn.setLocationAndAngles(x, (y + 1), z, (float) 0, (float) 0);
				entityToSpawn.setMotion(0, 0, 0);

				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);

				world.addEntity(entityToSpawn);
			}
		}

	}

}
