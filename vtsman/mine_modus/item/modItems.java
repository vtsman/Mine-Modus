package vtsman.mine_modus.item;

import vtsman.mine_modus.creativeTabs;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class modItems {
public static Item infoReader = new itemInformationScanner(1000).setCreativeTab(creativeTabs.tabModusItems).setUnlocalizedName("information scanner");
public static Item debug = new debug_add_energy(1001).setCreativeTab(creativeTabs.tabModusItems).setUnlocalizedName("debug tool");
public static Item upgrade = new upgrade(1002).setCreativeTab(creativeTabs.tabModusItems);
public static Item dustRuby = new ItemRubyDust(1004).setCreativeTab(creativeTabs.tabModusItems).setUnlocalizedName("RubyDust");
public static Item chunkTitanium = new ItemTitaniumChunk(1005).setCreativeTab(creativeTabs.tabModusItems).setUnlocalizedName("ChunkTitanium");
public static Item ingotTitanium = new ingotTitanium(1006).setCreativeTab(creativeTabs.tabModusItems).setUnlocalizedName("ingotTitanium");
public static Item ruby = new itemRuby(1007).setCreativeTab(creativeTabs.tabModusItems).setUnlocalizedName("ruby");
public static Item energyDiamond = new itemEnergyDiamond(1008).setCreativeTab(creativeTabs.tabModusItems).setUnlocalizedName("energyDiamond");
public void init(){
	LanguageRegistry.addName(debug, "debug tool");
	LanguageRegistry.addName(infoReader, "Information Scanner");
	LanguageRegistry.addName(dustRuby, "Ruby Dust");
	LanguageRegistry.addName(chunkTitanium, "Chunk of Titanium");
	LanguageRegistry.addName(ingotTitanium, "Titanium Ingot");
	LanguageRegistry.addName(ruby, "Synthetic ruby");
	LanguageRegistry.addName(energyDiamond, "Energized diamond");
}
}
