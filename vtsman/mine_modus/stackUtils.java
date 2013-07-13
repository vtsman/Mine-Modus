package vtsman.mine_modus;

import net.minecraft.item.ItemStack;

public class stackUtils {
	public static ItemStack[] sizer(ItemStack[] input, int size){
		ItemStack[] stack = input;
		if(input != null){
			for(int i = 0; i < stack.length; i++){
				if(stack[i] != null){
				stack[i].stackSize = size;
				}
			}
		}
		return stack;
	}
	public static boolean stackEquals(ItemStack s1, ItemStack s2){
		return (s1.stackSize == s2.stackSize) && (s1.itemID == s2.itemID) && (s1.getItemDamage() == s2.getItemDamage());
	}
	public static boolean canStack(ItemStack s1, ItemStack s2){
		return (s1.itemID == s2.itemID) && (s1.getItemDamage() == s2.getItemDamage());

	}
}
