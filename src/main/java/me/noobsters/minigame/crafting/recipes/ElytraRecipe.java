package me.noobsters.minigame.crafting.recipes;

import me.noobsters.minigame.crafting.CustomRecipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class ElytraRecipe extends CustomRecipe {

    public ElytraRecipe(NamespacedKey namespacedKey) {
        super(namespacedKey);

        final ItemStack item = new ItemStack(Material.ELYTRA);

        final ShapedRecipe recipe = new ShapedRecipe(namespacedKey, item);
        recipe.shape("ISI", "PEP", "FAF");
        recipe.setIngredient('E', Material.ENDER_EYE);
        recipe.setIngredient('I', Material.DIAMOND);
        recipe.setIngredient('S', Material.STRING);
        recipe.setIngredient('A', Material.AIR);
        recipe.setIngredient('F', Material.FEATHER);
        recipe.setIngredient('P', Material.PAPER);

        setRecipe(recipe);
    }

}