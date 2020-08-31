package net.mcreator.coolmodthatsactuallycool.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.coolmodthatsactuallycool.CoolModThatsActuallyCoolModElements;

import java.util.Map;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class GlowlMobplayerCollidesBlockProcedure extends CoolModThatsActuallyCoolModElements.ModElement {
	public GlowlMobplayerCollidesBlockProcedure(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 15);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure GlowlMobplayerCollidesBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.attackEntityFrom(DamageSource.WITHER, (float) 5);
	}
}
