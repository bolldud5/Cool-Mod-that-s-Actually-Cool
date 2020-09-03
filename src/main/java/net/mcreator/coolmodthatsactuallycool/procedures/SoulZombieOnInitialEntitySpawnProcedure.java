package net.mcreator.coolmodthatsactuallycool.procedures;

import net.mcreator.coolmodthatsactuallycool.CoolModThatsActuallyCoolModElements;

import java.util.Map;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class SoulZombieOnInitialEntitySpawnProcedure extends CoolModThatsActuallyCoolModElements.ModElement {
	public SoulZombieOnInitialEntitySpawnProcedure(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 22);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SoulZombieOnInitialEntitySpawn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((Math.random() < 0.1)) {
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = new ItemStack(Items.DIAMOND_AXE, (int) (1));
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.OFF_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
		}
		if ((Math.random() < 0.1)) {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).inventory.armorInventory.set(0, new ItemStack(Items.IRON_BOOTS, (int) (1)));
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
			/* @ItemStack */}
		if ((Math.random() < 0.1)) {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).inventory.armorInventory.set(2, new ItemStack(Items.IRON_CHESTPLATE, (int) (1)));
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
			/* @ItemStack */}
		if ((Math.random() < 0.1)) {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).inventory.armorInventory.set(3, new ItemStack(Items.IRON_HELMET, (int) (1)));
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
			/* @ItemStack */}
		if ((Math.random() < 0.1)) {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).inventory.armorInventory.set(1, new ItemStack(Items.IRON_LEGGINGS, (int) (1)));
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
			/* @ItemStack */}
		if ((Math.random() < 0.1)) {
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = new ItemStack(Items.IRON_SWORD, (int) (1));
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = new ItemStack(Items.SHIELD, (int) (1));
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.OFF_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
		}
		if ((Math.random() < 0.1)) {
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = new ItemStack(Items.GOLDEN_SWORD, (int) (1));
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = new ItemStack(Items.TOTEM_OF_UNDYING, (int) (1));
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.OFF_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
		}
	}
}
