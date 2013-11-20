package vtsman.MineModus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import vtsman.MineModus.items.IB;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class modBlocks {
	public static Block scaffold = new Scaffold(500, Material.wood)
			.setHardness(0.2F).setCreativeTab(CreativeTabs.tabTransport)
			.setUnlocalizedName("Scaffold");

	public static Block qm = new QuantMatBlock(501, Material.iron)
			.setHardness(1).setCreativeTab(CreativeTabs.tabBlock)
			.setUnlocalizedName("QuantMat");

	public static Block chestTag = new ChestTag(502, Material.iron)
			.setHardness(0.75f).setCreativeTab(CreativeTabs.tabDecorations)
			.setUnlocalizedName("chest tag");
	public static Block tpbase = new TilePistonBase(503, true).setCreativeTab(
			CreativeTabs.tabRedstone).setUnlocalizedName("tpbase");
	public static Block tpex = new TilePistonExtension(504)
			.setUnlocalizedName("tpex");
	public static Block tpmove = new TilePistonMoving(505)
			.setUnlocalizedName("tpmove");

	public static void init() {
		LanguageRegistry.addName(scaffold, "Scaffold");
		MinecraftForge.setBlockHarvestLevel(scaffold, "axe", 0);
		GameRegistry.registerBlock(scaffold, "Scaffold");

		GameRegistry.registerBlock(qm, "Quantum Materializer");
		LanguageRegistry.addName(qm, "Quantum Materializer");
		MinecraftForge.setBlockHarvestLevel(qm, "pickaxe", 1);

		GameRegistry.registerBlock(chestTag, "Chest Tag");
		LanguageRegistry.addName(chestTag, "Chest Tag");
		MinecraftForge.setBlockHarvestLevel(chestTag, "pickaxe", 1);

		addBlock(tpbase, "Quantum Piston Base", "pickaxe", 0);
		addBlock(tpex, "Quantum Piston Extended", "pickaxe", 0);
		addBlock(tpmove, "Quantum Piston Moving", "pickaxe", 0);
	}

	private static void addBlock(Block b, String s, String type, int level) {
		GameRegistry.registerBlock(b, IB.class, s);
		LanguageRegistry.addName(b, s);
		MinecraftForge.setBlockHarvestLevel(b, type, level);
	}

}
