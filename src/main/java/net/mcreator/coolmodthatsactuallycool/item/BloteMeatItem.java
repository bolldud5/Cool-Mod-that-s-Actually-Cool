
package net.mcreator.coolmodthatsactuallycool.item;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class BloteMeatItem extends CoolModThatsActuallyCoolModElements.ModElement {

	@ObjectHolder("cool_mod_thats_actually_cool:blote_meat")
	public static final Item block = null;

	public BloteMeatItem(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 2);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}

	public static class FoodItemCustom extends Item {

		public FoodItemCustom() {
			super(new Item.Properties().group(ItemGroup.FOOD).maxStackSize(64)
					.food((new Food.Builder()).hunger(6).saturation(6f).setAlwaysEdible().meat().build()));
			setRegistryName("blote_meat");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 100;
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
			ItemStack retval = super.onItemUseFinish(itemStack, world, entity);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();

				$_dependencies.put("entity", entity);

				BloteMeatFoodEatenProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}

		@Override
		public void onCreated(ItemStack itemstack, World world, PlayerEntity entity) {
			super.onCreated(itemstack, world, entity);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();

				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);

				BloteMeatItemIsCraftedsmeltedProcedure.executeProcedure($_dependencies);
			}
		}
	}

}
