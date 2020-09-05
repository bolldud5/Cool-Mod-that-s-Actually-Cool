package net.mcreator.coolmodthatsactuallycool.procedures;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class DesertWalkerOnEntityTickUpdateProcedure extends CoolModThatsActuallyCoolModElements.ModElement {

	public DesertWalkerOnEntityTickUpdateProcedure(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 24);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure DesertWalkerOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure DesertWalkerOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure DesertWalkerOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure DesertWalkerOnEntityTickUpdate!");
			return;
		}

		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");

		if ((Math.random() < 0.008)) {
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.FLAME, x, y, z, (int) 5, 0, 0, 0, 1);
			}
			if (((Math.random() < 0.2) && (Math.random() > 0))) {
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new SoulZombieEntity.CustomEntity(SoulZombieEntity.entity, world.getWorld());
					entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);

					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);

					world.addEntity(entityToSpawn);
				}
			} else if (((Math.random() < 0.4) && (Math.random() > 0.2))) {
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new GhastEntity(EntityType.GHAST, world.getWorld());
					entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);

					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);

					world.addEntity(entityToSpawn);
				}
			} else if (((Math.random() < 0.6) && (Math.random() > 0.4))) {
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new CreeperEntity(EntityType.CREEPER, world.getWorld());
					entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);

					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);

					world.addEntity(entityToSpawn);
				}
			} else if (((Math.random() < 0.8) && (Math.random() > 0.6))) {
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new ZombieEntity(EntityType.ZOMBIE, world.getWorld());
					entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);

					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);

					world.addEntity(entityToSpawn);
				}
			} else if (((Math.random() < 1) && (Math.random() > 0.8))) {
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, world.getWorld());
					entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);

					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);

					world.addEntity(entityToSpawn);
				}
			}
		}

	}

}
