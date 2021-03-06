package vtsman.MineModus.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class quantModel extends ModelBase implements IModel {

	// fields
	ModelRenderer Base;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;
	ModelRenderer Leg4;
	ModelRenderer Top1;
	ModelRenderer Top2;
	ModelRenderer Glass1;
	ModelRenderer Glass2;
	ModelRenderer Glass3;
	ModelRenderer Glass4;

	public quantModel() {
		textureWidth = 128;
		textureHeight = 64;

		Base = new ModelRenderer(this, 74, 0);
		Base.addBox(0F, 0F, 0F, 12, 1, 12);
		Base.setRotationPoint(-6F, 23F, -6F);
		Base.setTextureSize(64, 32);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		Leg1 = new ModelRenderer(this, 40, 40);
		Leg1.addBox(0F, 0F, 0F, 2, 14, 2);
		Leg1.setRotationPoint(-7F, 10F, -7F);
		Leg1.setTextureSize(64, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0F, 0F, 0F);
		Leg2 = new ModelRenderer(this, 28, 40);
		Leg2.addBox(0F, 0F, 0F, 2, 14, 2);
		Leg2.setRotationPoint(5F, 10F, 5F);
		Leg2.setTextureSize(64, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0F, 0F, 0F);
		Leg3 = new ModelRenderer(this, 16, 40);
		Leg3.addBox(0F, 0F, 0F, 2, 14, 2);
		Leg3.setRotationPoint(5F, 10F, -7F);
		Leg3.setTextureSize(64, 32);
		Leg3.mirror = true;
		setRotation(Leg3, 0F, 0F, 0F);
		Leg4 = new ModelRenderer(this, 4, 40);
		Leg4.addBox(0F, 0F, 0F, 2, 14, 2);
		Leg4.setRotationPoint(-7F, 10F, 5F);
		Leg4.setTextureSize(64, 32);
		Leg4.mirror = true;
		setRotation(Leg4, 0F, 0F, 0F);
		Top1 = new ModelRenderer(this, 74, 16);
		Top1.addBox(0F, 0F, 0F, 12, 1, 12);
		Top1.setRotationPoint(-6F, 9F, -6F);
		Top1.setTextureSize(64, 32);
		Top1.mirror = true;
		setRotation(Top1, 0F, 0F, 0F);
		Top2 = new ModelRenderer(this, 80, 32);
		Top2.addBox(0F, 0F, 0F, 8, 1, 8);
		Top2.setRotationPoint(-4F, 8F, -4F);
		Top2.setTextureSize(64, 32);
		Top2.mirror = true;
		setRotation(Top2, 0F, 0F, 0F);
		Glass1 = new ModelRenderer(this, 41, 0);
		Glass1.addBox(0F, 0F, 0F, 10, 13, 1);
		Glass1.setRotationPoint(-5F, 10F, -6F);
		Glass1.setTextureSize(64, 32);
		Glass1.mirror = true;
		setRotation(Glass1, 0F, 0F, 0F);
		Glass2 = new ModelRenderer(this, 53, 34);
		Glass2.addBox(0F, 0F, 0F, 1, 13, 10);
		Glass2.setRotationPoint(5F, 10F, -5F);
		Glass2.setTextureSize(64, 32);
		Glass2.mirror = true;
		setRotation(Glass2, 0F, 0F, 0F);
		Glass3 = new ModelRenderer(this, 41, 17);
		Glass3.addBox(0F, 0F, 0F, 10, 13, 1);
		Glass3.setRotationPoint(-5F, 10F, 5F);
		Glass3.setTextureSize(64, 32);
		Glass3.mirror = true;
		setRotation(Glass3, 0F, 0F, 0F);
		Glass4 = new ModelRenderer(this, 0, 0);
		Glass4.addBox(0F, 0F, 0F, 0, 13, 10);
		Glass4.setRotationPoint(-6F, 10F, -5F);
		Glass4.setTextureSize(64, 32);
		Glass4.mirror = true;
		setRotation(Glass4, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Base.render(f5);
		Leg1.render(f5);
		Leg2.render(f5);
		Leg3.render(f5);
		Leg4.render(f5);
		Top1.render(f5);
		Top2.render(f5);
		Glass1.render(f5);
		Glass2.render(f5);
		Glass3.render(f5);
		Glass4.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}

	@Override
	public void renderAll() {
		Base.render(0.0625F);
		Leg1.render(0.0625F);
		Leg2.render(0.0625F);
		Leg3.render(0.0625F);
		Leg4.render(0.0625F);
		Top1.render(0.0625F);
		Top2.render(0.0625F);
		Glass1.render(0.0625F);
		Glass2.render(0.0625F);
		Glass3.render(0.0625F);
		Glass4.render(0.0625F);
	}

}