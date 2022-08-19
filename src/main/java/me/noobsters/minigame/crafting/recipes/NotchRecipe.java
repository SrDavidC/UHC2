package me.noobsters.minigame.crafting.recipes;

import me.noobsters.minigame.crafting.CustomRecipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class NotchRecipe extends CustomRecipe {

    public NotchRecipe(NamespacedKey namespacedKey) {
        super(namespacedKey);

        final ItemStack item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);

        final ShapedRecipe recipe = new ShapedRecipe(namespacedKey, item);
        recipe.shape("AAA", "ABA", "AAA");
        recipe.setIngredient('A', Material.GOLD_BLOCK);
        recipe.setIngredient('B', Material.APPLE);

        setRecipe(recipe);
    }

}