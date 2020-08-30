package net.mcreator.coolmodthatsactuallycool.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coolmodthatsactuallycool.potion.BloatedEffectPotion;
import net.mcreator.coolmodthatsactuallycool.CoolModThatsActuallyCoolModElements;

import java.util.Map;

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
		if ((Math.random() < 0.33)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(BloatedEffectPotion.potion, (int) 60, (int) 1));
		}
	}
}
