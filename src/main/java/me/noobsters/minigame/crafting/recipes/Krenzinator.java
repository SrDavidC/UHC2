package me.noobsters.minigame.crafting.recipes;

import me.noobsters.minigame.crafting.CustomRecipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class Krenzinator extends CustomRecipe {

    public Krenzinator(NamespacedKey namespacedKey) {
        super(namespacedKey);

        final ShapelessRecipe recipe = new ShapelessRecipe(namespacedKey, new ItemStack(Material.DIAMOND));
        recipe.addIngredient(9, Material.REDSTONE_BLOCK);

        setRecipe(recipe);
    }

}