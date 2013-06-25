package vtsman.mine_modus.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import vtsman.mine_modus.tileentity.node;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Model_Node extends ModelBase
{
	private IModelCustom modelTutBox;
	
	public Model_Node()
	{
		modelTutBox = AdvancedModelLoader.loadModel("/vtsman/mine_modus/client/models/dodecahedron.obj");
	}
	
	public void render()
	{
		modelTutBox.renderAll();
	}
	
	public void render(node tile, double x, double y, double z)
	{
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
		GL11.glTranslatef((float)x + 0.5f, (float)y + 0.5f, (float)z + 0.5f);
		float rad = (float)tile.getRenderRadius(tile.capacity, vtsman.mine_modus.block.node.intType(tile.type));
		// Scale our object to about half-size in all directions (the OBJ file is a little large)
		if(tile.type != null){
		GL11.glScalef(1.2f * rad, 1.2f * rad, 1.2f * rad);
		}
		else{
			GL11.glScalef(1.2f, 1.2f, 1.2f);
		}
		// Bind the texture, so that OpenGL properly textures our block.
		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/vtsman/mine_modus/client/models/temp.png");

		// Render the object, using modelTutBox.renderAll();
		this.render();

		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}
}
