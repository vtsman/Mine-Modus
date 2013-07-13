package vtsman.mine_modus.tileentity;

import vtsman.mine_modus.stackUtils;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.*;

public class tileChestTag extends TileEntity{
	int tick = 100;
	public ItemStack stack1 = null;
	public ItemStack stack2 = null;
	
	@Override
	public void updateEntity(){
		if(this.worldObj.isRemote){
			if(tick == 100){
				tick = 0;
				this.EnumStacks();
			}
			tick++;
		}
	}
	public void EnumStacks(){
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
		IInventory TE = null;
		switch(meta){
		case 0: if(this.worldObj.getBlockTileEntity(this.xCoord + 1, this.yCoord, this.zCoord) instanceof IInventory) TE = (IInventory)this.worldObj.getBlockTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
		case 1: if(this.worldObj.getBlockTileEntity(this.xCoord - 1, this.yCoord, this.zCoord) instanceof IInventory) TE = (IInventory)this.worldObj.getBlockTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
		case 2: if(this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord + 1) instanceof IInventory) TE = (IInventory)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
		case 3: if(this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord - 1) instanceof IInventory) TE = (IInventory)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
		}
		boolean endSearch = false;
		int j = 0;
		if(TE != null){
			ItemStack[] items = new ItemStack[TE.getSizeInventory()];
			for(int i = 0; i < TE.getInventoryStackLimit(); i++){
				while(!endSearch){
					if(j < TE.getSizeInventory()){
					if(items[j] != null){
						if(stackUtils.canStack(TE.getStackInSlot(i), items[j])){
							items[j].stackSize = items[j].stackSize + TE.getStackInSlot(i).stackSize;
							endSearch = true;
						}
					}
					else{
						items[j] = TE.getStackInSlot(i);
					}
					if(j >= items.length)endSearch = true;
					j++;
				}
					else{
						endSearch = true;
					}
				}
				j = 0;
				endSearch = false;
			}
			for(int i = 0; i < items.length; i++){
				if(items[i] != null){
					if(stack1 != null){
						if(stack1.stackSize < items[i].stackSize){
							stack2 = stack1;
							stack1 = items[i];
						}
						else if(stack2 != null){
							if(stack2.stackSize < items[i].stackSize){
								stack2 = items[i];
							}
						}
						else{
							stack2 = items[i];
						}
					}
					else{
						stack1 = items[i];
					}
						
		}
			}	
		}
	}
	
}
