package me.noobsters.minigame.crafting.recipes;

import me.noobsters.minigame.crafting.CustomRecipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class SimpleNetherite extends CustomRecipe {

    public SimpleNetherite(NamespacedKey namespacedKey) {
        super(namespacedKey);

        final ShapelessRecipe recipe = new ShapelessRecipe(namespacedKey, new ItemStack(Material.NETHERITE_INGOT));
        recipe.addIngredient(2, Material.NETHERITE_SCRAP);
        recipe.addIngredient(2, Material.GOLD_INGOT);

        setRecipe(recipe);
    }

}