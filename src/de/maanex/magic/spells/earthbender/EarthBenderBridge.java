package de.maanex.magic.spells.earthbender;


import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import de.maanex.magic.MagicPlayer;
import de.maanex.magic.missile.BridgeDrawMissile;
import de.maanex.magic.spell.MagicSpell;
import de.maanex.magic.spell.SpellCategory;
import de.maanex.magic.spell.SpellRarity;
import de.maanex.magic.spell.SpellType;
import de.maanex.magic.wands.WandType;
import de.maanex.magic.wands.WandValues;
import de.maanex.magic.wands.WandValues.WandModifier;


public class EarthBenderBridge extends MagicSpell {

	public EarthBenderBridge() {
		super(16, "Erdbendigung - Br�cke", "Schmiede eine m�chtige Br�cke!", 18, 0, SpellType.ACTIVE, SpellCategory.BENDER, SpellRarity.VERY_RARE);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, WandValues val) {
		Block b = caster.getMCPlayer().getTargetBlock(null, 40 + val.getMod(WandModifier.ENERGY) - 100);
		if (b.getType().equals(Material.AIR)) return;

		Random r = new Random();
		for (int x = -4; x < 4; x++)
			for (int y = -4; y < 4; y++)
				for (int z = -4; z < 4; z++) {
					if (r.nextInt(2) != 0) continue;

					Location l = b.getLocation().clone().add(x, y, z).add(b.getLocation().clone().add(caster.getMCPlayer().getEyeLocation()).toVector().normalize().multiply(r.nextInt(3) + 1));

					if (!l.getBlock().getType().isSolid()) continue;

					BridgeDrawMissile m = new BridgeDrawMissile(l, caster, caster.getMCPlayer().getLocation().clone().add(x - 1 + r.nextInt(3), -1 - r.nextInt(3), z - 1 + r.nextInt(3)),
							l.getBlock().getType(), l.getBlock().getData());
					m.launch();
				}

		takeMana(caster, val);
	}

}
