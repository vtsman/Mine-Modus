package vtsman.MineModus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.BlockSnow;
import net.minecraft.util.Facing;
import net.minecraft.world.World;

public class TilePistonBase extends BlockPistonBase {
	public boolean sticky;

	public TilePistonBase(int par1, boolean par2) {
		super(par1, par2);
		this.sticky = par2;
		// TODO Auto-generated constructor stub
	}

	private static boolean canPushBlock(int par0, World par1World, int par2,
			int par3, int par4, boolean par5) {
		if (par0 == Block.obsidian.blockID) {
			return false;
		} else {
			if (par0 != Block.pistonBase.blockID
					&& par0 != Block.pistonStickyBase.blockID) {
				if (Block.blocksList[par0].getBlockHardness(par1World, par2,
						par3, par4) == -1.0F) {
					return false;
				}

				if (Block.blocksList[par0].getMobilityFlag() == 2) {
					return false;
				}

				if (Block.blocksList[par0].getMobilityFlag() == 1) {
					if (!par5) {
						return false;
					}

					return true;
				}
			} else if (isExtended(par1World.getBlockMetadata(par2, par3, par4))) {
				return false;
			}

			return !par1World.blockHasTileEntity(par2, par3, par4);
		}
	}

	private boolean tryExtend(World par1World, int par2, int par3, int par4,
			int par5) {
		int i1 = par2 + Facing.offsetsXForSide[par5];
		int j1 = par3 + Facing.offsetsYForSide[par5];
		int k1 = par4 + Facing.offsetsZForSide[par5];
		int l1 = 0;
		while (true) {
			int i2;

			if (l1 < 13) {
				if (j1 <= 0 || j1 >= par1World.getHeight() - 1) {
					return false;
				}

				i2 = par1World.getBlockId(i1, j1, k1);

				if (!par1World.isAirBlock(i1, j1, k1)) {
					if (!canPushBlock(i2, par1World, i1, j1, k1, true)) {
						return false;
					}

					if (Block.blocksList[i2].getMobilityFlag() != 1) {
						if (l1 == 12) {
							return false;
						}

						i1 += Facing.offsetsXForSide[par5];
						j1 += Facing.offsetsYForSide[par5];
						k1 += Facing.offsetsZForSide[par5];
						++l1;
						continue;
					}

					// With our change to how snowballs are dropped this needs
					// to disallow to mimic vanilla behavior.
					float chance = (Block.blocksList[i2] instanceof BlockSnow ? -1.0f
							: 1.0f);
					Block.blocksList[i2].dropBlockAsItemWithChance(par1World,
							i1, j1, k1, par1World.getBlockMetadata(i1, j1, k1),
							chance, 0);
					par1World.setBlockToAir(i1, j1, k1);
				}
			}

			l1 = i1;
			i2 = j1;
			int j2 = k1;
			int k2 = 0;
			int[] aint;
			int l2;
			int i3;
			int j3;

			for (aint = new int[13]; i1 != par2 || j1 != par3 || k1 != par4; k1 = j3) {
				l2 = i1 - Facing.offsetsXForSide[par5];
				i3 = j1 - Facing.offsetsYForSide[par5];
				j3 = k1 - Facing.offsetsZForSide[par5];
				int k3 = par1World.getBlockId(l2, i3, j3);
				int l3 = par1World.getBlockMetadata(l2, i3, j3);

				if (k3 == this.blockID && l2 == par2 && i3 == par3
						&& j3 == par4) {
					par1World.setBlock(i1, j1, k1, modBlocks.tpmove.blockID,
							par5 | (this.sticky ? 8 : 0), 4);
					par1World
							.setBlockTileEntity(i1, j1, k1, BlockPistonMoving
									.getTileEntity(modBlocks.tpex.blockID, par5
											| (this.sticky ? 8 : 0), par5,
											true, false));
				} else {
					par1World.setBlock(i1, j1, k1, modBlocks.tpmove.blockID,
							l3, 4);
					par1World.setBlockTileEntity(i1, j1, k1, BlockPistonMoving
							.getTileEntity(k3, l3, par5, true, false));
				}

				aint[k2++] = k3;
				i1 = l2;
				j1 = i3;
			}

			i1 = l1;
			j1 = i2;
			k1 = j2;

			for (k2 = 0; i1 != par2 || j1 != par3 || k1 != par4; k1 = j3) {
				l2 = i1 - Facing.offsetsXForSide[par5];
				i3 = j1 - Facing.offsetsYForSide[par5];
				j3 = k1 - Facing.offsetsZForSide[par5];
				par1World.notifyBlocksOfNeighborChange(l2, i3, j3, aint[k2++]);
				i1 = l2;
				j1 = i3;
			}

			return true;
		}
	}

}
