package vtsman.MineModus.client;

import net.minecraftforge.client.MinecraftForgeClient;
import vtsman.MineModus.CommonProxy;
import vtsman.MineModus.blocks.modBlocks;
import vtsman.MineModus.tileentity.QuantMatTE;
import vtsman.MineModus.tileentity.TileTag;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	public static final String TEXTURES = "testmod:";

	@Override
	public void registerRenderers() {

		ClientRegistry.bindTileEntitySpecialRenderer(QuantMatTE.class,
				new quantrender());
		MinecraftForgeClient.registerItemRenderer(modBlocks.qm.blockID,
				new TEItem(new quantModel()));

		ClientRegistry.bindTileEntitySpecialRenderer(TileTag.class,
				new CTRender());
		MinecraftForgeClient.registerItemRenderer(modBlocks.chestTag.blockID,
				new TEItem(new ChestTag()));

	}

}
