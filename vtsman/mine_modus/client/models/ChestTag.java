






package vtsman.mine_modus.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ChestTag extends ModelBase
{
  //fields
    ModelRenderer BG;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
  
  public ChestTag()
  {
    int textureWidth = 128;
    int textureHeight = 64;
    
      BG = new ModelRenderer(this, 0, 0);
      BG.addBox(-5F, 12F, 8F, 10, 8, 0);
      BG.setRotationPoint(0F, 0F, 0F);
      BG.setTextureSize(128, 64);
      BG.mirror = true;
      setRotation(BG, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 28, 24);
      Shape1.addBox(0F, 0F, 0F, 12, 1, 1);
      Shape1.setRotationPoint(-6F, 20F, 7F);
      Shape1.setTextureSize(128, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 66, 24);
      Shape2.addBox(0F, 0F, 0F, 12, 1, 1);
      Shape2.setRotationPoint(-6F, 11F, 7F);
      Shape2.setTextureSize(128, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 122, 24);
      Shape3.addBox(0F, 0F, 0F, 1, 8, 1);
      Shape3.setRotationPoint(5F, 12F, 7F);
      Shape3.setTextureSize(128, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 112, 24);
      Shape4.addBox(0F, 0F, 0F, 1, 8, 1);
      Shape4.setRotationPoint(-6F, 12F, 7F);
      Shape4.setTextureSize(128, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    BG.render(f5);
    Shape1.render(f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
  }
  public void renderAll() {
	    BG.render(0.0625F);
	    Shape1.render(0.0625F);
	    Shape2.render(0.0625F);
	    Shape3.render(0.0625F);
	    Shape4.render(0.0625F);

	  }

}
