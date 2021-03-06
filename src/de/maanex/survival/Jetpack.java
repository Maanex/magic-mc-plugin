package de.maanex.survival;


import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import de.maanex.utils.ParticleUtil;


public class Jetpack implements Listener {

	public static final ItemStack	ITEM_IRON;
	public static final ItemStack	ITEM_DIA;

	static {
		ITEM_IRON = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta m = ITEM_IRON.getItemMeta();
		m.setDisplayName("�2Jetpack");
		m.setLore(Arrays.asList("�0jetpack", "�7Fliegen wie im Creativ-Modus!", "�7Aber achte drauf, dass du", "�7immer genug �6Kohle�7 dabei hast!"));
		ITEM_IRON.setItemMeta(m);

		ITEM_DIA = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta m2 = ITEM_DIA.getItemMeta();
		m2.setDisplayName("�cAdvanced Jetpack");
		m2.setLore(Arrays.asList("�0advjetpack", "�7Fliegen wie im Creativ-Modus!", "�7Doppelt so schnell und", "�7Energieeffizienter als sein Vorg�nger"));
		ITEM_DIA.setItemMeta(m2);
	}

	@SuppressWarnings("deprecation")
	public static void registerRecipe() {
		ShapedRecipe res = new ShapedRecipe(ITEM_IRON);
		res.shape("SRS", "RBR", "KKK");
		res.setIngredient('S', Material.GUNPOWDER);
		res.setIngredient('R', Material.REDSTONE_BLOCK);
		res.setIngredient('B', Material.IRON_CHESTPLATE);
		res.setIngredient('K', Material.COAL);
		Bukkit.addRecipe(res);

		ShapedRecipe res2 = new ShapedRecipe(ITEM_DIA);
		res2.shape("GGG", "CDC", "OPO");
		res2.setIngredient('G', Material.GHAST_SPAWN_EGG);
		res2.setIngredient('C', Material.COAL_BLOCK);
		res2.setIngredient('O', Material.FURNACE);
		res2.setIngredient('P', Material.PISTON);
		res2.setIngredient('D', Material.DIAMOND_CHESTPLATE);
		Bukkit.addRecipe(res2);
	}

	@EventHandler
	public void onArmorEquip(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			check(p);
		}
	}

	@EventHandler
	public void onGamemodeChange(PlayerGameModeChangeEvent e) {
		if (!isSurvival(e.getPlayer())) {
			e.getPlayer().setAllowFlight(true);
			e.getPlayer().setFlySpeed(.1f);
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		check(e.getPlayer());
	}

	private void check(Player p) {
		if (!isSurvival(p)) return;
		if (p.getInventory().getChestplate() != null && p.getInventory().getChestplate().hasItemMeta() && p.getInventory().getChestplate().getItemMeta().hasLore()
				&& p.getInventory().getChestplate().getItemMeta().getLore().contains("�0jetpack") && hasFuel(p)) {
			p.setAllowFlight(true);
			p.setFlySpeed(.05f);
		} else if (p.getInventory().getChestplate() != null && p.getInventory().getChestplate().hasItemMeta() && p.getInventory().getChestplate().getItemMeta().hasLore()
				&& p.getInventory().getChestplate().getItemMeta().getLore().contains("�0advjetpack") && hasFuel(p)) {
			p.setAllowFlight(true);
			p.setFlySpeed(.1f);
		} else {
			p.setAllowFlight(false);
		}
	}

	private static boolean hasFuelItem(Player p) {
		return p.getInventory().contains(Material.COAL) || p.getInventory().contains(Material.COAL_BLOCK);
	}

	private static boolean hasFuel(Player p) {
		return hasFuelItem(p) || (fuel.containsKey(p) && fuel.get(p) > 0);
	}

	private static int removeFuel(Player p, boolean adv) {
		for (ItemStack s : p.getInventory().getContents()) {
			if (s != null) {
				if (s.getType().equals(Material.COAL)) {
					// if (s.getAmount() == 1) s.setType(Material.AIR); else
					s.setAmount(s.getAmount() - 1);
					if (adv) return 20 * 6;
					else return 20 * 3;
				}
				if (s.getType().equals(Material.COAL_BLOCK)) {
					// if (s.getAmount() == 1) s.setType(Material.AIR); else
					s.setAmount(s.getAmount() - 1);
					if (adv) return 20 * 6 * 9;
					else return 20 * 3 * 9;
				}
			}
		}
		return 0;
	}

	private static boolean isSurvival(Player p) {
		return p.getGameMode().equals(GameMode.SURVIVAL) || p.getGameMode().equals(GameMode.ADVENTURE);
	}

	private static HashMap<Player, Integer> fuel = new HashMap<>();

	public static void tick() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.isFlying() && isSurvival(p) && p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getItemMeta().hasLore()
					&& (p.getInventory().getChestplate().getItemMeta().getLore().contains("�0jetpack") || p.getInventory().getChestplate().getItemMeta().getLore().contains("�0advjetpack"))) {
				if (!fuel.containsKey(p)) fuel.put(p, 0);
				if (fuel.get(p) <= 0) {
					if (hasFuelItem(p)) {
						fuel.put(p, removeFuel(p, p.getInventory().getChestplate().getItemMeta().getLore().contains("�0advjetpack")));
					} else {
						p.setFlying(false);
						p.setAllowFlight(false);
					}
				} else fuel.put(p, fuel.get(p) - 1);

				ParticleUtil.spawn(Particle.FLAME, p.getLocation(), 5, 0, .3, .3, .3);
			} else if (p.isFlying() && isSurvival(p)) {
				p.setFlying(false);
				p.setAllowFlight(false);
			}
		}
	}

}
