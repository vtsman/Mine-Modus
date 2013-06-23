package vtsman.mine_modus.client.render;

import org.lwjgl.opengl.GL11;

import vtsman.mine_modus.client.models.Quantum_Materializer_model;
import vtsman.mine_modus.tileentity.Quantum_Materializer_TE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

public class QuantumItemModel implements IItemRenderer {

private Quantum_Materializer_model Model;

public QuantumItemModel() {

Model = new Quantum_Materializer_model();
}

@Override
public boolean handleRenderType(ItemStack item, ItemRenderType type) {

return true;
}

@Override
public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

return true;
}

@Override
public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
TileEntityRenderer.instance.renderTileEntityAt(new Quantum_Materializer_TE(), 0.0D, 0.0D, 0.0D, 0.0F);
}
}