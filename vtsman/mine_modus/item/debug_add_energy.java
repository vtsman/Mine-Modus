package vtsman.mine_modus.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import vtsman.mine_modus.client.clientProxy;
import vtsman.mine_modus.tileentity.node;

public class debug_add_energy extends Item{
	public debug_add_energy(int par1) {
		super(par1);
        this.maxStackSize = 1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
	    if((node)world.getBlockTileEntity(x, y, z) != null && !world.isRemote){
	    	node Node = (node)world.getBlockTileEntity(x, y, z);
	    	if(Node != null){
	    		if(Node instanceof node){
	    	if(Node.type == "Energy"){
	    		Node.addEnergy(5);
	    	}
	    	else if(Node.type == "Item"){
	    		Node.addItems(new ItemStack(Block.stone, 5));
	    	}
	    	else if(Node.type == "Liquid"){
	    		FluidStack liquidstack = new FluidStack(Block.waterStill.blockID, 100);
	    		Node.addLiquid(liquidstack);
	    	}
	    	else{
	    		player.addChatMessage("Oh noes! This node doesn't look right >.<");
	    	}
	    	return true;
	    }
	    	}
	    }
		return false;
	}
	 @Override
	    public void registerIcons(IconRegister par1IconRegister)
	    {
	    	this.itemIcon = par1IconRegister.registerIcon(clientProxy.TEXTURES + "debug");
	    }
}
