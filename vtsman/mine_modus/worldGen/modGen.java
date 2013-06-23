package vtsman.mine_modus.worldGen;

import cpw.mods.fml.common.registry.GameRegistry;

public class modGen {
public static void init(){
	GameRegistry.registerWorldGenerator(new enumOreGen());
}
}
