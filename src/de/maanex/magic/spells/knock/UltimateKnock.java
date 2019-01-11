package de.maanex.magic.spells.knock;


import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;

import de.maanex.magic.MagicPlayer;
import de.maanex.magic.spell.MagicSpell;
import de.maanex.magic.spell.SpellCategory;
import de.maanex.magic.spell.SpellRarity;
import de.maanex.magic.spell.SpellType;
import de.maanex.magic.wands.WandType;
import de.maanex.magic.wands.WandValues;
import de.maanex.utils.ParticleUtil;


public class UltimateKnock extends MagicSpell {

	public UltimateKnock() {
		super(46, "Ultimativer Knock", "KNOCK KNOCK KNOCK KNOCK KNOCK KNOCK KNOCK KNOCK KNOCK KNOCK KNOCK KNOCK KNOCK KNOCK KNOCK KNOCK!", 1, 6, SpellType.ACTIVE, SpellCategory.COMBAT,
				SpellRarity.LEGENDARY);
	}

	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, WandValues val) {
		for (int i = 0; i < 16; i++) {
			Location l = caster.getMCPlayer().getEyeLocation().clone().add(caster.getMCPlayer().getLocation().getDirection().multiply(i));
			ParticleUtil.spawn(Particle.CRIT, l, 100, .05, .15, .15, .15);

			if (i % 3 == 0) l.getWorld().getNearbyEntities(l, 1.6, 1.6, 1.6).forEach(e -> {
				if (e instanceof LivingEntity && !e.isDead() && !e.equals(caster.getMCPlayer())) ((LivingEntity) e).damage(6, caster.getMCPlayer());
			});
		}
		takeMana(caster, val);
	}

}
