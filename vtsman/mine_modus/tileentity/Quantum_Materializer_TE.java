package vtsman.mine_modus.tileentity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Quantum_Materializer_TE extends TileEntity implements IInventory{
		int tick = 100;
		NBTTagCompound tag = new NBTTagCompound();
		int maxDistance = 200;
        private ItemStack[] inv;
        private void sendPacket(){
        	if(!this.worldObj.isRemote){
        	byte[] out = null;
        	this.writeToNBT(tag);

    		try {
    			out = CompressedStreamTools.compress(tag);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
       	 ByteArrayOutputStream bos = new ByteArrayOutputStream(out.length);
         DataOutputStream outputStream = new DataOutputStream(bos);
        	Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.channel = "MineModus TE";
            try {
    			outputStream.writeShort(out.length);
    			outputStream.write(out);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            packet.data = bos.toByteArray();
            packet.length = bos.size();
            if (packet != null) {
    			for (int j = 0; j < this.worldObj.playerEntities.size(); j++) {
    				EntityPlayerMP player = null;//
    				if(this.worldObj.playerEntities.get(j) instanceof EntityPlayerMP){
    				player = (EntityPlayerMP) this.worldObj.playerEntities.get(j);
    				System.out.println("PlayerMP!");
    				}
    				if(player != null){
    				if (Math.abs(player.posX - this.xCoord) <= maxDistance && Math.abs(player.posY - this.yCoord) <= maxDistance && Math.abs(player.posZ - this.zCoord) <= maxDistance) {
    					PacketDispatcher.sendPacketToPlayer(packet, (Player) player);
    					//player.playerNetServerHandler.sendPacketToPlayer(packet);
    				}
    				}
    			}
    		}
        	}
             // We are on the Bukkit server.
        }
        
        public Quantum_Materializer_TE(){
                inv = new ItemStack[1];
        }
        @Override
        public int getSizeInventory() {
                return inv.length;
        }

        @Override
        public ItemStack getStackInSlot(int slot) {  
        	ItemStack fakeStack = inv[slot];
        	if(fakeStack != null){
        		if(inv[slot].stackSize == 8192){
        			return inv[slot];
        		}
        	if(inv[slot].stackSize > 63){
        	fakeStack.stackSize = 32;
        	}
        	}
        	sendPacket();
        	return fakeStack;
        }
        @Override
        public void updateEntity(){
        	if(this.worldObj != null){
        		if(!this.worldObj.isRemote&&this.tick == 100){
        			this.sendPacket();
        			tick = 0;
        		}
        		tick++;
        	}
        }
        @Override
        public void setInventorySlotContents(int slot, ItemStack stack) {
                inv[slot] = stack;
                if (stack != null && stack.stackSize > getInventoryStackLimit()) {
                        stack.stackSize = getInventoryStackLimit();
                }               
            	sendPacket();
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
        public ItemStack getStackInSlotOnClosing(int slot) {
                ItemStack stack = getStackInSlot(slot);
                if (stack != null) {
                        setInventorySlotContents(slot, null);
                }
                return stack;
        }
        
        public int getInventoryStackLimit() {
                return 8192;
        }

        @Override
        public boolean isUseableByPlayer(EntityPlayer player) {
                return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this &&
                player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
        }

        @Override
        public void openChest() {}

        @Override
        public void closeChest() {}
        
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
                sendPacket();
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
                tagCompound.setInteger("x", this.xCoord);
                tagCompound.setInteger("y", this.yCoord);
                tagCompound.setInteger("z", this.zCoord);
                
        }
	        
                @Override
                public String getInvName() {
                        return "mod.vtsman_hodgecraft.shelfTE";
                }

				public boolean func_94042_c() {
					// TODO Auto-generated method stub
					return false;
				}
				public boolean func_94041_b(int i, ItemStack itemstack) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean isInvNameLocalized() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean isStackValidForSlot(int i, ItemStack itemstack) {
					if(itemstack != null && inv[0] != null){
					return itemstack.stackSize + inv[0].stackSize <= this.getInventoryStackLimit() && itemstack.itemID == inv[0].itemID && itemstack.getItemDamage() == inv[0].getItemDamage();
					}
					return true;
				}


			    /**
			     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
			     * side
			     */


			    /**
			     * Writes a tile entity to NBT.
			     */

					
}