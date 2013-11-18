package vtsman.MineModus;

import net.minecraft.block.BlockDispenser;
import net.minecraft.item.ItemStack;
import vtsman.MineModus.blocks.dispenseScaffold;
import vtsman.MineModus.blocks.modBlocks;
import vtsman.MineModus.items.modItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler; // used in 1.6.2
//import cpw.mods.fml.common.Mod.PreInit;    // used in 1.5.2
//import cpw.mods.fml.common.Mod.Init;       // used in 1.5.2
//import cpw.mods.fml.common.Mod.PostInit;   // used in 1.5.2
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "Mine Modus", name = "Mine Modus", version = "0.0.0")
@NetworkMod(clientSideRequired = true)
public class BaseMod {

	// The instance of your mod that Forge uses.
	@Instance(value = "MineModus")
	public static BaseMod instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "vtsman.MineModus.client.ClientProxy", serverSide = "vtsman.MineModus.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	// used in 1.6.2
	// @PreInit // used in 1.5.2
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
		proxy.registerTEs();
		modItems.init();
		modBlocks.init();
		proxy.registerRenderers();
		BlockDispenser.dispenseBehaviorRegistry.putObject(new ItemStack(
				modBlocks.scaffold, 1, 0).getItem(), new dispenseScaffold());
	}

	@EventHandler
	// used in 1.6.2
	// @Init // used in 1.5.2
	public void load(FMLInitializationEvent event) {

		proxy.registerRenderers();
	}

	@EventHandler
	// used in 1.6.2
	// @PostInit // used in 1.5.2
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
