package de.maanex.magic.spells.lightmagic;


import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import de.maanex.magic.MagicPlayer;
import de.maanex.magic.basic.Element;
import de.maanex.magic.spell.MagicSpell;
import de.maanex.magic.spell.SpellCategory;
import de.maanex.magic.spell.SpellRarity;
import de.maanex.magic.spell.SpellType;
import de.maanex.magic.wands.WandType;
import de.maanex.magic.wands.WandValues;
import de.maanex.main.Main;
import de.maanex.utils.ParticleUtil;


public class LightImpetus extends MagicSpell {

	public LightImpetus() {
		super(61, "Licht Impetus", "(Helles) WUUUSCH!", 3, 12, SpellType.ACTIVE, SpellCategory.UTILITY, SpellRarity.GODLIKE, WandType.LIGHT, "Dauer :light:");
	}

	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, WandValues val) {
		if (val.getElement(Element.ESSENCE_LIGHT) <= 0) return;

		for (int i = 0; i < val.getElement(Element.ESSENCE_LIGHT); i += 2)
			perform(caster.getMCPlayer(), i);

		caster.getMCPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, val.getElement(Element.ESSENCE_LIGHT) * 10 + 20 * 5, 1, true, false));
		caster.getMCPlayer().addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, val.getElement(Element.ESSENCE_LIGHT) * 10, 1, true, false));

		takeMana(caster, val);
	}

	private void perform(Player p, int delay) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
			p.setVelocity(vel(p));
			ParticleUtil.spawn(Particle.CLOUD, p.getLocation().clone().add(0, 1, 0), 20, .01, .5, 1, .5);
			for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 2, 3, 2)) {
				if (e.equals(p)) continue;
				if (!(e instanceof LivingEntity)) continue;
				((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 2));
				((LivingEntity) e).setVelocity(p.getVelocity().multiply(.2));
			}
		}, delay);
	}

	private Vector vel(Player p) {
		Vector o = p.getLocation().getDirection().multiply(.9);
		o.setY(o.getY() * .7);
		return o;
	}

}
