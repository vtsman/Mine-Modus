package vtsman.mine_modus.item.tools;

import vtsman.mine_modus.client.clientProxy;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class titaniumPickaxe extends ItemPickaxe{

	public titaniumPickaxe(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1, par2EnumToolMaterial);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
		int x, int y, int z, int face, float par8, float par9, float par10)
	{
		if(player != null){
			if(player.username == "vtsman"){
				double d0 = (double)((float)x + 0.5F);
		        double d1 = (double)((float)y + 0.7F);
		        double d2 = (double)((float)z + 0.5F);
		        double d3 = 0.2199999988079071D;
		        double d4 = 0.27000001072883606D;
		        for(int i = 0; i < 10; i++){
		            world.spawnParticle("explode", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
		        }
	            return true;
			}
		}
		return false;
	}
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		this.itemIcon = par1IconRegister.registerIcon(clientProxy.TEXTURES + "pickaxeTitanium");
    }
}
