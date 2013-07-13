package vtsman.mine_modus.item;

import vtsman.mine_modus.block.LEDtorch;
import vtsman.mine_modus.tileentity.colorTorch;
import vtsman.mine_modus.tileentity.node;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class itemTorch extends ItemBlock{

	public itemTorch(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
		int x, int y, int z, int face, float par8, float par9, float par10)
	{
		//System.out.printf("ItemShape.onItemUse\n");
		if (super.onItemUse(stack, player, world, x, y, z, face, par8, par9, par10)) {
			colorTorch te = LEDtorch.lastTE;
			if (te != null) {
				NBTTagCompound tag = stack.getTagCompound();
				if (tag != null) {
					//System.out.printf("ItemShape.onItemUse: initialising TEShape\n");
					te.color = tag.getInteger("Color");
				}
			}
			return true;
		}
		return false;
	}
}
