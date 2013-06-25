package vtsman.mine_modus.client.render;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import vtsman.mine_modus.block.node;
import vtsman.mine_modus.client.models.Model_Node;

import cpw.mods.fml.client.FMLClientHandler;

public class renderNodeItem implements IItemRenderer
{
	private Model_Node Node;
	
	public renderNodeItem()
	{
		Node = new Model_Node();
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
				render(0f, 0f, 0f, 1.2f * rad);
				return;
			}
			
			case EQUIPPED:{
				render(0f, 0f, 0f, 1.2f * rad);

				return;
			}
				
			case INVENTORY:{
				render(0f, 0f, 0f, 1.2f * rad);

				return;
			}
		
			default:return;
		}
		}
		}
		render(0f, 0f, 0f, 1.2f);
	}
	
	private void render(float x, float y, float z, float scale)
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
		
		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/vtsman/mine_modus/client/models/temp.png");
		
		Node.render();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
