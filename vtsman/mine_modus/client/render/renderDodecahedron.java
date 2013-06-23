package vtsman.mine_modus.client.render;

import vtsman.mine_modus.tileentity.node;
import vtsman.mine_modus.client.models.Model_Node;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class renderDodecahedron extends TileEntitySpecialRenderer{
	Model_Node RenderNode = new Model_Node();
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y,
			double z, float f) {
		RenderNode.render((node)tileEntity, x, y, z);
		
	}
}
