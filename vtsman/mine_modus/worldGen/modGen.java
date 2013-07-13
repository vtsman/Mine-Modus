package vtsman.mine_modus.worldGen;

import vtsman.mine_modus.worldGen.biome.craig;
import cpw.mods.fml.common.registry.GameRegistry;

public class modGen {
public static void init(){
	GameRegistry.registerWorldGenerator(new enumOreGen());
	GameRegistry.addBiome(new craig(50));
}
}
