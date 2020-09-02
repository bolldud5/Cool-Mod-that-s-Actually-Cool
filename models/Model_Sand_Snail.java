// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class Model_Sand_Snail extends EntityModel<Entity> {
	private final ModelRenderer Head;
	private final ModelRenderer Bottom_Cube;
	private final ModelRenderer Center_Cube;
	private final ModelRenderer Top_Cube;

	public Model_Sand_Snail() {
		textureWidth = 64;
		textureHeight = 64;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 21.0F, 6.0F);
		Head.setTextureOffset(30, 24).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 3.0F, 0.0F, false);
		Head.setTextureOffset(0, 0).addBox(-3.0F, -3.0F, 1.0F, 6.0F, 3.0F, 0.0F, 0.0F, false);

		Bottom_Cube = new ModelRenderer(this);
		Bottom_Cube.setRotationPoint(0.0F, -17.0F, -6.0F);
		Head.addChild(Bottom_Cube);
		Bottom_Cube.setTextureOffset(32, 36).addBox(-4.0F, 12.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		Center_Cube = new ModelRenderer(this);
		Center_Cube.setRotationPoint(0.0F, 13.0F, 0.0F);
		Center_Cube.setTextureOffset(0, 24).addBox(-5.0F, -7.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);

		Top_Cube = new ModelRenderer(this);
		Top_Cube.setRotationPoint(0.0F, 24.0F, 0.0F);
		Top_Cube.setTextureOffset(0, 0).addBox(-6.0F, -30.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		Head.render(matrixStack, buffer, packedLight, packedOverlay);
		Center_Cube.render(matrixStack, buffer, packedLight, packedOverlay);
		Top_Cube.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.Center_Cube.rotateAngleY = f4 / (180F / (float) Math.PI);
		this.Top_Cube.rotateAngleY = f3 / (180F / (float) Math.PI);
	}
}