package vtsman.mine_modus.item;



import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;

import vtsman.mine_modus.client.clientProxy;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class upgrade extends Item{
	public static final String storage = "mine_modus:storageupgrade";
	public static final String range = "mine_modus:rangeupgrade";
	public static final String battery = "mine_modus:batteryupgrade";
	public Icon[] icon = new Icon[9];
	public String[] itemMetaNames = {"Tier 1 Storage Upgrade", "Tier 2 Storage Upgrade", "Tier 3 Storage Upgrade", "Tier 1 Range Upgrade", "Tier 2 Range Upgrade", "Tier 3 Range Upgrade", "Tier 1 Battery Upgrade", "Tier 2 Battery Upgrade", "Tier 3 Battery Upgrade"};
	public upgrade(int par1) {
		super(par1);
		this.setUnlocalizedName("Upgrade");
		for(int i = 0; i < this.itemMetaNames.length; i++){
		LanguageRegistry.instance().addStringLocalization("item.Upgrade." + this.itemMetaNames[i], this.itemMetaNames[i]);
		}
		// TODO Auto-generated constructor stub
	}
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
    	for(int i = 0; i < 9; i++){
    		if(i < 3){
        		this.icon[i] = par1IconRegister.registerIcon(this.storage + ((i+1)%3 + 1));
        		}
    		if(i > 2 && i < 6){
        		this.icon[i] = par1IconRegister.registerIcon(this.range + ((i+1)%3 + 1));     
        		}
    		if(i > 5){
    			this.icon[i] = par1IconRegister.registerIcon(this.battery + ((i+1)%3 + 1
    					));
        		}
    	}
    }
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
             for (int j = 0; j < icon.length; ++j)
             {
                     par3List.add(new ItemStack(par1, 1, j));
             }
    }
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
             int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 3);
			return super.getUnlocalizedName() + "." + itemMetaNames[i];
    }
    public Icon getIconFromDamage(int par1)
    {
        return this.icon[par1];
    }
}
