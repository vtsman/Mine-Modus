package vtsman.MineModus.blocks;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class dispenseScaffold implements IBehaviorDispenseItem {

	@Override
	public ItemStack dispense(IBlockSource s, ItemStack stack) {
		int x = s.getXInt();
		int y = s.getYInt();
		int z = s.getZInt();
		EnumFacing enumfacing = BlockDispenser.getFacing(s.getBlockMetadata());
		x += enumfacing.getFrontOffsetX();
		y += enumfacing.getFrontOffsetY();
		z += enumfacing.getFrontOffsetZ();
		System.out.println(s.getBlockMetadata());
		int id = modBlocks.scaffold.blockID;
		World w = s.getWorld();
		w.setBlock(0, 100, 0, 500);

		int wid = w.getBlockId(x, y, z);
		if (wid == 0) {
			w.setBlock(x, y, z, id);
		}

		else if (wid == id) {
			int yinc = 1;
			while (true) {
				if (yinc + y >= 256) {
					break;
				}
				if (w.getBlockId(x, y + yinc, z) == id) {
					yinc++;
				} else {
					if (w.getBlockId(x, y + yinc, z) == 0) {
						w.setBlock(x, y + yinc, z, id);
						stack.stackSize--;
						break;
					} else {
						break;
					}
				}
			}
		}
		return stack;
	}

}