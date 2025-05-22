package me.not_ryuzaki.rTP2;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class ConfirmGUI {
    public static void openRTPGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "Random Teleport");

        // Determine the dimension and block
        World.Environment env = player.getWorld().getEnvironment();
        Material blockMaterial;
        String dimensionName;

        switch (env) {
            case NETHER:
                blockMaterial = Material.NETHERRACK;
                dimensionName = ChatColor.RED + "Nether";
                break;
            case THE_END:
                blockMaterial = Material.END_STONE;
                dimensionName = ChatColor.LIGHT_PURPLE + "The End";
                break;
            default:
                blockMaterial = Material.GRASS_BLOCK;
                dimensionName = ChatColor.GREEN + "Overworld";
                break;
        }

        // Middle item showing the dimension
        ItemStack block = new ItemStack(blockMaterial);
        ItemMeta blockMeta = block.getItemMeta();
        blockMeta.setDisplayName(dimensionName);
        block.setItemMeta(blockMeta);

        // Cancel button
        ItemStack redPane = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta redPaneMeta = redPane.getItemMeta();
        redPaneMeta.setDisplayName(ChatColor.RED + "Cancel");
        redPaneMeta.setLore(Collections.singletonList(ChatColor.WHITE + "Click to cancel"));
        redPane.setItemMeta(redPaneMeta);

        // Confirm button
        ItemStack greenPane = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta greenPaneMeta = greenPane.getItemMeta();
        greenPaneMeta.setDisplayName(ChatColor.GREEN + "Confirm");
        greenPaneMeta.setLore(Collections.singletonList(ChatColor.WHITE + "Click to confirm"));
        greenPane.setItemMeta(greenPaneMeta);

        // Place items in the GUI
        gui.setItem(11, redPane);
        gui.setItem(13, block);
        gui.setItem(15, greenPane);

        player.openInventory(gui);
    }
}
