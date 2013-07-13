package vtsman.mine_modus.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface INode {
		public boolean canStore(ItemStack stack, FluidStack Stack, double energy);
	    public boolean addItems(ItemStack stack);
	    public boolean addLiquid(FluidStack stack);
	    public boolean addEnergy(double energy);
	    public double getPercentItems();
	    public double getPercentLiquid();
	    public double getPercentEnergy();
	    public boolean handshake(String type, String network);
	    
}
