package vtsman.MineModus.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import vtsman.vtsmcUtil.SyncTE;

public class QuantMatTE extends TileEntity implements IInventory {
	int tick = 100;
	NBTTagCompound tag = new NBTTagCompound();
	int maxDistance = 200;
	private final ItemStack[] inv;

	public QuantMatTE() {
		inv = new ItemStack[1];
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		ItemStack fakeStack = inv[slot];
		if (fakeStack != null) {
			if (inv[slot].stackSize == 8192) {
				return inv[slot];
			}
			if (inv[slot].stackSize > 63) {
				fakeStack.stackSize = 32;
			}
		}
		SyncTE.sendPacket(this);
		return fakeStack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
		SyncTE.sendPacket(this);

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
		SyncTE.sendPacket(this);
		return stack;

	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 8192;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this
				&& player.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
						zCoord + 0.5) < 64;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public void updateEntity() {
		if (this.worldObj != null) {
			if (!this.worldObj.isRemote && this.tick == 100) {
				SyncTE.sendPacket(this);
				tick = 0;
			}
			tick++;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Inventory");
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		SyncTE.sendPacket(this);
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inv.length; i++) {
			ItemStack stack = inv[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);

	}

	@Override
	public String getInvName() {
		return "mod.vtsman_hodgecraft.shelfTE";
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if (itemstack != null && inv[0] != null) {
			return itemstack.stackSize + inv[0].stackSize <= this
					.getInventoryStackLimit()
					&& itemstack.itemID == inv[0].itemID
					&& itemstack.getItemDamage() == inv[0].getItemDamage();
		}
		return true;
	}

	/**
	 * Returns true if automation can extract the given item in the given slot
	 * from the given side. Args: Slot, item, side
	 */

	/**
	 * Writes a tile entity to NBT.
	 */

}