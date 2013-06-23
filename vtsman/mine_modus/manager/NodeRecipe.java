package vtsman.mine_modus.manager;


import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.asn1.cms.Time;

import vtsman.mine_modus.stackUtils;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class NodeRecipe {
	private static List<NodeRecipe> recipes = new ArrayList<NodeRecipe>();
	private ItemStack[] recipe = new ItemStack[3];
	private ItemStack result = null;
	
	public NodeRecipe(ItemStack s1, ItemStack s2, ItemStack s3, ItemStack res){
		this.recipe[0] = s1;
		this.recipe[1] = s2;
		this.recipe[2] = s3;
		this.result = res;
	}

	public static void addRecipe(NodeRecipe r){
		recipes.add(r);
	}
	private static boolean compare(ItemStack[] s1, ItemStack[] input){
		boolean[] inputCheck = {false, false, false};
		boolean[] recipeCheck = {false, false, false};
		ItemStack[] stack1 = stackUtils.sizer(s1, 1);
		ItemStack[] stack2 = stackUtils.sizer(input, 1);
		int comp = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(stackUtils.stackEquals(stack1[i], stack2[j]) && !inputCheck[j] && !recipeCheck[i]){
					inputCheck[j] = true;
					recipeCheck[i] = true;
					comp++;
				}
			}
		}
		return comp == 3;
	}
	public static ItemStack getOutput(ItemStack[] input){
		int i = 0;
		while(i <  recipes.size()){
			for(int j = 0; j <3; j++){
				System.out.println(recipes.get(1).recipe[j]);
			}
			if(compare(recipes.get(i).recipe, input)){
				return recipes.get(i).result;
			}
			i++;
		}
		return null;
	}
}
