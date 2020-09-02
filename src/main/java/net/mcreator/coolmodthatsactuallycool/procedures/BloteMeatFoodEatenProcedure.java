package net.mcreator.coolmodthatsactuallycool.procedures;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class BloteMeatFoodEatenProcedure extends CoolModThatsActuallyCoolModElements.ModElement {

	public BloteMeatFoodEatenProcedure(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 2);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure BloteMeatFoodEaten!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(BloatedEffectPotion.potion, (int) 180, (int) 1));

	}

}
