package de.maanex.magic.spells;


import java.util.HashMap;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import de.maanex.magic.MagicPlayer;
import de.maanex.magic.spell.MagicSpell;
import de.maanex.magic.spell.SpellCategory;
import de.maanex.magic.spell.SpellRarity;
import de.maanex.magic.spell.SpellType;
import de.maanex.magic.wands.WandType;
import de.maanex.magic.wands.WandValues;
import de.maanex.magic.wands.WandValues.WandModifier;
import de.maanex.utils.ParticleUtil;
import de.maanex.utils.TargetEntityFinder;


public class Enderarm extends MagicSpell {

	public Enderarm() {
		super(38, "Enderarm", "Muhahahaha!", 1, 0, SpellType.ACTIVE, SpellCategory.UTILITY, SpellRarity.EPIC);
	}

	private static HashMap<MagicPlayer, LivingEntity>	drag	= new HashMap<>();
	private static HashMap<MagicPlayer, Integer>		dist	= new HashMap<>();
	private static HashMap<MagicPlayer, Integer>		slot	= new HashMap<>();
	private static HashMap<MagicPlayer, Integer>		tick	= new HashMap<>();

	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, WandValues val) {
		if (drag.containsKey(caster)) {
			drag.remove(caster);
			dist.remove(caster);
			slot.remove(caster);
			tick.remove(caster);
		} else {
			Block b = caster.getMCPlayer().getTargetBlock(null, val.getMod(WandModifier.ENERGY) / 2);
			if (b == null || b.isEmpty()) return;

			Entity tar = TargetEntityFinder.find(b);
			if (tar == null || tar.equals(caster.getMCPlayer()) || !(tar instanceof LivingEntity)) return;

			drag.put(caster, (LivingEntity) tar);
			dist.put(caster, (int) tar.getLocation().distance(caster.getMCPlayer().getLocation()));
			slot.put(caster, caster.getMCPlayer().getInventory().getHeldItemSlot());
			tick.put(caster, 0);
		}
	}

	public static void tick() {
		Random r = new Random();
		for (MagicPlayer c : drag.keySet()) {
			tick.put(c, tick.get(c) + 1);
			if (tick.get(c) >= 10) {
				tick.put(c, 0);
				c.addMana(-1);
				if (c.getMana() <= 0) {
					drag.remove(c);
					dist.remove(c);
					slot.remove(c);
					tick.remove(c);
					return;
				}
			}
			if (slot.get(c) != c.getMCPlayer().getInventory().getHeldItemSlot()) {
				drag.remove(c);
				dist.remove(c);
				slot.remove(c);
				tick.remove(c);
				return;
			}

			Location t = c.getMCPlayer().getTargetBlock(null, dist.get(c)).getLocation();
			Vector v = t.toVector().subtract(drag.get(c).getLocation().toVector());
			drag.get(c).setVelocity(Vector.getMinimum(v, v.normalize()));
			drag.get(c).setFallDistance(0);
			drawRay(r, c.getMCPlayer().getEyeLocation().clone(), drag.get(c).getLocation());
		}
	}

	private static void drawRay(Random r, Location src, Location tar) {
		double ldis = 1000;
		src.setDirection(tar.toVector().subtract(src.toVector()));
		while (ldis > (ldis = src.distance(tar))) {
			src.add(src.getDirection().multiply(.5));
			ParticleUtil.spawn(Particle.DRAGON_BREATH, src, 1, 0, .05, .05, .05);
		}
	}
}
