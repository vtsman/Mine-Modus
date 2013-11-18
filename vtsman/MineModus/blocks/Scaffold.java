package vtsman.MineModus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Scaffold extends Block {
	/**
	 * 
	 * @param BlockID
	 * @param Material
	 */

	public Scaffold(int par1, Material par2Material) {
		super(par1, par2Material);

	}

	@Override
	public boolean isLadder(World world, int x, int y, int z,
			EntityLivingBase entity) {
		return true;
	}

	/*
	 * public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int
	 * par2, int par3, int par4) { return AxisAlignedBB.getAABBPool().getAABB(
	 * (double) par2 + this.minX + 0.2, (double) par3 + this.minY + 0.2,
	 * (double) par4 + this.minZ + 0.2, (double) par2 + this.maxX - 0.2,
	 * (double) par3 + this.maxY - 0.2, (double) par4 + this.maxZ - 0.2); }
	 */

	public void updateLadderBounds(int par1) {
		float f = 0.125F;

		if (par1 == 0) {
			this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
		}

		if (par1 == 3) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
		}

		if (par1 == 4) {
			this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}

		if (par1 == 5) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
		}
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 0;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return this.canBlockStay(world, x, y, z);
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public void registerIcons(IconRegister ir) {
		this.blockIcon = ir.registerIcon("minemodus:block");
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int par5) {
		if (!canBlockStay(world, x, y, z)) {
			world.setBlockToAir(x, y, z);
			if (!world.isRemote) {
				EntityItem e = new EntityItem(world, x, y, z, new ItemStack(
						this, 1));
				world.spawnEntityInWorld(e);
			}
		}
	}

	public boolean canBlockStay(World world, int x, int y, int z) {
		return !(world.getBlockId(x, y - 1, z) == 0);
	}

	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack s = player.getHeldItem();
		if (s != null) {
			if (s.itemID == this.blockID) {
				// put in scaffolding code here
				int yinc = 1;
				while (true) {
					if (yinc + y >= 256) {
						break;
					}
					if (world.getBlockId(x, y + yinc, z) == this.blockID) {
						yinc++;
					} else {
						if (world.getBlockId(x, y + yinc, z) == 0) {
							world.setBlock(x, y + yinc, z, this.blockID);
							if (player instanceof EntityPlayerSP) {
								StepSound stepsound = this.stepSound;
								player.playSound(stepsound.getStepSound(),
										stepsound.getVolume() * 0.15F,
										stepsound.getPitch());
							}
							if (player instanceof EntityPlayerMP) {
								EntityPlayerMP p = (EntityPlayerMP) player;
								if (!p.theItemInWorldManager.isCreative()) {
									s.stackSize--;
								}

							}
							return true;
						} else {
							return false;
						}
					}
				}

			} else {
				if (hasBlock(s)) {
					swap(world, x, y, z, s);
					if (player instanceof EntityPlayerMP) {
						EntityPlayerMP p = (EntityPlayerMP) player;
						if (!p.theItemInWorldManager.isCreative()) {
							s.stackSize--;
						}

					}
					return true;
				}
			}
		}
		return false;
	}

	public static boolean hasBlock(ItemStack s) {
		String name = Item.itemsList[s.itemID].getUnlocalizedName();
		if (name.substring(0, 4).matches("item")) {
			return false;
		}
		return true;
	}

	public static void swap(World world, int x, int y, int z, ItemStack s) {
		world.setBlock(x, y, z, s.itemID, s.getItemDamage(), 1);
		if (!world.isRemote) {
			EntityItem e = new EntityItem(world, x, y, z, new ItemStack(
					modBlocks.scaffold, 1));
			world.spawnEntityInWorld(e);
		}
	}
}
