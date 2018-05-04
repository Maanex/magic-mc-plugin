package de.maanex.magic.spells;


import de.maanex.magic.MagicPlayer;
import de.maanex.magic.MagicSpell;
import de.maanex.magic.WandModifiers;
import de.maanex.magic.wandsuse.WandType;


public class Fireball extends MagicSpell {

	public Fireball() {
		super(10, "Feuerball", "Schie�t einen Feuerball", 8);
	}

	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, WandModifiers mods) {
		caster.getMCPlayer().launchProjectile(org.bukkit.entity.Fireball.class);
		takeMana(caster, mods);
	}
}
