package me.noobsters.minigame.crafting.recipes;

import me.noobsters.minigame.crafting.CustomRecipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class SaddleRecipe extends CustomRecipe {

    public SaddleRecipe(NamespacedKey namespacedKey) {
        super(namespacedKey);

        final ItemStack item = new ItemStack(Material.SADDLE);

        final ShapedRecipe recipe = new ShapedRecipe(namespacedKey, item);
        recipe.shape("LAL", "LLL", "LAL");
        recipe.setIngredient('A', Material.IRON_INGOT);
        recipe.setIngredient('L', Material.LEATHER);

        setRecipe(recipe);
    }

}
