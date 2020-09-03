
package net.mcreator.coolmodthatsactuallycool.entity;

import net.minecraft.block.material.Material;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class HamsterEntity extends CoolModThatsActuallyCoolModElements.ModElement {

	public static EntityType entity = null;

	public HamsterEntity(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 8);

		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.AMBIENT).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.5f, 0.8125f)).build("hamster")
						.setRegistryName("hamster");

		elements.entities.add(() -> entity);

		elements.items
				.add(() -> new SpawnEggItem(entity, -10667520, -11188176, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("hamster"));

	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Model_Hamster_1(), 0.9f) {

				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("cool_mod_thats_actually_cool:textures/myhamster.png");
				}
			};
		});

	}

	public static class CustomEntity extends MonsterEntity {

		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 5;
			setNoAI(false);

			enablePersistence();

		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();

			this.goalSelector.addGoal(1,
					new TemptGoal(this, 1.5, Ingredient.fromItems(new ItemStack(HamsterPelletItem.block, (int) (1)).getItem()), false));
			this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
			this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 0.8));
			this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
			this.targetSelector.addGoal(6, new NearestAttackableTargetGoal(this, AgeableEntity.class, false, false));
			this.goalSelector.addGoal(7, new BreakDoorGoal(this, e -> true));

		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		@Override
		public double getMountedYOffset() {
			return super.getMountedYOffset() + 0.6;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.GOLD_NUGGET, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.endermite.ambient"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
					.getValue(new ResourceLocation("cool_mod_thats_actually_cool:mob.hamster.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
					.getValue(new ResourceLocation("cool_mod_thats_actually_cool:mob.hamster.death"));
		}

		@Override
		public boolean processInteract(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			boolean retval = true;

			super.processInteract(sourceentity, hand);

			sourceentity.startRiding(this);

			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			return retval;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();

			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);

			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);

			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10);

			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);

		}

		@Override
		public void travel(Vec3d dir) {
			Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
			if (this.isBeingRidden()) {
				this.rotationYaw = entity.rotationYaw;
				this.prevRotationYaw = this.rotationYaw;
				this.rotationPitch = entity.rotationPitch * 0.5F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.jumpMovementFactor = this.getAIMoveSpeed() * 0.15F;
				this.renderYawOffset = entity.rotationYaw;
				this.rotationYawHead = entity.rotationYaw;
				this.stepHeight = 1.0F;

				if (entity instanceof LivingEntity) {
					this.setAIMoveSpeed((float) this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());

					float forward = 0;

					float strafe = ((LivingEntity) entity).moveStrafing;

					super.travel(new Vec3d(strafe, 0, forward));
				}

				this.prevLimbSwingAmount = this.limbSwingAmount;
				double d1 = this.getPosX() - this.prevPosX;
				double d0 = this.getPosZ() - this.prevPosZ;
				float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
				if (f1 > 1.0F)
					f1 = 1.0F;
				this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
				this.limbSwing += this.limbSwingAmount;
				return;
			}
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;

			super.travel(dir);
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

}
