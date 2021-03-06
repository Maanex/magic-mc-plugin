package de.maanex.magic.spells;


import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import de.maanex.magic.MagicPlayer;
import de.maanex.magic.basic.Element;
import de.maanex.magic.spell.MagicSpell;
import de.maanex.magic.spell.SpellCategory;
import de.maanex.magic.spell.SpellRarity;
import de.maanex.magic.spell.SpellType;
import de.maanex.magic.wands.WandType;
import de.maanex.magic.wands.WandValues;
import de.maanex.utils.ParticleUtil;


public class Timewarp extends MagicSpell {

	public Timewarp() {
		super(37, "Zeitsprung", "Teleportiert dich zur�ck!", 4, 20, SpellType.ACTIVE, SpellCategory.UTILITY, SpellRarity.RARE, "Reichweite :air:");
	}

	private HashMap<Player, Location> pos = new HashMap<>();

	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, WandValues val) {
		if (!pos.containsKey(caster.getMCPlayer())) {
			pos.put(caster.getMCPlayer(), caster.getMCPlayer().getLocation());
			ParticleUtil.spawn(Particle.ENCHANTMENT_TABLE, caster.getMCPlayer().getLocation(), 100, 1, .5, 1, .5);
		} else {
			Location loc = pos.get(caster.getMCPlayer());
			pos.remove(caster.getMCPlayer());
			if (!loc.getWorld().equals(caster.getMCPlayer().getWorld()) || loc.distance(caster.getMCPlayer().getLocation()) > val.getElement(Element.AIR) * 20) {
				caster.getMCPlayer().sendMessage("�8Distanz zu gro�!");
				return;
			}

			ParticleUtil.spawn(Particle.ENCHANTMENT_TABLE, caster.getMCPlayer().getLocation(), 100, 1, .5, 1, .5);
			caster.getMCPlayer().setFallDistance(0);
			caster.getMCPlayer().teleport(loc);
			takeMana(caster, val);
		}
	}

}
