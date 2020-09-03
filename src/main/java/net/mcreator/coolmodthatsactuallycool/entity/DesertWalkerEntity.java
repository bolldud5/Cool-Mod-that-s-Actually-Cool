
package net.mcreator.coolmodthatsactuallycool.entity;

import net.minecraft.block.material.Material;

@CoolModThatsActuallyCoolModElements.ModElement.Tag
public class DesertWalkerEntity extends CoolModThatsActuallyCoolModElements.ModElement {

	public static EntityType entity = null;

	public DesertWalkerEntity(CoolModThatsActuallyCoolModElements instance) {
		super(instance, 24);

		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(3.5f, 2f))
						.build("desert_walker").setRegistryName("desert_walker");

		elements.entities.add(() -> entity);

		elements.items.add(
				() -> new SpawnEggItem(entity, -16777216, -13421773, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("desert_walker"));

	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("cool_mod_thats_actually_cool:soul_desert")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("desert")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;

			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 1, 1, 1));
		}

		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::canMonsterSpawn);

	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelWalker4Legs(), 1.3f) {

				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("cool_mod_thats_actually_cool:textures/spawner_tripod_2.png.png");
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
			experienceValue = 20;
			setNoAI(false);

		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();

			this.goalSelector.addGoal(1, new AvoidEntityGoal(this, PlayerEntity.class, (float) 10, 1.2, 1.5));
			this.goalSelector.addGoal(2, new PanicGoal(this, 0.1));
			this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1));

		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public double getMountedYOffset() {
			return super.getMountedYOffset() + -0.3;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.skeleton.ambient"));
		}

		@Override
		public void playStepSound(BlockPos pos, BlockState blockIn) {
			this.playSound((net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.bamboo.break")), 0.15f,
					1);
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.brewing_stand.brew"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			if (source == DamageSource.LIGHTNING_BOLT)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, ILivingEntityData livingdata,
				CompoundNBT tag) {
			ILivingEntityData retval = super.onInitialSpawn(world, difficulty, reason, livingdata, tag);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();

				$_dependencies.put("entity", entity);

				DesertWalkerOnInitialEntitySpawnProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}

		@Override
		public void baseTick() {
			super.baseTick();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();

				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);

				DesertWalkerOnEntityTickUpdateProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();

			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);

			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);

			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);

			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);

		}

	}

	// Made with Blockbench 3.6.6
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports

	public static class ModelWalker4Legs extends EntityModel<Entity> {
		private final ModelRenderer BackLeg1;
		private final ModelRenderer BackLeg2;
		private final ModelRenderer FrontLeg1;
		private final ModelRenderer FrontLeg2;
		private final ModelRenderer LeftLeg1;
		private final ModelRenderer LeftLeg2;
		private final ModelRenderer RightLeg;
		private final ModelRenderer Rightleg2;
		private final ModelRenderer Spawner;

		public ModelWalker4Legs() {
			textureWidth = 128;
			textureHeight = 128;

			BackLeg1 = new ModelRenderer(this);
			BackLeg1.setRotationPoint(2.0F, 5.0F, 0.0F);
			setRotationAngle(BackLeg1, 0.0F, 0.0F, -0.3927F);
			BackLeg1.setTextureOffset(40, 40).addBox(-1.7277F, -0.1502F, -2.0F, 32.0F, 4.0F, 4.0F, 0.0F, false);

			BackLeg2 = new ModelRenderer(this);
			BackLeg2.setRotationPoint(29.0F, -3.0F, 0.0F);
			BackLeg2.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 27.0F, 4.0F, 0.0F, false);

			FrontLeg1 = new ModelRenderer(this);
			FrontLeg1.setRotationPoint(-2.0F, 4.0F, -0.25F);
			setRotationAngle(FrontLeg1, 0.0F, 0.0F, 0.3927F);
			FrontLeg1.setTextureOffset(40, 48).addBox(-28.9657F, 0.391F, -1.75F, 32.0F, 4.0F, 4.0F, 0.0F, false);

			FrontLeg2 = new ModelRenderer(this);
			FrontLeg2.setRotationPoint(-28.0F, -3.0F, 0.0F);
			FrontLeg2.setTextureOffset(16, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 27.0F, 4.0F, 0.0F, false);

			LeftLeg1 = new ModelRenderer(this);
			LeftLeg1.setRotationPoint(0.0F, 4.0F, -2.0F);
			setRotationAngle(LeftLeg1, -0.3927F, 0.0F, 0.0F);
			LeftLeg1.setTextureOffset(0, 36).addBox(-2.0F, 0.8498F, -30.2723F, 4.0F, 4.0F, 32.0F, 0.0F, false);

			LeftLeg2 = new ModelRenderer(this);
			LeftLeg2.setRotationPoint(0.0F, -3.0F, -30.0F);
			LeftLeg2.setTextureOffset(16, 36).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 27.0F, 4.0F, 0.0F, false);

			RightLeg = new ModelRenderer(this);
			RightLeg.setRotationPoint(0.0F, 5.0F, 2.0F);
			setRotationAngle(RightLeg, 0.3927F, 0.0F, 0.0F);
			RightLeg.setTextureOffset(0, 0).addBox(-2.0F, -0.1502F, -1.7277F, 4.0F, 4.0F, 32.0F, 0.0F, false);

			Rightleg2 = new ModelRenderer(this);
			Rightleg2.setRotationPoint(0.0F, -3.0F, 30.0F);
			Rightleg2.setTextureOffset(0, 36).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 27.0F, 4.0F, 0.0F, false);

			Spawner = new ModelRenderer(this);
			Spawner.setRotationPoint(0.0F, 12.0F, 1.0F);
			Spawner.setTextureOffset(40, 0).addBox(-8.0F, -20.0F, -9.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			BackLeg1.render(matrixStack, buffer, packedLight, packedOverlay);
			BackLeg2.render(matrixStack, buffer, packedLight, packedOverlay);
			FrontLeg1.render(matrixStack, buffer, packedLight, packedOverlay);
			FrontLeg2.render(matrixStack, buffer, packedLight, packedOverlay);
			LeftLeg1.render(matrixStack, buffer, packedLight, packedOverlay);
			LeftLeg2.render(matrixStack, buffer, packedLight, packedOverlay);
			RightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
			Rightleg2.render(matrixStack, buffer, packedLight, packedOverlay);
			Spawner.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {

			this.FrontLeg2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.Rightleg2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.LeftLeg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.BackLeg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		}
	}

}
