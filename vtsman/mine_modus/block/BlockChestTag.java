package vtsman.mine_modus.block;

import vtsman.mine_modus.tileentity.tileChestTag;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockChestTag extends Block{
	public static tileChestTag lastTE;
	public static int lastX;
	public static int lastY;
	public static int lastZ;
	public BlockChestTag(int par1) {
		super(par1, Material.iron);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getRenderType() {
		return -1;
	}
	@Override
	   public boolean hasTileEntity(int metadata)
 {
     return true;
 }
	
	@Override
 public TileEntity createTileEntity(World world, int meta)
 {
	return new vtsman.mine_modus.tileentity.tileChestTag();
 }
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) 
	{
	lastTE = (vtsman.mine_modus.tileentity.tileChestTag)world.getBlockTileEntity(x, y, z);
	
	}

}
