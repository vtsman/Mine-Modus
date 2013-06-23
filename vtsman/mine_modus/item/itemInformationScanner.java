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
	    if((node)world.getBlockTileEntity(x, y, z) != null && !world.isRemote){
	    	node Node = (node)world.getBlockTileEntity(x, y, z);
	    	
	    	
	    	if(Node.type.getType() == "Liquid"){
	    		if(Node.liquid != null){
	    		player.addChatMessage("This node contains " + Node.liquid.amount + " out of " + Node.type.getCapacity() + " of " + Item.itemsList[Node.liquid.itemID].func_77653_i(new ItemStack(Item.itemsList[Node.liquid.itemID], 1, Node.liquid.itemMeta)));
	    		return true;
	    		}
	    		else{
	    			player.addChatMessage("There is no liquid in this node");
	    			return true;
	    		}
	    		
	    	}
	    	if(Node.type.getType() == "Item"){
	    	if(Node.itemstack != null){
	    		player.addChatMessage("This node contains " + Node.itemstack.stackSize + " " + Node.itemstack.getItem().func_77653_i(Node.itemstack));
	    		return true;
	    		}
	    		else{
	    			player.addChatMessage("There are no items in this node");
	    			return true;
	    		}
	    	}
	    	if(Node.type.getType() == "Energy")player.addChatMessage("Energy: " + df.format(Node.energy) + " out of " + Node.type.getCapacity());
	    	return true;
	    }
		return false;
	}
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
    	this.itemIcon = par1IconRegister.registerIcon(clientProxy.TEXTURES + "information scanner");
    }

}
