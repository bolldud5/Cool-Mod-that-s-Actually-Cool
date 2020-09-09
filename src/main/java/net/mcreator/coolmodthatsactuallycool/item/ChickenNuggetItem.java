
package net.mcreator.coolmodthatsactuallycool.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.mcreator.coolmodthatsactuallycool.CoolModThatsActuallyCoolModElements;

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
			super(new Item.Properties().group(ItemGroup.FOOD).maxStackSize(64).food((new Food.Builder()).hunger(2).saturation(1f).meat().build()));
			setRegistryName("chicken_nugget");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 8;
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}
	}
}
