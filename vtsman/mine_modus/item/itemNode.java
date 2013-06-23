package vtsman.mine_modus.item;

import vtsman.mine_modus.tileentity.node;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class itemNode extends ItemBlock{

	public itemNode(int par1) {
		super(par1);

		// TODO Auto-generated constructor stub
	}
	   public int getMetadata(int par1)
	    {
	        return par1;
	    }
		@Override
		public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
			int x, int y, int z, int face, float par8, float par9, float par10)
		{
			//System.out.printf("ItemShape.onItemUse\n");
			if (super.onItemUse(stack, player, world, x, y, z, face, par8, par9, par10)) {
				node te = vtsman.mine_modus.block.node.lastTE;
				if (te != null) {
					NBTTagCompound tag = stack.getTagCompound();
					if (tag != null) {
						//System.out.printf("ItemShape.onItemUse: initialising TEShape\n");
						te.setType(tag.getString("Type"), tag.getInteger("Range"), tag.getInteger("Capacity"));
					}
				}
				return true;
			}
			return false;
		}
}