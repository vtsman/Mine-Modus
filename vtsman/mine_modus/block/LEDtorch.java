package vtsman.mine_modus.block;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vtsman.mine_modus.client.clientProxy;
import vtsman.mine_modus.tileentity.colorTorch;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LEDtorch extends BlockTorch{
	private Random rand = new Random();
	private colorTorch TE;
	public static colorTorch lastTE;
	public static Icon[] icons = new Icon[16];
	private float[] r = {.9f, .8f, .8f, .3f, .8f, .1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};
	private float[] g = {.9f, .3f, 0f, .3f, .8f, .9f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};
	private float[] b = {.9f, 0f, .75f, 1f, .1f, .1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};
	protected LEDtorch(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}
	   @SideOnly(Side.CLIENT)

	    /**
	     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
	     */
	    public Icon getBlockTexture(IBlockAccess access, int par2, int par3, int par4, int par5)
	    {
		   return icons[TE.color];
	       // return this.getIcon(par5, access.getBlockMetadata(par2, par3, par4));
	    }
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) 
	{
	lastTE = (vtsman.mine_modus.tileentity.colorTorch)world.getBlockTileEntity(x, y, z);
	
	}
	/*public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
		if(player.getItemInUse() != null){
		if(player.getItemInUse().itemID == Item.dyePowder.itemID){
			this.TE.setColor(player.getItemInUse().getItemDamage());
			return true;
		}
		}
		return false;
    }*/
	private int getColor(){
		if(this.TE != null){
			return TE.color;
		}
		return 0;
	}
	@Override
	public int getRenderType() {
		return -1;
	}
	public static ItemStack torchNBT(ItemStack i, int c){
		i.stackTagCompound = new NBTTagCompound();
		i.getTagCompound().setInteger("Color", c);
		return i;
	}
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {	
		for(int i = 0; i < 16; i++){
			this.icons[i] = par1IconRegister.registerIcon(clientProxy.TEXTURES + "ledtorch_" + i);
		}
		this.blockIcon = par1IconRegister.registerIcon(clientProxy.TEXTURES + "ledtorch_0");
    }	@Override
    public Icon getIcon(int par1, int par2)
    {
    	if(this.TE != null){
    	if(this.getColor() < this.icons.length){
        return this.icons[15 - this.getColor()];
        }
    	}
    	return this.icons[0];
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
	        	fx.setRBGColorF(r[15-getColor()], g[15-getColor()], b[15-getColor()]);
	        	mc.effectRenderer.addEffect(fx);
	        }
	        else if (l == 2)
	        {
	        	EntityFX fx = new EntityReddustFX(par1World, d0 + d4, d1 + d3, d2, 0.0f, 0.0f, 0.0f);
	        	fx.setRBGColorF(r[15-getColor()], g[15-getColor()], b[15-getColor()]);
	        	mc.effectRenderer.addEffect(fx);
	        }
	        else if (l == 3)
	        {
	        	EntityFX fx = new EntityReddustFX(par1World, d0, d1 + d3, d2 - d4, 0.0f, 0.0f, 0.0f);
	        	fx.setRBGColorF(r[15-getColor()], g[15-getColor()], b[15-getColor()]);
	        	mc.effectRenderer.addEffect(fx);
	          
	        }
	        else if (l == 4)
	        {
	        	EntityFX fx = new EntityReddustFX(par1World, d0, d1 + d3, d2 + d4, 0.0f, 0.0f, 0.0f);
	        	fx.setRBGColorF(r[15-getColor()], g[15-getColor()], b[15-getColor()]);
	        	mc.effectRenderer.addEffect(fx);
	            
	        }
	        else
	        {
	        	EntityFX fx = new EntityReddustFX(par1World, d0, d1, d2, 0.0f, 0.0f, 0.0f);
	        	fx.setRBGColorF(r[15-getColor()], g[15-getColor()], b[15-getColor()]);
	        	mc.effectRenderer.addEffect(fx);
	            
	        }
	    }
	    }	
	 	@Override
		   public boolean hasTileEntity(int metadata)
	    {
	        return true;
	    }
	 	@Override
	    public TileEntity createTileEntity(World world, int meta)
	    {
	    	TE = new vtsman.mine_modus.tileentity.colorTorch();
	        return TE;
	    }
}
