package vtsman.mine_modus.tileentity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.*;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class colorTorch extends TileEntity{
	public int color = 0;
	public int tick = 100;
	private int maxDistance = 200;
	private NBTTagCompound tag = new NBTTagCompound();
	@Override
	 public void readFromNBT(NBTTagCompound data)
	    {
	        super.readFromNBT(data);
	        this.color = data.getInteger("Color");
	    }
   /**
	     * Writes a tile entity to NBT.
	     */
	public void setColor(int i){
		this.color = i;
		this.sendPacket();
	}
	 @Override
		public void writeToNBT(NBTTagCompound data) {
		 		super.writeToNBT(data);
		 		data.setInteger("x", this.xCoord);
		 		data.setInteger("y", this.yCoord);
		 		data.setInteger("z", this.zCoord);
				data.setInteger("Color", this.color);
		}
	public void updateEntity()
	{
	    if (this.worldObj != null && !this.worldObj.isRemote)
	    {
	    	if(tick == 100){
	    		this.sendPacket();
	    		tick = 0;
	    	}
	    	tick++;
	    }
	}
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
					System.out.println("here");
					//player.playerNetServerHandler.sendPacketToPlayer(packet);
				}
				}
			}
		}
	    System.out.println("sent packet");
		}
	     // We are on the Bukkit server.
	}
}
