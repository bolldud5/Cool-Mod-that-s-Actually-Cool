
package net.mcreator.coolmodthatsactuallycool.item;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class ChickenNuggetItem extends CoolModThatsActuallyCoolModElements.ModElement {

	@ObjectHolder("cool_mod_thats_actually_cool:chicken_nugget")
	public static final Item block = null;

	public ChickenNuggetItem(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 22);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}

	public static class FoodItemCustom extends Item {

		public FoodItemCustom() {
			super(new Item.Properties().group(ItemGroup.FOOD).maxStackSize(64)
					.food((new Food.Builder()).hunger(5).saturation(0.3f).setAlwaysEdible().meat().build()));
			setRegistryName("chicken_nugget");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}

	}

}
