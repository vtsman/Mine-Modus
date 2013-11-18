package vtsman.MineModus;

import vtsman.MineModus.tileentity.QuantMatTE;
import vtsman.MineModus.tileentity.TileTag;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	// Client stuff
	public void registerTEs() {
		System.out.println("Mine modus registering tile entities");
		GameRegistry.registerTileEntity(QuantMatTE.class, "MineModus.Quantum");
		GameRegistry.registerTileEntity(TileTag.class, "MineModus.Tag");
	}

	public void registerRenderers() {
		// Nothing here as the server doesn't render graphics or entities!
	}
}