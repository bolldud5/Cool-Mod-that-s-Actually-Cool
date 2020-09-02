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
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
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

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.FrontLeg2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.Rightleg2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.LeftLeg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.BackLeg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
	}
}