package vtsman.mine_modus;

//import ic2.core.Ic2Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vtsman.mine_modus.block.*;
import vtsman.mine_modus.item.modItems;
import vtsman.mine_modus.item.tools.enumTools;
import vtsman.mine_modus.manager.NodeRecipe;
//import vtsman.mine_modus.manager.nodeRecipeManager;
import cpw.mods.fml.common.registry.GameRegistry;
//import cpw.mods.ironchest.IronChest;

public class recipes {
public static void init(){
GameRegistry.addShapedRecipe(new ItemStack(modItems.infoReader), "IGI", "QEQ", "RLR", 'I', Item.ingotIron, 'G', Block.thinGlass, 'Q', Item.netherQuartz, 'E', Item.emerald, 'R', Item.redstone, 'L', Item.glowstone);
GameRegistry.addShapedRecipe(new ItemStack(modItems.upgrade, 1, 0), "RIR", "ICI", "RIR", 'R', Item.redstone, 'I', Item.ingotIron, 'C', Block.chest);
GameRegistry.addShapedRecipe(new ItemStack(modItems.upgrade, 1, 1), "RIR", "ICI", "RIR", 'R', Item.redstone, 'I', Item.ingotGold, 'C', Block.chest);
GameRegistry.addShapedRecipe(new ItemStack(modItems.upgrade, 1, 2), "RIR", "ICI", "RIR", 'R', Item.redstone, 'I', Item.diamond, 'C', Block.chest);

GameRegistry.addShapedRecipe(new ItemStack(modItems.dustRuby, 1, 0), " R ", "RSR", " R ", 'R', Item.redstone, 'S', Block.sand);
/*
GameRegistry.addShapedRecipe(new ItemStack(enumTools.titaniumAxe, 1, 0), "TT", "TS", " S", 'T', modItems.ingotTitanium, 'S', Item.stick);
GameRegistry.addShapedRecipe(new ItemStack(enumTools.titaniumAxe, 1, 0), "TT", "ST", " S", 'T', modItems.ingotTitanium, 'S', Item.stick);
GameRegistry.addShapedRecipe(new ItemStack(enumTools.titaniumHoe, 1, 0), "TT", " S", " S", 'T', modItems.ingotTitanium, 'S', Item.stick);
GameRegistry.addShapedRecipe(new ItemStack(enumTools.titaniumHoe, 1, 0), "TT", "S ", "S ", 'T', modItems.ingotTitanium, 'S', Item.stick);
GameRegistry.addShapedRecipe(new ItemStack(enumTools.titaniumPickaxe, 1, 0), "TTT", " S ", " S ", 'T', modItems.ingotTitanium, 'S', Item.stick);
GameRegistry.addShapedRecipe(new ItemStack(enumTools.titaniumSword, 1, 0), "T", "T", "S", 'T', modItems.ingotTitanium, 'S', Item.stick);
GameRegistry.addShapedRecipe(new ItemStack(enumTools.titaniumShovel, 1, 0), "T", "S", "S", 'T', modItems.ingotTitanium, 'S', Item.stick);
*/
GameRegistry.addShapedRecipe(new ItemStack(modBlocks.led, 32, 0), "G", "R", "T", 'R', Item.redstone, 'G', Item.glowstone, 'T', modItems.ingotTitanium);
GameRegistry.addShapedRecipe(new ItemStack(modBlocks.qm, 1, 0), " T ", "GRG", "TTT", 'T', modItems.ingotTitanium, 'R', modItems.ruby, 'G', Block.thinGlass);
GameRegistry.addSmelting(modItems.dustRuby.itemID, new ItemStack(modItems.ruby, 1, 0), 0.05f);
GameRegistry.addSmelting(modBlocks.ore.blockID, new ItemStack(modItems.ingotTitanium, 1, 0), 0.05f);
for (int i = 0; i < 16; ++i)
{
    GameRegistry.addShapelessRecipe(LEDtorch.torchNBT(new ItemStack(modBlocks.led, 1, 0), i), new ItemStack(Item.dyePowder, 1, i), new ItemStack(Item.itemsList[modBlocks.led.blockID], 1));
}
initBC();
initIC2();
initTE();
initIC();
ItemStack[] recipe = {null, null, null};
ItemStack result = null;
result = new ItemStack(modBlocks.node, 1, 0);
node.setNBT(result, "Item", 5, 16);
NodeRecipe.addRecipe(new NodeRecipe(new ItemStack(Block.blockNetherQuartz.blockID, 1 ,0), new ItemStack(modItems.ruby, 1 ,0), new ItemStack(Block.chest.blockID, 1 ,0), result));
result = new ItemStack(modBlocks.node, 1, 0);
node.setNBT(result, "Liquid", 5, 500);
NodeRecipe.addRecipe(new NodeRecipe(new ItemStack(Block.blockNetherQuartz.blockID, 1 ,0), new ItemStack(modItems.ruby, 1 ,0), new ItemStack(Item.bucketEmpty.itemID, 1 ,0), result));
}
public static void initBC(){
	try{
		//GameRegistry.addShapedRecipe(new ItemStack(modItems.upgrade, 1, 0), "RIR", "ICI", "RIR", 'R', Item.redstone, 'I', Item.ingotIron, 'C', BuildCraftFactory.tankBlock);
	}
	catch(Exception e){
		System.out.println("BuildCraft plugin not loaded");
	}
}
public static void initIC2(){
	try{
		
	}
	catch(Exception e){
		System.out.println("IC2 plugin not loaded");
	}
}
public static void initTE(){
	try{
		
	}
	catch(Exception e){
		System.out.println("Thermal Expansion plugin not loaded");
	}
}
public static void initIC(){
	try{
		
	}
	catch(Exception e){
		System.out.println("Iron Chests plugin not loaded");
	}
}
}
