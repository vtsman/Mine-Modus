package vtsman.MineModus.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vtsman.MineModus.tileentity.QuantMatTE;
import vtsman.vtsmcUtil.MathUtils;

public class QuantMatBlock extends BlockContainer {

	public QuantMatBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	/**
	 * The type of render function that is called for this block
	 */

	public int getRenderType() {
		return -1;
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = Block.blockIron.getBlockTextureFromSide(1);
	}

	/**
	 * Is this block (a) opaque and (B) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */

	public boolean isOpaqueCube() {
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z) {
		Random rand = new Random();

		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory)) {
			return;
		}
		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;
				int loop = MathUtils.divUp(item.stackSize, 64) - 1;
				int fin = item.stackSize % 64;
				for (int k = 0; k < loop; k++) {
					ItemStack out = item.copy();
					out.stackSize = 64;
					EntityItem entityItem = new EntityItem(world, x + rx, y
							+ ry, z + rz, out);
					float factor = 0.05F;
					entityItem.motionX = rand.nextGaussian() * factor;
					entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
					entityItem.motionZ = rand.nextGaussian() * factor;
					world.spawnEntityInWorld(entityItem);
				}
				ItemStack out = item.copy();
				out.stackSize = fin;
				EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z
						+ rz, out);
				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);

				item.stackSize = 0;
			}
		}
	}

	public TileEntity createNewTileEntity(World par1World) {

		return new QuantMatTE();
	}

}