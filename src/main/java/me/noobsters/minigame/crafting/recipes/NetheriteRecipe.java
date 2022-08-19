package me.noobsters.minigame.crafting.recipes;

import me.noobsters.minigame.crafting.CustomRecipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class NetheriteRecipe extends CustomRecipe {

    public NetheriteRecipe(NamespacedKey namespacedKey) {
        super(namespacedKey);

        final ShapelessRecipe recipe = new ShapelessRecipe(namespacedKey, new ItemStack(Material.NETHERITE_INGOT, 2));
        recipe.addIngredient(4, Material.NETHERITE_SCRAP);
        recipe.addIngredient(4, Material.GOLD_INGOT);

        setRecipe(recipe);
    }

}