package vtsman.mine_modus.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import vtsman.mine_modus.commonProxy;
import vtsman.mine_modus.block.BlockChestTag;
import vtsman.mine_modus.block.modBlocks;
import vtsman.mine_modus.client.render.*;
import vtsman.mine_modus.tileentity.Quantum_Materializer_TE;
import vtsman.mine_modus.tileentity.colorTorch;
import vtsman.mine_modus.tileentity.node;
import vtsman.mine_modus.tileentity.tileChestTag;
import net.minecraftforge.client.MinecraftForgeClient;
public class clientProxy extends commonProxy {
public static String TEXTURES = "minemodus:";
@Override
public void registerRenderInformation(){
		ClientRegistry.bindTileEntitySpecialRenderer(node.class, new renderDodecahedron());
	//	MinecraftForgeClient.registerItemRenderer(modBlocks.node.blockID, new renderNodeItem());
		ClientRegistry.bindTileEntitySpecialRenderer(Quantum_Materializer_TE.class, new Quantum_Materializer_TE_Renderer()); 	   	MinecraftForgeClient.registerItemRenderer(modBlocks.qm.blockID, new QuantumItemModel());
		ClientRegistry.bindTileEntitySpecialRenderer(tileChestTag.class, new ChestTagTE()); 
	   	MinecraftForgeClient.registerItemRenderer(modBlocks.chesttag.blockID, new itemTagRender());
	}

}