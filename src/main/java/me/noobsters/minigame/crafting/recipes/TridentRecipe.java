package me.noobsters.minigame.crafting.recipes;

import me.noobsters.minigame.crafting.CustomRecipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class TridentRecipe extends CustomRecipe {

    public TridentRecipe(NamespacedKey namespacedKey) {
        super(namespacedKey);

        final ItemStack item = new ItemStack(Material.TRIDENT);

        final ShapedRecipe recipe = new ShapedRecipe(namespacedKey, item);
        recipe.shape("QQQ", "ABA", "ABA");
        recipe.setIngredient('Q', Material.QUARTZ);
        recipe.setIngredient('A', Material.AIR);
        recipe.setIngredient('B', new RecipeChoice.MaterialChoice(Material.DIAMOND, Material.PRISMARINE_CRYSTALS,
        Material.PRISMARINE_SHARD));

        setRecipe(recipe);
    }

}