package de.maanex.magic.spells.basic;


import org.bukkit.Particle;

import de.maanex.magic.MagicPlayer;
import de.maanex.magic.spell.MagicSpell;
import de.maanex.magic.spell.SpellCategory;
import de.maanex.magic.spell.SpellRarity;
import de.maanex.magic.spell.SpellType;
import de.maanex.magic.wands.WandType;
import de.maanex.magic.wands.WandValues;
import de.maanex.utils.ParticleUtil;


public class AirSpirit extends MagicSpell {

	public AirSpirit() {
		super(27, "Luftgeist", "Spiritus caeli", 0, 0, SpellType.NOT_USEABLE, SpellCategory.UTILITY, SpellRarity.BASIC);
	}

	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, WandValues val) {
		ParticleUtil.spawn(caster.getMCPlayer(), Particle.CLOUD, caster.getMCPlayer().getEyeLocation(), 300, .2, 2, .3, 2);
	}

}
