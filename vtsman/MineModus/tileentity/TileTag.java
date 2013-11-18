package vtsman.MineModus.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import vtsman.vtsmcUtil.StackUtils;
import vtsman.vtsmcUtil.SyncTE;

public class TileTag extends TileEntity {
	int cycle = 100;
	public ItemStack s1 = null;
	public ItemStack s2 = null;

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		ItemStack inv[] = new ItemStack[2];
		NBTTagList tagList = tagCompound.getTagList("Inventory");
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		s1 = inv[0];
		s2 = inv[1];
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		ItemStack inv[] = new ItemStack[2];
		inv[0] = s1;
		inv[1] = s2;
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
	public void updateEntity() {
		if (!this.worldObj.isRemote)
			if (cycle == 100) {
				cycle = 0;
				TileEntity te = this.worldObj.getBlockTileEntity(this.xCoord,
						this.yCoord + 1, this.zCoord);
				if (te != null) {
					if (te instanceof IInventory) {
						IInventory inv = (IInventory) te;
						ItemStack[] stacks = new ItemStack[inv
								.getSizeInventory()];
						for (int i = 0; i < stacks.length; i++) {
							stacks[i] = inv.getStackInSlot(i);
						}
						stacks = StackUtils.getNonNull(stacks);
						stacks = StackUtils.sortGreatest(StackUtils
								.combine(stacks));
						if (stacks.length > 0)
							s1 = stacks[0];
						if (stacks.length > 1)
							s2 = stacks[1];
						SyncTE.sendPacket(this);
					}
				}
			}
		cycle++;
	}
}
