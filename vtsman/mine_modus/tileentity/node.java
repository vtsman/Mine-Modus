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
	private NBTTagCompound tag = new NBTTagCompound();
	public ItemStack itemstack;
	public double energy = 0;
	public LiquidStack liquid;
	String network = "Any";
	int tick = 100;
	int length = 0;
	
	public String type;
	public int capacity;
	public int range;
	
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
			
			int id;
			int meta;
			int amount;
			
			if(data.hasKey("ItemID")){
				id = data.getInteger("ItemID");
				amount = data.getInteger("ItemAmount");
				meta = data.getInteger("ItemMeta");
				this.itemstack = new ItemStack(id, amount, meta);
			}
			
			if(data.hasKey("LiquidID")){
				id = data.getInteger("LiquidID");
				amount = data.getInteger("LiquidAmount");
				meta = data.getInteger("LiquidMeta");
				this.liquid = new LiquidStack(id, amount, meta);
			}
			
			this.energy = data.getDouble("energy");
			this.type =  vtsman.mine_modus.block.node.strType(data.getInteger("Type"));
	        this.setType(this.type, data.getInteger("Radius"), data.getInteger("Capacity"));
	    }
    /**
	     * Writes a tile entity to NBT.
	     */
	 @Override
		public void writeToNBT(NBTTagCompound data) {
		 		super.writeToNBT(data);
		 		
		 		if(this.itemstack != null){
			 		data.setInteger("ItemID", this.itemstack.itemID);
			 		data.setInteger("ItemAmount", this.itemstack.stackSize);
			 		data.setInteger("ItemMeta", this.itemstack.getItemDamage());
		 		}
		 		if(this.liquid != null){
			 		data.setInteger("LiquidID", this.liquid.itemID);
			 		data.setInteger("LiquidAmount", this.liquid.amount);
			 		data.setInteger("LiquidMeta", this.liquid.itemMeta);
		 		}
		 		
				data.setDouble("energy", this.energy);
				data.setInteger("Type", vtsman.mine_modus.block.node.intType(this.type));
				data.setInteger("Radius", this.range);
				data.setInteger("Capacity", this.capacity);
				data.setInteger("x", this.xCoord);
				data.setInteger("y", this.yCoord);
				data.setInteger("z", this.zCoord);
		}
	public static double getRenderRadius(int n, int type){
    	if(type == 2) return (Math.pow(n/64, 1.0/3.0)) * .4;

    	if(type == 1) return (Math.pow(n/1000, 1.0/3.0)) * .4;
    	
    	if(type == 0) return (Math.pow(n/100, 1.0/3.0)) * .2;
    	return 0;
    }
    public void setType(String Type, int range, int capacity){
    	this.type = Type;
    	this.range = range;
    	this.capacity = capacity;
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
            if(this.type == "Energy"){
            	this.sendEnergy();
            }
            else if(this.type == "Liquid"){
            	this.sendLiquid();
            }
            else if(this.type == "Item"){
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
	    public boolean canStore(ItemStack stack, LiquidStack Stack, double energy){
	    	if(this.type == "Item"){
	    		if(this.itemstack == null){
	    			return true;
	    		}
	    		if(this.itemstack.getItem() == stack.getItem() && this.itemstack.getMaxDamage() == stack.getItemDamage() && this.itemstack.stackSize + stack.stackSize <= this.capacity){
	    			return true;
	    		}
	    	}
	    	if(this.type == "Liquid"){
	    		if(this.liquid == null){
	    			return true;
	    		}
	    		if(this.liquid.isLiquidEqual(Stack) && this.liquid.amount + Stack.amount <= this.capacity){
	    			return true;
	    		}
	    	}
	    	if(this.type == "Energy"){
	    		if(this.energy + energy <= this.capacity){
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    public boolean handshake(String type, String network){
	    	return this.type == type;
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
	    	return this.itemstack.stackSize / this.capacity;
	    }
	    public double getPercentLiquid(){
	    	return this.liquid.amount / this.capacity;
	    }
	    public double getPercentEnergy(){
	    	return this.energy / this.capacity;
	    }
	    public void getNetwork(){
	    	length = 0;
	    	for(int x = -1 * this.range; x <= this.range; x++){
		    	for(int y = -1 * this.range; y <= this.range; y++){
			    	for(int z = -1 * this.range; z <= this.range; z++){
			    		if(this.worldObj != null){
			    			if(this.worldObj.getBlockId(this.xCoord + x, this.yCoord + y, this.zCoord + z) == modBlocks.node.blockID){
			    				if(((node)this.worldObj.getBlockTileEntity(this.xCoord + x, this.yCoord + y, this.zCoord + z)).handshake(this.type, this.network)){
			    					node other_node = (node)this.worldObj.getBlockTileEntity(this.xCoord + x, this.yCoord + y, this.zCoord + z);
			    					if(this.type == "Energy"){
			    					join(x, y, z);
			    					}

			    					if(this.type == "Item"){
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

			    						if(this.type == "Liquid"){

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
				System.out.println("Node at: " + this.xCoord + "," + this.yCoord + "," + this.zCoord + " connected to node at: " + (this.xCoord + x) + "," + (this.yCoord + y) +"," + (this.zCoord + z) + " of type: " + this.type + " in cell " + length);
				length++;
	    		}
	    	}

	    public void sendEnergy(){
	    	double addPercent = 0;
	    	int capacity = 0;
	    	int check = 0;
	    	if(this.group != null){
	    	for(int i = 0; i < length; i++){
	    		capacity = capacity + group[i].capacity;
	    	}
	    	for(int i = 0; i < length; i++){
	    		addPercent = addPercent + group[i].energy;
	    	} 
	    	for(int i = 0; i < length; i++){
	    		if(group[i].energy == group[i].capacity *(addPercent/capacity)){
	    			check++;
	    		}
	    	}
	    	if(check != length){
	    	for(int i = 0; i < length; i++){
	    		group[i].energy = group[i].capacity *(addPercent/capacity);
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
	    		capacity = capacity + group[i].capacity;
	    	}
	    	for(int i = 0; i < length; i++){
	    		if(group[i].itemstack != null){
	    		addPercent = addPercent + group[i].itemstack.stackSize;
	    		}
	    	} 
	    	int n = 0;
	    	int m = 0;
	    	for(int i = 0; i < length; i++){
	    		n = (int)((double)group[i].capacity * (double)addPercent/(double)capacity);
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
	    		capacity = capacity + group[i].capacity;
	    	}
	    	for(int i = 0; i < length; i++){
	    		if(group[i].liquid != null){
	    		addPercent = addPercent + group[i].liquid.amount;
	    		}
	    	} 
	    	int n = 0;
	    	int m = 0;
	    	for(int i = 0; i < length; i++){
	    		n = (int)((double)group[i].capacity * (double)addPercent/(double)capacity);
	    			group[i].liquid = (new LiquidStack(this.liquid.itemID, n, this.liquid.itemMeta));

	    		m = m + n;
	    	}
	    	this.liquid.amount = this.liquid.amount + (addPercent - m);
	    	}
	    }
}