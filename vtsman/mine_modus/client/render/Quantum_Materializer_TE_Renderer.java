package vtsman.mine_modus.client.render;

import vtsman.mine_modus.baseMod;
import vtsman.mine_modus.tileentity.Quantum_Materializer_TE;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import vtsman.mine_modus.client.models.Quantum_Materializer_model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Quantum_Materializer_TE_Renderer extends TileEntitySpecialRenderer{
	RenderItem renderitem = (RenderItem)RenderManager.instance.getEntityClassRenderObject(EntityItem.class);
	private Quantum_Materializer_model model;
	  public static final Quantum_Materializer_TE_Renderer instance = new Quantum_Materializer_TE_Renderer();
public Quantum_Materializer_TE_Renderer(){
model = new Quantum_Materializer_model();
}
public ItemStack stack;
static Class<Quantum_Materializer_TE> TE = Quantum_Materializer_TE.class;
public void renderAModelAt(Quantum_Materializer_TE tile, double d, double d1, double d2, float f) {

int rotation = 0;
if(tile.worldObj != null)
{
rotation = tile.getBlockMetadata();
}
bindTextureByName("/vtsman/mine_modus/client/models/Quantum Materializer.png"); //texture
GL11.glPushMatrix();
GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
GL11.glScalef(1.0F, -1F, -1F);
GL11.glRotatef(rotation*90, 0.0F, 1.0F, 0.0F);
model.renderAll();
GL11.glPopMatrix(); //end
}


public int id = 0;


@Override
public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
		double d2, float f) {

	//Quantum_Materializer_TE pubTE = (Quantum_Materializer_TE) tileentity;
	EntityItem entityitem = new EntityItem(null);
	 Quantum_Materializer_TE TE = (Quantum_Materializer_TE)tileentity;
    entityitem.worldObj = TE.worldObj;
    entityitem.age = 0;
    entityitem.rotationYaw = (float)(0.0D);
    entityitem.lifespan = 100;
    
  
   // this.entityitem.setPosition((double)d0, (double)d1, (double)d2);
    entityitem.hoverStart = 2.0F;
	this.renderAModelAt(TE, d0, d1, d2, f);
	//entityitem.setPositionAndRotation((double)d0, (double)d1, (double)d2, 0, TE.angle);
    
	 GL11.glTranslated(d0 + 0.5d, d1 + 0.25D, d2+ 0.5d);
		if(tileentity.worldObj != null){

			 if(TE.getStackInSlot(0) != null){
				 entityitem.setEntityItemStack(new ItemStack(TE.getStackInSlot(0).itemID, 1, TE.getStackInSlot(0).getItemDamage()));
				 
				renderitem.doRenderItem(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
				GL11.glRotatef((float)TE.angle/(float)360, 1, 0, 0);
			 }
			 }
	GL11.glScaled(1.0D / 1, 1.0D / 1, 1.0D / 1);
	GL11.glTranslated(-1 * (d0 + 0.5d), (d1 + 0.25D) * -1, -1 * (d2+ 0.5d));

}


}