
package net.mcreator.coolmodthatsactuallycool.item;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class HamsterPelletItem extends CoolModThatsActuallyCoolModElements.ModElement {

	@ObjectHolder("cool_mod_thats_actually_cool:hamster_pellet")
	public static final Item block = null;

	public HamsterPelletItem(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 8);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(64));
			setRegistryName("hamster_pellet");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("Ammuntion for a Hamster Launcher"));
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

				HamsterPelletItemIsCraftedsmeltedProcedure.executeProcedure($_dependencies);
			}
		}

	}

}
