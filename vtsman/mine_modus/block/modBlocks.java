package vtsman.mine_modus.block;

import vtsman.mine_modus.creativeTabs;
import vtsman.mine_modus.item.itemNode;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

public class modBlocks {
	public static Block node = new node(500, Material.glass).setCreativeTab(creativeTabs.tabModusNodes).setHardness(1f).setUnlocalizedName("node");
	public static Block led = new LEDtorch(501).setCreativeTab(creativeTabs.tabModusBlocks).setHardness(.02f).setUnlocalizedName("LEDTorch").setLightValue(1F);
	public static Block ore = new BlockOre(502, Material.rock).setCreativeTab(creativeTabs.tabModusBlocks).setHardness(1f).setUnlocalizedName("ore");
	public static Block qm = new quantumMaterializer(503, Material.iron).setCreativeTab(creativeTabs.tabModusBlocks).setHardness(1f).setUnlocalizedName("Quantum_Materializer");
public void registerBlocks(){
	GameRegistry.registerBlock(node, itemNode.class, "node");
	GameRegistry.registerBlock(led, itemNode.class, "led");
	GameRegistry.registerBlock(ore, itemNode.class, "ore");
	GameRegistry.registerBlock(qm, itemNode.class, "Quantum Materializer");
	GameRegistry.registerTileEntity(vtsman.mine_modus.tileentity.node.class, "node");
	GameRegistry.registerTileEntity(vtsman.mine_modus.tileentity.Quantum_Materializer_TE.class, "Quantum_Materializer");
	MinecraftForge.setBlockHarvestLevel(node, "pickaxe", 1);
	MinecraftForge.setBlockHarvestLevel(ore, "pickaxe", 2);
	MinecraftForge.setBlockHarvestLevel(qm, "pickaxe", 1);
	LanguageRegistry.addName(node, "Node");
	LanguageRegistry.addName(led, "LED torch");
	LanguageRegistry.addName(qm, "Quantum Materializer");
}

}
