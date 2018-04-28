package de.maanex.magic.spells;


import de.maanex.magic.MagicPlayer;
import de.maanex.magic.MagicSpell;
import de.maanex.magic.WandModifiers;
import de.maanex.magic.missile.StunMissile;
import de.maanex.magic.wandsuse.WandType;


public class Stun extends MagicSpell {

	public Stun() {
		super(20, "Stun", "H�lt fest!", 3, new BuildRequirements(0, 5, 15, 30, 0, 0, 5, 20, 0, 5, 90));
	}

	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, WandModifiers mods) {
		StunMissile mis = new StunMissile(caster.getMCPlayer().getEyeLocation(), caster, caster.getMCPlayer().getLocation().clone(), mods.getEnergy());
		mis.launch();
		takeMana(caster, mods);
	}

}
