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
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		Tail.render(matrixStack, buffer, packedLight, packedOverlay);
		Head.render(matrixStack, buffer, packedLight, packedOverlay);
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.Tail.rotateAngleY = f4 / (180F / (float) Math.PI);
	}
}