
package net.mcreator.coolmodthatsactuallycool.item;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class PROGRAMMedleyItem extends CoolModThatsActuallyCoolModElements.ModElement {

	@ObjectHolder("cool_mod_thats_actually_cool:program_medley")
	public static final Item block = null;

	public PROGRAMMedleyItem(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}

	public static class MusicDiscItemCustom extends MusicDiscItem {

		public MusicDiscItemCustom() {
			super(0, CoolModThatsActuallyCoolModElements.sounds.get(new ResourceLocation("cool_mod_thats_actually_cool:program1")),
					new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("program_medley");
		}

	}

}
