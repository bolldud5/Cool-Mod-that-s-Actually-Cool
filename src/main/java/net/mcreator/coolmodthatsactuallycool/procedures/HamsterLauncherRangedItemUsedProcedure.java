package net.mcreator.coolmodthatsactuallycool.procedures;

import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;

import net.mcreator.coolmodthatsactuallycool.CoolModThatsActuallyCoolModElements;

import java.util.Map;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class HamsterLauncherRangedItemUsedProcedure extends CoolModThatsActuallyCoolModElements.ModElement {
	public HamsterLauncherRangedItemUsedProcedure(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 10);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure HamsterLauncherRangedItemUsed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setMotionMultiplier(null, new Vec3d(0.25D, (double) 0.05F, 0.25D));
	}
}
