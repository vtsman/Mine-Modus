package vtsman.mine_modus.item;

import java.text.DecimalFormat;

import vtsman.mine_modus.client.clientProxy;
import vtsman.mine_modus.tileentity.node;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class itemInformationScanner extends Item{

	public itemInformationScanner(int par1) {
		super(par1);
        this.maxStackSize = 1;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		DecimalFormat df = new DecimalFormat("#.##");
		if(!world.isRemote){
	    	node Node = null;
	    	if(world.getBlockTileEntity(x, y, z) instanceof node)Node = (node)world.getBlockTileEntity(x, y, z);
	    	if(Node != null){
	    		switch(world.getBlockMetadata(x, y, z)){
	    		case 0: player.addChatMessage("Normal Node");
	    		case 1: player.addChatMessage("Interface Node");
	    		}
	    	final String s = Node.type;
	    	if(s == "Liquid"){
	    		if(Node.liquid != null){
	    		player.addChatMessage("This node contains " + Node.liquid.amount + " out of " + Node.capacity + " of " + Item.itemsList[Node.liquid.fluidID].getUnlocalizedName());
	    		return true;
	    		}
	    		else{
	    			player.addChatMessage("There is no liquid in this node");
	    			return true;
	    		}
	    		
	    	}
	    	else if(s == "Item"){
	    	if(Node.itemstack != null){
	    		player.addChatMessage("This node contains " + Node.itemstack.stackSize + " out of " + Node.capacity + " of " + Node.itemstack.getItem().getUnlocalizedName());
	    		return true;
	    		}
	    		else{
	    			player.addChatMessage("There are no items in this node");
	    			return true;
	    		}
	    	}
	    	else if(s == "Energy"){
	    		player.addChatMessage("Energy: " + df.format(Node.energy) + " out of " + Node.capacity);
	    	}
	    	else{
	    		player.addChatMessage("Oh noes! This node doesn't look right >.<");
	    	}
	    	return true;
	    }
		}
		return false;
	}
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
    	this.itemIcon = par1IconRegister.registerIcon(clientProxy.TEXTURES + "information scanner");
    }

}
