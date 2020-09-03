package net.mcreator.coolmodthatsactuallycool.procedures;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class SoulStoneEntityWalksOnTheBlockProcedure extends CoolModThatsActuallyCoolModElements.ModElement {

	public SoulStoneEntityWalksOnTheBlockProcedure(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 20);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SoulStoneEntityWalksOnTheBlock!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		boolean AreadyStolen = false;
		if ((!((((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get(0) : ItemStack.EMPTY)
				.getItem() == new ItemStack(Items.IRON_BOOTS, (int) (1)).getItem())
				|| (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get(0) : ItemStack.EMPTY)
						.getItem() == new ItemStack(Items.GOLDEN_BOOTS, (int) (1)).getItem()))
				|| ((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get(0) : ItemStack.EMPTY)
						.getItem() == new ItemStack(Items.CHAINMAIL_BOOTS, (int) (1)).getItem())
						|| (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get(0) : ItemStack.EMPTY)
								.getItem() == new ItemStack(Items.LEATHER_BOOTS, (int) (1)).getItem())))
				|| (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get(0) : ItemStack.EMPTY)
						.getItem() == new ItemStack(Items.DIAMOND_BOOTS, (int) (1)).getItem())))) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getFoodStats()
						.setFoodLevel((int) (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).getFoodStats().getFoodLevel() : 0) - 1));
		}

	}

}
