package de.maanex.magic.spells.waterbender;


import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import de.maanex.magic.MagicPlayer;
import de.maanex.magic._legacy.LegacyWandModifiers;
import de.maanex.magic.missile.WaterBeamMissile;
import de.maanex.magic.spell.MagicSpell;
import de.maanex.magic.spell.SpellCategory;
import de.maanex.magic.spell.SpellRarity;
import de.maanex.magic.spell.SpellType;
import de.maanex.magic.wands.WandType;


public class WaterBenderBeam extends MagicSpell {

	public WaterBenderBeam() {
		super(73, "Wasserbendigung - Strahl", "Ja nimmt sich halt Wasser und schie�t es raus... joa", 3, 5, SpellType.ACTIVE, SpellCategory.BENDER, SpellRarity.RARE);
	}

	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, LegacyWandModifiers mods) {
		double mis = 40;
		Block target = caster.getMCPlayer().getTargetBlock(null, 60 + mods.getEnergy() - 100);

		while (mis-- > 0) {
			Location loc = caster.getMCPlayer().getLocation().clone().add(r(), r(), r());
			if (loc.getBlock().getType().equals(Material.WATER)) new WaterBeamMissile(loc, caster, mods.getEnergy(), target.getLocation()).launch();
			else mis += .3;
		}
		takeMana(caster, mods);

	}

	private int r() {
		return (new Random().nextInt(5) + 5) * (new Random().nextBoolean() ? 1 : -1);
	}
}
