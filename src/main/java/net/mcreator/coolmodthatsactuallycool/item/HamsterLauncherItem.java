
package net.mcreator.coolmodthatsactuallycool.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ActionResult;
import net.minecraft.network.IPacket;
import net.minecraft.item.UseAction;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.IRenderTypeBuffer;

import net.mcreator.coolmodthatsactuallycool.procedures.HamsterLauncherRangedItemUsedProcedure;
import net.mcreator.coolmodthatsactuallycool.procedures.HamsterLauncherBulletHitsBlockProcedure;
import net.mcreator.coolmodthatsactuallycool.CoolModThatsActuallyCoolModElements;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.google.common.collect.Multimap;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class HamsterLauncherItem extends CoolModThatsActuallyCoolModElements.ModElement {
	@ObjectHolder("cool_mod_thats_actually_cool:hamster_launcher")
	public static final Item block = null;
	@ObjectHolder("cool_mod_thats_actually_cool:entitybullethamster_launcher")
	public static final EntityType arrow = null;
	public HamsterLauncherItem(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 8);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemRanged());
		elements.entities.add(() -> (EntityType.Builder.<ArrowCustomEntity>create(ArrowCustomEntity::new, EntityClassification.MISC)
				.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
				.size(0.5f, 0.5f)).build("entitybullethamster_launcher").setRegistryName("entitybullethamster_launcher"));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void init(FMLCommonSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(arrow, renderManager -> new CustomRender(renderManager));
	}
	public static class ItemRanged extends Item {
		public ItemRanged() {
			super(new Item.Properties().group(ItemGroup.COMBAT).maxDamage(10000));
			setRegistryName("hamster_launcher");
		}

		@Override
		public UseAction getUseAction(ItemStack stack) {
			return UseAction.BOW;
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			entity.setActiveHand(hand);
			return new ActionResult(ActionResultType.SUCCESS, entity.getHeldItem(hand));
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 72000;
		}

		@Override
		public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot) {
			Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot);
			if (slot == EquipmentSlotType.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
						new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "ranged_item_damage", (double) -2, AttributeModifier.Operation.ADDITION));
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
						new AttributeModifier(ATTACK_SPEED_MODIFIER, "ranged_item_attack_speed", -2.4, AttributeModifier.Operation.ADDITION));
			}
			return multimap;
		}

		@Override
		public void onPlayerStoppedUsing(ItemStack itemstack, World world, LivingEntity entityLiving, int timeLeft) {
			if (!world.isRemote && entityLiving instanceof ServerPlayerEntity) {
				ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;
				double x = entity.getPosX();
				double y = entity.getPosY();
				double z = entity.getPosZ();
				if (true) {
					ItemStack stack = ShootableItem.getHeldAmmo(entity,
							e -> e.getItem() == new ItemStack(HamsterPelletItem.block, (int) (1)).getItem());
					if (stack == ItemStack.EMPTY) {
						for (int i = 0; i < entity.inventory.mainInventory.size(); i++) {
							ItemStack teststack = entity.inventory.mainInventory.get(i);
							if (teststack != null && teststack.getItem() == new ItemStack(HamsterPelletItem.block, (int) (1)).getItem()) {
								stack = teststack;
								break;
							}
						}
					}
					if (entity.abilities.isCreativeMode || stack != ItemStack.EMPTY) {
						ArrowCustomEntity entityarrow = shoot(world, entity, random, 3f, 10, 1);
						itemstack.damageItem(1, entity, e -> e.sendBreakAnimation(entity.getActiveHand()));
						if (entity.abilities.isCreativeMode) {
							entityarrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
						} else {
							if (new ItemStack(HamsterPelletItem.block, (int) (1)).isDamageable()) {
								if (stack.attemptDamageItem(1, random, entity)) {
									stack.shrink(1);
									stack.setDamage(0);
									if (stack.isEmpty())
										entity.inventory.deleteStack(stack);
								}
							} else {
								stack.shrink(1);
								if (stack.isEmpty())
									entity.inventory.deleteStack(stack);
							}
						}
						{
							Map<String, Object> $_dependencies = new HashMap<>();
							$_dependencies.put("entity", entity);
							HamsterLauncherRangedItemUsedProcedure.executeProcedure($_dependencies);
						}
					}
				}
			}
		}
	}

	@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
	public static class ArrowCustomEntity extends AbstractArrowEntity implements IRendersAsItem {
		public ArrowCustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			super(arrow, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, World world) {
			super(type, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, double x, double y, double z, World world) {
			super(type, x, y, z, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, LivingEntity entity, World world) {
			super(type, entity, world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack getItem() {
			return null;
		}

		@Override
		protected ItemStack getArrowStack() {
			return new ItemStack(HamsterPelletItem.block, (int) (1));
		}

		@Override
		protected void arrowHit(LivingEntity entity) {
			super.arrowHit(entity);
			entity.setArrowCountInEntity(entity.getArrowCountInEntity() - 1);
		}

		@Override
		public void tick() {
			super.tick();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			World world = this.world;
			Entity entity = this.getShooter();
			if (this.inGround) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					HamsterLauncherBulletHitsBlockProcedure.executeProcedure($_dependencies);
				}
				this.remove();
			}
		}
	}

	public static class CustomRender extends EntityRenderer<ArrowCustomEntity> {
		private static final ResourceLocation texture = new ResourceLocation("cool_mod_thats_actually_cool:textures/myhamster.png");
		public CustomRender(EntityRendererManager renderManager) {
			super(renderManager);
		}

		@Override
		public void render(ArrowCustomEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn,
				int packedLightIn) {
			IVertexBuilder vb = bufferIn.getBuffer(RenderType.getEntityCutout(this.getEntityTexture(entityIn)));
			matrixStackIn.push();
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90));
			matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90 + MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
			EntityModel model = new Model_Hamster_1();
			model.render(matrixStackIn, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
			matrixStackIn.pop();
			super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		}

		@Override
		public ResourceLocation getEntityTexture(ArrowCustomEntity entity) {
			return texture;
		}
	}

	// Made with Blockbench 3.6.6
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class Model_Hamster_1 extends EntityModel<Entity> {
		private final ModelRenderer Tail;
		private final ModelRenderer Head;
		private final ModelRenderer Body;
		public Model_Hamster_1() {
			textureWidth = 64;
			textureHeight = 64;
			Tail = new ModelRenderer(this);
			Tail.setRotationPoint(0.0F, 24.0F, 5.0F);
			Tail.setTextureOffset(4, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
			Head = new ModelRenderer(this);
			Head.setRotationPoint(0.0F, 19.0F, -6.0F);
			Head.setTextureOffset(0, 3).addBox(-3.0F, -2.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			Head.setTextureOffset(0, 0).addBox(2.0F, -2.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
			Head.setTextureOffset(0, 16).addBox(-3.0F, -1.0F, -2.0F, 6.0F, 4.0F, 3.0F, 0.0F, false);
			Body = new ModelRenderer(this);
			Body.setRotationPoint(0.0F, 21.0F, 0.0F);
			Body.setTextureOffset(0, 0).addBox(-4.0F, -2.0F, -6.0F, 8.0F, 5.0F, 11.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			Tail.render(matrixStack, buffer, packedLight, packedOverlay);
			Head.render(matrixStack, buffer, packedLight, packedOverlay);
			Body.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.Tail.rotateAngleY = f4 / (180F / (float) Math.PI);
		}
	}
	public static ArrowCustomEntity shoot(World world, LivingEntity entity, Random random, float power, double damage, int knockback) {
		ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, world);
		entityarrow.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setIsCritical(true);
		entityarrow.setDamage(damage);
		entityarrow.setKnockbackStrength(knockback);
		world.addEntity(entityarrow);
		double x = entity.getPosX();
		double y = entity.getPosY();
		double z = entity.getPosZ();
		world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.blaze.shoot")),
				SoundCategory.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static ArrowCustomEntity shoot(LivingEntity entity, LivingEntity target) {
		ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, entity.world);
		double d0 = target.getPosY() + (double) target.getEyeHeight() - 1.1;
		double d1 = target.getPosX() - entity.getPosX();
		double d3 = target.getPosZ() - entity.getPosZ();
		entityarrow.shoot(d1, d0 - entityarrow.getPosY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 3f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setDamage(10);
		entityarrow.setKnockbackStrength(1);
		entityarrow.setIsCritical(true);
		entity.world.addEntity(entityarrow);
		double x = entity.getPosX();
		double y = entity.getPosY();
		double z = entity.getPosZ();
		entity.world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.blaze.shoot")),
				SoundCategory.PLAYERS, 1, 1f / (new Random().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}
