package de.maanex.magic.spells;


import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import de.maanex.magic.MagicPlayer;
import de.maanex.magic.MagicSpell;
import de.maanex.magic.WandModifiers;
import de.maanex.magic.enumeri.SpellCategory;
import de.maanex.magic.enumeri.SpellRarity;
import de.maanex.magic.enumeri.SpellType;
import de.maanex.magic.enumeri.WandType;
import de.maanex.utils.ParticleUtil;


public class Timewarp extends MagicSpell {

	public Timewarp() {
		super(37, "Zeitsprung", "Teleportiert dich zur�ck!", 4, 20, SpellType.ACTIVE, SpellCategory.UTILITY, SpellRarity.RARE);
	}

	private HashMap<Player, Location> pos = new HashMap<>();

	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, WandModifiers mods) {
		if (!pos.containsKey(caster.getMCPlayer())) {
			pos.put(caster.getMCPlayer(), caster.getMCPlayer().getLocation());
			ParticleUtil.spawn(Particle.ENCHANTMENT_TABLE, caster.getMCPlayer().getLocation(), 100, 1, .5, 1, .5);
		} else {
			Location loc = pos.get(caster.getMCPlayer());
			pos.remove(caster.getMCPlayer());
			if (!loc.getWorld().equals(caster.getMCPlayer().getWorld()) || loc.distance(caster.getMCPlayer().getLocation()) > mods.getEnergy()) {
				caster.getMCPlayer().sendMessage("�8Distanz zu gro�!");
				return;
			}

			ParticleUtil.spawn(Particle.ENCHANTMENT_TABLE, caster.getMCPlayer().getLocation(), 100, 1, .5, 1, .5);
			caster.getMCPlayer().teleport(loc);
			takeMana(caster, mods);
		}
	}

}
