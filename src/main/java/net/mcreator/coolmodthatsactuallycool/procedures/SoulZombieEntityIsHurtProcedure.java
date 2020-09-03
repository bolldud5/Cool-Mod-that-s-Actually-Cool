package net.mcreator.coolmodthatsactuallycool.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.coolmodthatsactuallycool.CoolModThatsActuallyCoolModElements;

import java.util.Map;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class SoulZombieEntityIsHurtProcedure extends CoolModThatsActuallyCoolModElements.ModElement {
	public SoulZombieEntityIsHurtProcedure(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 23);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SoulZombieEntityIsHurt!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SoulZombieEntityIsHurt!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SoulZombieEntityIsHurt!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SoulZombieEntityIsHurt!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SoulZombieEntityIsHurt!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((Math.random() < 0.05)) {
			if (!world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z,
						((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY));
				entityToSpawn.setPickupDelay(10);
				world.addEntity(entityToSpawn);
			}
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = new ItemStack(Blocks.VOID_AIR, (int) (1));
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
		} else if ((Math.random() < 0.1)) {
			if (!world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z,
						((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY));
				entityToSpawn.setPickupDelay(10);
				world.addEntity(entityToSpawn);
			}
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = new ItemStack(Blocks.VOID_AIR, (int) (1));
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.OFF_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
		}
	}
}
