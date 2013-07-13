package vtsman.mine_modus.item.tools;

import vtsman.mine_modus.client.clientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class titaniumHoe extends ItemHoe{

	public titaniumHoe(int par1, EnumToolMaterial par2EnumToolMaterial) {
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
		 if (!player.canPlayerEdit(x, y, z, face, stack))
	        {
	            return false;
	        }
	        else
	        {
	            UseHoeEvent event = new UseHoeEvent(player, stack, world, x, y, z);
	            if (MinecraftForge.EVENT_BUS.post(event))
	            {
	                return false;
	            }

	            if (event.getResult() == Result.ALLOW)
	            {
	                stack.damageItem(1, player);
	                return true;
	            }

	            int i1 = world.getBlockId(x, y, z);
	            int j1 = world.getBlockId(x, y + 1, z);

	            if ((face == 0 || j1 != 0 || i1 != Block.grass.blockID) && i1 != Block.dirt.blockID)
	            {
	                return false;
	            }
	            else
	            {
	                Block block = Block.tilledField;
	                world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

	                if (world.isRemote)
	                {
	                    return true;
	                }
	                else
	                {
	                    world.setBlock(x, y, z, block.blockID);
	                    stack.damageItem(1, player);
	                    return true;
	                }
	            }
	        }
	}
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		this.itemIcon = par1IconRegister.registerIcon(clientProxy.TEXTURES + "hoeTitanium");
    }
}
