package vtsman.mine_modus.block;

import vtsman.mine_modus.client.clientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class titaniumOre extends Block{

	public titaniumOre(int par1, Material par2Material) {
		super(par1, par2Material);
		// TODO Auto-generated constructor stub
	}
	public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(clientProxy.TEXTURES + "titaniumore");
    }
}
