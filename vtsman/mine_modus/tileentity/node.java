package vtsman.mine_modus.tileentity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import vtsman.mine_modus.baseMod;
import vtsman.mine_modus.block.modBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class node extends TileEntity{
	public nodetype type;
	private NBTTagCompound tag = new NBTTagCompound();
	public ItemStack itemstack;
	public double energy = 0;
	public LiquidStack liquid;
	String network = "Any";
	int tick = 100;
	int length = 0;
	public static int maxDistance = 200;
	ItemStack filter;
	public node[] group;
	public node(){
		this.group = new node[40];
	}
	@Override
	 public void readFromNBT(NBTTagCompound data)
	    {
	        super.readFromNBT(data);
			data.setInteger("x", this.xCoord);
			data.setInteger("y", this.yCoord);
			data.setInteger("z", this.zCoord);
	        this.setType(data.getString("Type"), data.getInteger("Radius"), data.getInteger("Capacity"));
	    }
    public static double getRenderRadius(int n, String type){
    	if(type == "Item") return (Math.pow(n/64, 1.0/3.0)) * .4;

    	if(type == "Liquid") return (Math.pow(n/1000, 1.0/3.0)) * .4;
    	
    	if(type == "Energy") return (Math.pow(n/100, 1.0/3.0)) * .2;
    	return 0;
    }
    public void setType(String Type, int range, int capacity){
    	this.type = new nodetype(Type, range, capacity, 0x000000, this.getRenderRadius(capacity, Type));
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
					//player.playerNetServerHandler.sendPacketToPlayer(packet);
				}
				}
			}
		}
        System.out.println("sent packet");
    	}
         // We are on the Bukkit server.
    }
    
    public void updateEntity()
    {
        if (this.worldObj != null && !this.worldObj.isRemote && this.type != null)
        {
            if(tick == 100){
            	tick = 0;
            	this.getNetwork();
            	sendPacket();
            }
            if(this.type.getType() == "Energy"){
            	this.sendEnergy();
            }
            else if(this.type.getType() == "Liquid"){
            	this.sendLiquid();
            }
            else if(this.type.getType() == "Item"){
            	this.sendItems();
            }
            tick++;
            if(this.itemstack != null){
            	if(this.itemstack.stackSize == 0){
            		this.itemstack = null;
            	}
            }
            if(this.liquid != null){
            	if(this.liquid.amount == 0){
            		this.liquid = null;
            	}
            }
        }
    }
	    /**
	     * Writes a tile entity to NBT.
	     */
	 @Override
		public void writeToNBT(NBTTagCompound data) {
		 		super.writeToNBT(data);
				data.setDouble("energy", this.energy);
				data.setString("Type", this.type.getType());
				data.setInteger("Radius", this.type.getRange());
				data.setInteger("Capacity", this.type.getCapacity());
				data.setInteger("x", this.xCoord);
				data.setInteger("y", this.yCoord);
				data.setInteger("z", this.zCoord);
		}
	    public boolean canStore(ItemStack stack, LiquidStack Stack, double energy){
	    	if(this.type.getType() == "Item"){
	    		if(this.itemstack == null){
	    			return true;
	    		}
	    		if(this.itemstack.getItem() == stack.getItem() && this.itemstack.getMaxDamage() == stack.getItemDamage() && this.itemstack.stackSize + stack.stackSize <= this.type.getCapacity()){
	    			return true;
	    		}
	    	}
	    	if(this.type.getType() == "Liquid"){
	    		if(this.liquid == null){
	    			return true;
	    		}
	    		if(this.liquid.isLiquidEqual(Stack) && this.liquid.amount + Stack.amount <= this.type.getCapacity()){
	    			return true;
	    		}
	    	}
	    	if(this.type.getType() == "Energy"){
	    		if(this.energy + energy <= this.type.getCapacity()){
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    public boolean handshake(nodetype node, String network){
	    	return this.type.getType() == node.getType() && (this.network == "Any" || this.network == network);
	    }
	    public boolean addItems(ItemStack stack){
	    	if(canStore(stack, null, 0)){
	    		if(this.itemstack != null){
	    		this.itemstack.stackSize = this.itemstack.stackSize + stack.stackSize;
	    		}
	    		else{
	    			this.itemstack = stack;
	    		}
	    		return true;
	    	}
	    	return false;
	    }
	    //todo finish functions below
	    public boolean addLiquid(LiquidStack stack){
	    	if(canStore(null, stack, 0)){
	    		if(this.liquid != null){
		    		this.liquid.amount = this.liquid.amount + stack.amount;
		    		}
		    		else{
		    			this.liquid = stack;
		    		}
	    		return true;
	    	}
	    	return false;
	    }
	    public boolean addEnergy(double energy){
	    	if(canStore(null, null, energy)){
	    		this.energy = this.energy + energy;
	    		return true;
	    	}
	    	return false;
	    }
	    public double getPercentItems(){
	    	return this.itemstack.stackSize / this.type.getCapacity();
	    }
	    public double getPercentLiquid(){
	    	return this.liquid.amount / this.type.getCapacity();
	    }
	    public double getPercentEnergy(){
	    	return this.energy / this.type.getCapacity();
	    }
	    public void getNetwork(){
	    	length = 0;
	    	for(int x = -1 * this.type.getRange(); x <= this.type.getRange(); x++){
		    	for(int y = -1 * this.type.getRange(); y <= this.type.getRange(); y++){
			    	for(int z = -1 * this.type.getRange(); z <= this.type.getRange(); z++){
			    		if(this.worldObj != null){
			    			if(this.worldObj.getBlockId(this.xCoord + x, this.yCoord + y, this.zCoord + z) == modBlocks.node.blockID){
			    				if(((node)this.worldObj.getBlockTileEntity(this.xCoord + x, this.yCoord + y, this.zCoord + z)).handshake(this.type, this.network)){
			    					node other_node = (node)this.worldObj.getBlockTileEntity(this.xCoord + x, this.yCoord + y, this.zCoord + z);
			    					if(this.type.getType() == "Energy"){
			    					join(x, y, z);
			    					}
			    					
			    					if(this.type.getType() == "Item"){
			    						if(other_node.itemstack == null){
				    						join(x,y,z);
				    					}
				    					else{
				    						if(this.itemstack == null){
				    							System.out.println("linking");
				    							this.itemstack = other_node.itemstack;
				    							this.itemstack.stackSize = 0;
				    						}
			    						if(other_node.itemstack.getItem() == this.itemstack.getItem() && other_node.itemstack.getItemDamage() == this.itemstack.getItemDamage()){
			    							join(x, y, z);
			    						}
			    					}
			    					}
			    					
			    						if(this.type.getType() == "Liquid"){

					    					if(other_node.liquid == null){
					    						join(x,y,z);
					    					}
					    					else{
						    						if(this.liquid == null){
						    							System.out.println("linking");
						    							this.liquid = other_node.liquid;
						    							this.liquid.amount = 0;
						    						}
				    						if((other_node.liquid.itemID == this.liquid.itemID && other_node.liquid.itemMeta == this.liquid.itemMeta) || other_node.liquid == null){
				    							join(x, y, z);
				    						}
				    					}
			    					}
			    				}
			    			}
			    		}
			    	}
		    	}
	    	}
	    }
	    	public void join(int x, int y, int z){
	    		if(length < 39){
	    		this.group[length] = (node)this.worldObj.getBlockTileEntity(this.xCoord + x, this.yCoord + y, this.zCoord + z);
				System.out.println("Node at: " + this.xCoord + "," + this.yCoord + "," + this.zCoord + " connected to node at: " + (this.xCoord + x) + "," + (this.yCoord + y) +"," + (this.zCoord + z) + " of type: " + this.type.getType() + " in cell " + length);
				length++;
	    		}
	    	}
	    	
	    public void sendEnergy(){
	    	double addPercent = 0;
	    	int capacity = 0;
	    	int check = 0;
	    	if(this.group != null){
	    	for(int i = 0; i < length; i++){
	    		capacity = capacity + group[i].type.getCapacity();
	    	}
	    	for(int i = 0; i < length; i++){
	    		addPercent = addPercent + group[i].energy;
	    	} 
	    	for(int i = 0; i < length; i++){
	    		if(group[i].energy == group[i].type.getCapacity() *(addPercent/capacity)){
	    			check++;
	    		}
	    	}
	    	if(check != length){
	    	for(int i = 0; i < length; i++){
	    		group[i].energy = group[i].type.getCapacity() *(addPercent/capacity);
	    	}
	    	}
	    	}
	    	}
	    
	    public void sendItems(){
	    	int addPercent = 0;
	    	int capacity = 0;
	    	int check = 0;
	    	if(this.group != null && this.itemstack != null){
	    	for(int i = 0; i < length; i++){
	    		capacity = capacity + group[i].type.getCapacity();
	    	}
	    	for(int i = 0; i < length; i++){
	    		if(group[i].itemstack != null){
	    		addPercent = addPercent + group[i].itemstack.stackSize;
	    		}
	    	} 
	    	int n = 0;
	    	int m = 0;
	    	for(int i = 0; i < length; i++){
	    		n = (int)((double)group[i].type.getCapacity() * (double)addPercent/(double)capacity);
	    			group[i].itemstack = (new ItemStack(this.itemstack.itemID, n, this.itemstack.getItemDamage()));

	    		m = m + n;
	    	}
	    	this.itemstack.stackSize = this.itemstack.stackSize + (addPercent - m);
	    	}
	    	}
	    public void sendLiquid(){
	    	int addPercent = 0;
	    	int capacity = 0;
	    	int check = 0;
	    	if(this.group != null && this.liquid != null){
	    	for(int i = 0; i < length; i++){
	    		capacity = capacity + group[i].type.getCapacity();
	    	}
	    	for(int i = 0; i < length; i++){
	    		if(group[i].liquid != null){
	    		addPercent = addPercent + group[i].liquid.amount;
	    		}
	    	} 
	    	int n = 0;
	    	int m = 0;
	    	for(int i = 0; i < length; i++){
	    		n = (int)((double)group[i].type.getCapacity() * (double)addPercent/(double)capacity);
	    			group[i].liquid = (new LiquidStack(this.liquid.itemID, n, this.liquid.itemMeta));

	    		m = m + n;
	    	}
	    	this.liquid.amount = this.liquid.amount + (addPercent - m);
	    	}
	    }
}
