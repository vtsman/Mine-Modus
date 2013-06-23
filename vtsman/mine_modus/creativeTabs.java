package vtsman.mine_modus;

import cpw.mods.fml.common.registry.LanguageRegistry;
import vtsman.mine_modus.block.modBlocks;
import vtsman.mine_modus.block.node;
import vtsman.mine_modus.item.modItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class creativeTabs {
	public static CreativeTabs tabModusNodes = new CreativeTabs("tabModusNodes") {
	    public ItemStack getIconItemStack() {
	    	ItemStack stack = new ItemStack(modBlocks.node.blockID, 1, 0);
	        node.setNBT(stack, "Energy", 10, 100);
	    	return stack;
	}
};
public static CreativeTabs tabModusItems = new CreativeTabs("tabModusItems") {
    public ItemStack getIconItemStack() {
    	ItemStack stack = new ItemStack(modItems.upgrade.itemID, 1, 0);
    	return stack;
}
};
public static CreativeTabs tabModusBlocks = new CreativeTabs("tabModusBlocks") {
    public ItemStack getIconItemStack() {
    	ItemStack stack = new ItemStack(modBlocks.ore.blockID, 1, 0);
    	return stack;
}
};
public void init(){
	LanguageRegistry.instance().addStringLocalization("itemGroup.tabModusNodes", "en_US", "Nodes");
	LanguageRegistry.instance().addStringLocalization("itemGroup.tabModusBlocks", "en_US", "Mine Modus Blocks");
	LanguageRegistry.instance().addStringLocalization("itemGroup.tabModusItems", "en_US", "Mine Modus Items");
}
}
