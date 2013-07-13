package vtsman.mine_modus.block;

import java.util.List;
import java.util.Random;

import org.bouncycastle.util.encoders.Hex;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class node extends Block{
	private String[] types = {"Energy", "Item", "Liquid", "Energy", "Energy"};
	private int[] radius = {5, 10, 10, 20, 20};
	private int[] capacity = {100, 64, 400, 1000, 10000};
	private int[] color = {0xA3CADE, 0xCC2900, 0xEDCC47, 0x000000, 0x000000};
	public static vtsman.mine_modus.tileentity.node lastTE;
	public vtsman.mine_modus.tileentity.node TE;
	Random random = new Random();
	public node(int par1, Material par2Material) {
		super(par1, par2Material);
		// TODO Auto-generated constructor stub
	}   
	@Override
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		return Block.blockIron.getIcon(0, 0);
	}
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	@Override
	public int getRenderType() {
		return -1;
	}
	public int quantityDropped(Random par1Random)
    {
        return 0;
    }
	
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
                    float f = this.random.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.random.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;
                    ItemStack stack = new ItemStack(this.blockID, 1, par1World.getBlockMetadata(par2, par3, par4));
                    this.setNBT(stack, this.TE.type, this.TE.range, this.TE.capacity);     
                        entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), stack);
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
                        par1World.spawnEntityInWorld(entityitem);
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) 
	{
	lastTE = (vtsman.mine_modus.tileentity.node)world.getBlockTileEntity(x, y, z);
	
	}
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		//normal nodes
		ItemStack stack = new ItemStack(this.blockID, 1, 0);
		this.setNBT(stack, "Energy", 20, 1000);
		par3List.add(stack);
		stack = new ItemStack(this.blockID, 1, 0);
		this.setNBT(stack, "Liquid", 10, 400);
		par3List.add(stack);
		stack = new ItemStack(this.blockID, 1, 0);
		this.setNBT(stack, "Item", 10, 64);
		par3List.add(stack);
		stack = new ItemStack(this.blockID, 1, 0);
		this.setNBT(stack, "Energy", 10, 100);
		par3List.add(stack);
		stack = new ItemStack(this.blockID, 1, 0);
		this.setNBT(stack, "Energy", 10, 10000);
		par3List.add(stack);
		//interface nodes
		stack = new ItemStack(this.blockID, 1, 1);
		this.setNBT(stack, "Energy", 20, 1000);
		par3List.add(stack);
		stack = new ItemStack(this.blockID, 1, 1);
		this.setNBT(stack, "Liquid", 10, 400);
		par3List.add(stack);
		stack = new ItemStack(this.blockID, 1, 1);
		this.setNBT(stack, "Item", 10, 64);
		par3List.add(stack);
		stack = new ItemStack(this.blockID, 1, 1);
		this.setNBT(stack, "Energy", 10, 100);
		par3List.add(stack);
		stack = new ItemStack(this.blockID, 1, 1);
		this.setNBT(stack, "Energy", 10, 10000);
		par3List.add(stack);
    }
	public static int intType(String type){
		if(type == "Energy")return 0;
		if(type == "Liquid")return 1;
		if(type == "Item")return 2;
		return 0;
	}
	public static String strType(int t){
		switch(t){
		case 0: return "Energy";
		case 1: return "Liquid";
		case 2: return "Item";
		}
		return null;
	}
	public static void setNBT(ItemStack stack, String type, int range, int capacity){
		stack.stackTagCompound = new NBTTagCompound();
		stack.stackTagCompound.setInteger("Type", intType(type));
		stack.stackTagCompound.setInteger("Range", range);
		stack.stackTagCompound.setInteger("Capacity", capacity);
	}
	@Override
	   public boolean hasTileEntity(int metadata)
    {
        return true;
    }
	/*public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		int meta = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		float rad = (float) TE.getRenderRadius(TE.capacity, intType(TE.type));
		float f = .5f;
		this.setBlockBounds(rad * -1 + f, rad * -1 + f, rad * -1 + f, rad + f, rad + f, rad + f);
	}*/
    public TileEntity createTileEntity(World world, int meta)
    {
    	switch(meta){
    	case 0: System.out.println("normal"); 
    		TE = new vtsman.mine_modus.tileentity.node();
    	case 1: System.out.println("interface");
    		TE = new vtsman.mine_modus.tileentity.interfaceNode();
    	}
        return TE;
    }

}