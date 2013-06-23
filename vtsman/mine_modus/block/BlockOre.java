package vtsman.mine_modus.block;

import java.util.Random;

import vtsman.mine_modus.client.clientProxy;
import vtsman.mine_modus.item.modItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockOre extends Block{
	private Random random = new Random();
	private String[] iconString = {"titaniumore"};
	public Icon[] icons = new Icon[this.iconString.length];
	public BlockOre(int par1, Material par2Material) {
		super(par1, par2Material);
		// TODO Auto-generated constructor stub
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
                    ItemStack stack = null;
                    switch(par1World.getBlockMetadata(par2, par3, par4)){
                    case 0: stack = new ItemStack(modItems.chunkTitanium.itemID, random.nextInt(3) + 1, 0);
                    }
                    	if(stack != null){
                        entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), stack);
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
                        par1World.spawnEntityInWorld(entityitem);
                    	}
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
	@Override
    public Icon getIcon(int par1, int par2)
    {
    	if(par2 < this.icons.length){
        return this.icons[par2];
        }
    	return null;
    }
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {	
		for(int i = 0; i < iconString.length; i++){
    	this.icons[i] = par1IconRegister.registerIcon(clientProxy.TEXTURES + iconString[i]);
		}
    }
}
