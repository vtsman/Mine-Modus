package vtsman.mine_modus;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vtsman.mine_modus.block.*;
import vtsman.mine_modus.client.ClientPacketHandler;
import vtsman.mine_modus.client.clientProxy;
import vtsman.mine_modus.worldGen.modGen;

@NetworkMod(clientSideRequired=true,serverSideRequired=false, //Whether client side and server side are needed
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"MineModus TE"}, packetHandler = ClientPacketHandler.class), //For clientside packet handling
serverPacketHandlerSpec = @SidedPacketHandler(channels = {"MineModusServer"}, packetHandler = ServerPacketHandler.class)) //For serverside packet handling
@Mod(modid="minemodus",name="Mine Modus",version="0.1.0")
public class baseMod { //The class file
	public modBlocks modBlocks = new modBlocks();
	public static vtsman.mine_modus.item.modItems modItems = new vtsman.mine_modus.item.modItems();
	public creativeTabs tabs = new creativeTabs();
@Instance("minemodus") //The instance, this is very important later on
public static baseMod instance = new baseMod();
@SidedProxy(clientSide = "vtsman.mine_modus.client.clientProxy", serverSide = "vtsman.mine_modus.commonProxy")
public static commonProxy proxy;

@Init
public void Init(FMLInitializationEvent event){ //Your main initialization method
this.modBlocks.registerBlocks();
this.modItems.init();
tabs.init();
modGen.init();
proxy.registerRenderInformation();
//nodeRecipeManager.init();
}

@PostInit
public static void postInit(FMLPostInitializationEvent event){
	recipes.init();
}

}