package vtsman.mine_modus.tileentity;

import vtsman.mine_modus.stackUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.*;
public class interfaceNode extends node implements IInventory, IFluidTank{
	
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		// TODO Auto-generated method stub
		return this.itemstack;
	}
	
	@Override
    public ItemStack decrStackSize(int slot, int amt) {
            ItemStack stack = getStackInSlot(slot);
            if (stack != null) {
                    if (stack.stackSize <= amt) {
                            setInventorySlotContents(slot, null);
                    } else {
                            stack = stack.splitStack(amt);
                            if (stack.stackSize == 0) {
                                    setInventorySlotContents(slot, null);
                            }
                    }
            }
            sendPacket();
            return stack;
            
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		// TODO Auto-generated method stub
		return this.itemstack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		this.itemstack = itemstack;
	}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return this.capacity;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return this.type == "Item" && stackUtils.canStack(itemstack, this.itemstack);

	}
//Liquid
	@Override
	public FluidStack getFluid() {
		// TODO Auto-generated method stub
		return this.liquid;
	}

	@Override
	public int getFluidAmount() {
		// TODO Auto-generated method stub
		return this.liquid.amount;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return this.capacity;
	}

	@Override
	public FluidTankInfo getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {

		resource = resource.copy();
		int totalUsed = 0;
		interfaceNode node = this;

		if (liquid != null && liquid.amount > 0 && !liquid.isFluidEqual(resource)) {
			return 0;
		}
		else{
			this.liquid.amount = this.liquid.amount + resource.amount;
		}
		return totalUsed;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		// TODO Auto-generated method stub
		FluidStack l = this.liquid;
		int Drain = maxDrain;
		if(this.liquid.amount - maxDrain < 0)Drain = this.liquid.amount;
		this.liquid.amount = this.liquid.amount - Drain;
		l.amount = Drain;
		return l;
	}

}
