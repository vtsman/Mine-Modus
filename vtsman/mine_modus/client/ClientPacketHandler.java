package vtsman.mine_modus.client;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import javax.management.ReflectionException;

import buildcraft.core.network.PacketTileUpdate;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class ClientPacketHandler implements IPacketHandler{

	@Override
public void onPacketData(INetworkManager manager, Packet250CustomPayload payload, Player player){
DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
World world = ((EntityPlayer)player).worldObj;
if(payload.channel == "MineModus TE"){
NBTTagCompound tag = null;
short length;
try {
	length = data.readShort();
	byte[] compressed = new byte[length];
	data.readFully(compressed);
	tag = CompressedStreamTools.decompress(compressed);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
if(tag != null){
if(tag.hasKey("x") && tag.hasKey("y") && tag.hasKey("z")){
	System.out.println("has x, y, z");
	int x = tag.getInteger("x");
	int y = tag.getInteger("y");
	int z = tag.getInteger("z");
	TileEntity te = world.getBlockTileEntity(x, y, z);
	if(te != null){
	te.readFromNBT(tag);
	}
}
}
}
}
}