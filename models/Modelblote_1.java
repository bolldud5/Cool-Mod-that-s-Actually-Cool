// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Modelblote_1 extends EntityModel<Entity> {
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer LeftBackLeg;
	private final ModelRenderer RightBackLeg;
	private final ModelRenderer Head;
	private final ModelRenderer Neck;
	private final ModelRenderer Tail1;
	private final ModelRenderer Tail2;
	private final ModelRenderer Body;

	public Modelblote_1() {
		textureWidth = 128;
		textureHeight = 128;

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-4.0F, 19.0F, -4.0F);
		RightLeg.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(4.0F, 19.0F, -4.0F);
		LeftLeg.setTextureOffset(0, 9).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

		LeftBackLeg = new ModelRenderer(this);
		LeftBackLeg.setRotationPoint(4.0F, 19.0F, 8.0F);
		LeftBackLeg.setTextureOffset(0, 52).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

		RightBackLeg = new ModelRenderer(this);
		RightBackLeg.setRotationPoint(-4.0F, 19.0F, 8.0F);
		RightBackLeg.setTextureOffset(16, 52).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 14.0F, -12.0F);
		Head.setTextureOffset(38, 38).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 7.0F, 6.0F, 0.0F, false);

		Neck = new ModelRenderer(this);
		Neck.setRotationPoint(0.0F, 13.0F, -8.0F);
		Neck.setTextureOffset(0, 34).addBox(-6.0F, -6.0F, -4.0F, 12.0F, 11.0F, 7.0F, 0.0F, false);

		Tail1 = new ModelRenderer(this);
		Tail1.setRotationPoint(0.0F, 13.0F, 12.0F);
		Tail1.setTextureOffset(35, 51).addBox(-5.0F, -6.0F, 0.0F, 10.0F, 10.0F, 3.0F, 0.0F, false);

		Tail2 = new ModelRenderer(this);
		Tail2.setRotationPoint(0.0F, 13.0F, 15.0F);
		Tail2.setTextureOffset(35, 51).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 12.0F, 2.0F);
		Body.setTextureOffset(0, 0).addBox(-8.0F, -7.0F, -10.0F, 16.0F, 14.0F, 20.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		RightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		LeftBackLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		RightBackLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		Head.render(matrixStack, buffer, packedLight, packedOverlay);
		Neck.render(matrixStack, buffer, packedLight, packedOverlay);
		Tail1.render(matrixStack, buffer, packedLight, packedOverlay);
		Tail2.render(matrixStack, buffer, packedLight, packedOverlay);
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.LeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.LeftBackLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.RightBackLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.Neck.rotateAngleZ = f4 / (180F / (float) Math.PI);
	}
}