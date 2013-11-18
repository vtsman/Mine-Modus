package vtsman.MineModus.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import vtsman.MineModus.tileentity.QuantMatTE;

public class quantrender extends TileEntitySpecialRenderer {
	RenderItem renderitem = (RenderItem) RenderManager.instance
			.getEntityClassRenderObject(EntityItem.class);
	private final ModelBase model;
	public static final quantrender instance = new quantrender();

	public quantrender() {
		model = new quantModel();
		// model = new NodeTable();
	}

	public ItemStack stack;

	public void renderAModelAt(QuantMatTE tile, double d, double d1, double d2,
			float f) {

		int rotation = 0;
		if (tile.worldObj != null) {
			rotation = tile.getBlockMetadata();
		}
		this.bindTexture(new ResourceLocation("minemodus",
				"textures/models/QM.png")); // texture
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F,
				(float) d2 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		GL11.glRotatef(rotation * 90, 0.0F, 1.0F, 0.0F);
		((quantModel) model).renderAll();
		GL11.glPopMatrix(); // end
	}

	public int id = 0;
	int ang = 0;

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
			double d2, float f) {
		ang++;
		if (ang == 360)
			ang = 0;
		EntityItem entityitem = new EntityItem(null);
		QuantMatTE TE = (QuantMatTE) tileentity;
		entityitem.worldObj = TE.worldObj;
		entityitem.age = 0;
		entityitem.rotationYaw = (float) (0.0D);
		entityitem.lifespan = 100;

		entityitem.hoverStart = 2.0F;
		this.renderAModelAt(TE, d0, d1, d2, f);

		GL11.glTranslated(d0 + 0.5d, d1 + 0.25D, d2 + 0.5d);
		if (tileentity.worldObj != null) {

			if (TE.getStackInSlot(0) != null) {
				entityitem.setEntityItemStack(new ItemStack(TE
						.getStackInSlot(0).itemID, 1, TE.getStackInSlot(0)
						.getItemDamage()));
				renderitem.doRenderItem(entityitem, 0.0D, 0.0D, 0.0D,
						ang / 360, 0.0F);

			}
		}
		GL11.glScaled(1.0D / 1, 1.0D / 1, 1.0D / 1);
		GL11.glTranslated(-1 * (d0 + 0.5d), (d1 + 0.25D) * -1, -1 * (d2 + 0.5d));

	}

}