package vtsman.mine_modus.item;

import java.util.List;

import vtsman.mine_modus.client.clientProxy;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ingotTitanium extends Item{
	public ingotTitanium(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		this.itemIcon = par1IconRegister.registerIcon(clientProxy.TEXTURES + "ingotTitanium");
    }
}
