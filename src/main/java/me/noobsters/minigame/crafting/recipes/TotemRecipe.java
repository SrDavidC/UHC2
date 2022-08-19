package me.noobsters.minigame.crafting.recipes;

import me.noobsters.minigame.crafting.CustomRecipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class TotemRecipe extends CustomRecipe {

    public TotemRecipe(NamespacedKey namespacedKey) {
        super(namespacedKey);

        final ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);

        final ShapedRecipe recipe = new ShapedRecipe(namespacedKey, item);
        recipe.shape("EAE", "AAA", "BAB");
        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('A', Material.GOLD_BLOCK);
        recipe.setIngredient('B', Material.AIR);

        setRecipe(recipe);
    }

}