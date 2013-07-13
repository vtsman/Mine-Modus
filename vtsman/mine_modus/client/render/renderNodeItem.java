package vtsman.mine_modus.client.render;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import vtsman.mine_modus.tileentity.*;
import vtsman.mine_modus.client.models.Model_Node;

import cpw.mods.fml.client.FMLClientHandler;

public class renderNodeItem implements IItemRenderer
{
	private renderDodecahedron Node;
	
	public renderNodeItem()
	{
		Node = new renderDodecahedron();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		if(item.getTagCompound() != null){
		if(item.getTagCompound().hasKey("Type") && item.getTagCompound().hasKey("Capacity")){
		float rad = (float)(vtsman.mine_modus.tileentity.node.getRenderRadius(item.stackTagCompound.getInteger("Capacity"), item.stackTagCompound.getInteger("Type")));
		switch(type)
		{
			case ENTITY:{
				render(0f, 0f, 0f, 1.2f * rad, item);
				return;
			}
			
			case EQUIPPED:{
				render(0f, 0f, 0f, 1.2f * rad, item);

				return;
			}
				
			case INVENTORY:{
				render(0f, 0f, 0f, 1.2f * rad, item);

				return;
			}
		
			default:return;
		}
		}
		}
		render(0f, 0f, 0f, 1.2f, item);
	}
	
	private void render(float x, float y, float z, float scale, ItemStack stack)
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslatef(x,  y,  z);
		double n = .9;
		if(scale <= n){
		GL11.glScalef(scale, scale, scale);
		}
		else{
			GL11.glScalef((float)n, (float)n, (float)n);
		}
		GL11.glRotatef(180f, 0f, 1f, 0f);
		
		//FMLClientHandler.instance().getClient().renderEngine.func_110577_a((new ResourceLocation("/vtsman/mine_modus/client/models/temp.png")));
		
		node N = new node();
		N.setType("Energy", 100, 20);
		Node.renderTileEntityAt((TileEntity)N, (double)x, (double)y, (double)z, 0f);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
