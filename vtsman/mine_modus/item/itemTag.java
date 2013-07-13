package vtsman.mine_modus.item;

import vtsman.mine_modus.block.BlockChestTag;
import vtsman.mine_modus.block.LEDtorch;
import vtsman.mine_modus.client.models.ChestTag;
import vtsman.mine_modus.tileentity.colorTorch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class itemTag extends ItemBlock{

	public itemTag(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
		int x, int y, int z, int face, float par8, float par9, float par10)
	{
		if (super.onItemUse(stack, player, world, x, y, z, face, par8, par9, par10)) {
			switch(face){
			case 0: world.setBlockMetadataWithNotify(BlockChestTag.lastX, BlockChestTag.lastZ, BlockChestTag.lastY, 2, 0);
			case 1: world.setBlockMetadataWithNotify(BlockChestTag.lastX, BlockChestTag.lastZ, BlockChestTag.lastY, 1, 0);
			case 2: world.setBlockMetadataWithNotify(BlockChestTag.lastX, BlockChestTag.lastZ, BlockChestTag.lastY, 3, 0);
			case 3: world.setBlockMetadataWithNotify(BlockChestTag.lastX, BlockChestTag.lastZ, BlockChestTag.lastY, 0, 0);
			default: world.setBlockMetadataWithNotify(BlockChestTag.lastX, BlockChestTag.lastZ, BlockChestTag.lastY, 0, 0);
			}
			return true;
		}
		return true;

	}

}
