package de.maanex.magic.spells.building;


import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.maanex.magic.MagicPlayer;
import de.maanex.magic.basic.Element;
import de.maanex.magic.spell.MagicSpell;
import de.maanex.magic.spell.SpellCategory;
import de.maanex.magic.spell.SpellRarity;
import de.maanex.magic.spell.SpellType;
import de.maanex.magic.wands.WandType;
import de.maanex.magic.wands.WandValues;


public class BlockSwarper extends MagicSpell {

	public BlockSwarper() {
		super(44, "Blocktauscher", "1. Ersetzblock Shift-Linksklicken\n2. Zielbl�cke Normal-Linksklicken", 5, 2, SpellType.ACTIVE, SpellCategory.BUILDING, SpellRarity.VERY_RARE, "Reichweite :air:");
	}

	private static HashMap<MagicPlayer, Material> replacementBlocks = new HashMap<>();

	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, WandValues val) {
		if (val.getElement(Element.ESSENCE_BUILDER) <= 0) return;

		Block target = caster.getMCPlayer().getTargetBlock(null, val.getElement(Element.AIR) * 2);
		if (caster.getMCPlayer().isSneaking()) {
			if (!target.isEmpty()) {
				replacementBlocks.put(caster, target.getType());
				caster.getMCPlayer().playSound(caster.getMCPlayer().getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
			} else caster.getMCPlayer().playSound(caster.getMCPlayer().getEyeLocation(), Sound.ENTITY_ITEM_BREAK, 1f, 1f);
		} else {
			if (!replacementBlocks.containsKey(caster)) caster.getMCPlayer().playSound(caster.getMCPlayer().getEyeLocation(), Sound.ENTITY_ITEM_BREAK, 1f, 1f);
			else for (int x = -1; x < 2; x++)
				for (int y = -1; y < 2; y++)
					for (int z = -1; z < 2; z++) {
						Location l = target.getLocation().clone().add(x, y, z);
						if (l.getBlock().isEmpty()) {
							caster.getMCPlayer().playSound(caster.getMCPlayer().getEyeLocation(), Sound.ENTITY_ARMOR_STAND_HIT, 1f, 1f);
							continue;
						}

						Material block = replacementBlocks.get(caster);

						if (l.getBlock().getType().equals(block)) continue;

						Inventory i = caster.getMCPlayer().getInventory();
						boolean sucess = caster.getMCPlayer().getGameMode().equals(GameMode.CREATIVE);
						if (!sucess) for (ItemStack s : i.getContents()) {
							if (s != null && s.getType().equals(block)) {
								s.setAmount(s.getAmount() - 1);
								sucess = true;
								break;
							}
						}

						if (sucess) {
							l.getBlock().breakNaturally();
							l.getBlock().setType(block);
							caster.getMCPlayer().playSound(caster.getMCPlayer().getEyeLocation(), Sound.ENTITY_ARMOR_STAND_PLACE, 1f, 1f);
						} else caster.getMCPlayer().playSound(caster.getMCPlayer().getEyeLocation(), Sound.ENTITY_ARMOR_STAND_BREAK, 1f, 1f);
					}
			takeMana(caster, val);
		}
	}

}
