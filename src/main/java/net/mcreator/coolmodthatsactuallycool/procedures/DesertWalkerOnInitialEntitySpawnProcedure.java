package net.mcreator.coolmodthatsactuallycool.procedures;

import net.mcreator.coolmodthatsactuallycool.CoolModThatsActuallyCoolModElements;

import java.util.Map;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class DesertWalkerOnInitialEntitySpawnProcedure extends CoolModThatsActuallyCoolModElements.ModElement {
	public DesertWalkerOnInitialEntitySpawnProcedure(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 25);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure DesertWalkerOnInitialEntitySpawn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, (int) 100000000, (int) 10, (false), (false)));
	}
}
