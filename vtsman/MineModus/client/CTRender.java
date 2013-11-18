package vtsman.MineModus.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class CTRender extends TileEntitySpecialRenderer {
	RenderItem renderitem = (RenderItem) RenderManager.instance
			.getEntityClassRenderObject(EntityItem.class);
	private final ModelBase model;
	public static final CTRender instance = new CTRender();

	public CTRender() {
		model = new quantModel();
		// model = new NodeTable();
	}

	public void renderAModelAt(TileEntity tile, double d, double d1, double d2,
			float f) {

		int rotation = 0;
		if (tile.worldObj != null) {
			rotation = tile.getBlockMetadata();
		}
		this.bindTexture(new ResourceLocation("minemodus",
				"textures/models/ChestTag.png")); // texture
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F,
				(float) d2 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		GL11.glRotatef(rotation * 90, 0.0F, 1.0F, 0.0F);
		((IModel) model).renderAll();
		GL11.glPopMatrix(); // end
	}

	public int id = 0;
	int ang = 0;

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
			double d2, float f) {

		this.renderAModelAt(tileentity, d0, d1, d2, f);

		GL11.glTranslated(d0 + 0.5d, d1 + 0.25D, d2 + 0.5d);
		if (tileentity.worldObj != null) {

		}
		GL11.glScaled(1.0D / 1, 1.0D / 1, 1.0D / 1);
		GL11.glTranslated(-1 * (d0 + 0.5d), (d1 + 0.25D) * -1, -1 * (d2 + 0.5d));

	}

}