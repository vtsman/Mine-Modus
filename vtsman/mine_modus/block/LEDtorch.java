package vtsman.mine_modus.block;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vtsman.mine_modus.client.clientProxy;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;

public class LEDtorch extends BlockTorch{
	private Random rand = new Random();
	protected LEDtorch(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {	
		this.blockIcon = par1IconRegister.registerIcon(clientProxy.TEXTURES + "ledtorch");
    }
	 @SideOnly(Side.CLIENT)

	    /**
	     * A randomly called display update to be able to add particles or other items for display
	     */
	    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	    {
		 Minecraft mc = Minecraft.getMinecraft();
	        int l = par1World.getBlockMetadata(par2, par3, par4);
	        double d0 = (double)((float)par2 + 0.5F);
	        double d1 = (double)((float)par3 + 0.7F);
	        double d2 = (double)((float)par4 + 0.5F);
	        double d3 = 0.2199999988079071D;
	        double d4 = 0.27000001072883606D;
	        if(mc.gameSettings.particleSetting != 2){
	        if (l == 1)
	        {
	        	EntityFX fx = new EntityReddustFX(par1World, d0 - d4, d1 + d3, d2, 0.0f, 0.0f, 0.0f);
	        	fx.setRBGColorF(0.0F, 1.0F, 1.0F);
	        	mc.effectRenderer.addEffect(fx);
	        }
	        else if (l == 2)
	        {
	        	EntityFX fx = new EntityReddustFX(par1World, d0 + d4, d1 + d3, d2, 0.0f, 0.0f, 0.0f);
	        	fx.setRBGColorF(0.0F, 1.0F, 1.0F);
	        	mc.effectRenderer.addEffect(fx);
	        }
	        else if (l == 3)
	        {
	        	EntityFX fx = new EntityReddustFX(par1World, d0, d1 + d3, d2 - d4, 0.0f, 0.0f, 0.0f);
	        	fx.setRBGColorF(0.0F, 1.0F, 1.0F);
	        	mc.effectRenderer.addEffect(fx);
	          
	        }
	        else if (l == 4)
	        {
	        	EntityFX fx = new EntityReddustFX(par1World, d0, d1 + d3, d2 + d4, 0.0f, 0.0f, 0.0f);
	        	fx.setRBGColorF(0.0F, 1.0F, 1.0F);
	        	mc.effectRenderer.addEffect(fx);
	            
	        }
	        else
	        {
	        	EntityFX fx = new EntityReddustFX(par1World, d0, d1, d2, 0.0f, 0.0f, 0.0f);
	        	fx.setRBGColorF(0.0F, 1.0F, 1.0F);
	        	mc.effectRenderer.addEffect(fx);
	            
	        }
	    }
	    }
}
