package me.noobsters.minigame.crafting.recipes;

import me.noobsters.minigame.crafting.CustomRecipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CarrotRecipe extends CustomRecipe {

    public CarrotRecipe(NamespacedKey namespacedKey) {
        super(namespacedKey);

        final ItemStack item = new ItemStack(Material.GOLDEN_CARROT);

        final ShapedRecipe recipe = new ShapedRecipe(namespacedKey, item);
        recipe.shape("AAA", "ABA", "AAA");
        recipe.setIngredient('A', Material.GOLD_INGOT);
        recipe.setIngredient('B', Material.CARROT);

        setRecipe(recipe);
    }

}