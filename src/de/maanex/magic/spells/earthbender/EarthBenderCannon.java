package de.maanex.magic.spells.earthbender;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.material.MaterialData;
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


public class EarthBenderCannon extends MagicSpell implements Listener {

	public EarthBenderCannon() {
		super(17, "Erdbendigung - Schleuder", "Hepp... UUUuunnd SCHUSS!", 3, 2, SpellType.ACTIVE, SpellCategory.BENDER, SpellRarity.RARE);
	}

	static HashMap<MagicPlayer, FallingBlock>	blocks	= new HashMap<>();
	static List<FallingBlock>					shooten	= new ArrayList<>();

	@SuppressWarnings({ "deprecation" })
	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, WandValues val) {
		if (val.getElement(Element.EARTH) <= 0) return;
		if (val.getElement(Element.ESSENCE_BENDER) <= 0) return;

		if (blocks.containsKey(caster)) {
			for (Entity e : caster.getMCPlayer().getWorld().getNearbyEntities(caster.getMCPlayer().getEyeLocation(), 2, 1, 2)) {
				if (blocks.containsValue(e)) {
					FallingBlock b = (FallingBlock) e;
					boolean found = false;
					if (blocks.get(caster).equals(b)) {
						blocks.remove(caster);
						takeMana(caster, val);

						b.setVelocity(b.getLocation().clone().subtract(caster.getMCPlayer().getLocation()).toVector().normalize().multiply(4).setY(.1));
						shooten.add(b);
						found = true;
					}

					if (!found) {
						caster.setMana(0);
					}
				}
			}

			takeMana(caster, val);
		} else {
			Block b = caster.getMCPlayer().getTargetBlock(null, 5);
			if (b == null || !b.getType().isSolid() || !b.getType().isOccluding() || !b.getLocation().clone().add(0, 1, 0).getBlock().isEmpty()) return;

			Bukkit.getOnlinePlayers().forEach(p -> {
				p.sendBlockChange(b.getLocation(), Material.AIR, (byte) 0);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
					p.sendBlockChange(b.getLocation(), b.getType(), b.getData());
				}, 100);
			});
			FallingBlock f = b.getWorld().spawnFallingBlock(b.getLocation().clone().add(.5, 1, .5), new MaterialData(b.getType(), b.getData()));
			blocks.put(caster, f);
			f.setDropItem(false);
			f.setVelocity(new Vector(0, .6f, 0));
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onFallingBlockLand(EntityChangeBlockEvent e) {
		if (e.getEntity() instanceof FallingBlock) {
			FallingBlock b = (FallingBlock) e.getEntity();
			if (blocks.values().contains(b)) {
				e.setCancelled(true);
				ParticleUtil.spawn(Particle.BLOCK_CRACK, b.getLocation(), 5, 1, 0, 0, 0, b.getBlockData());
				for (MagicPlayer p : blocks.keySet())
					if (blocks.get(p).equals(b)) {
						blocks.remove(p);
						p.setMana(0);
					}
			} else if (shooten.contains(b)) {
				e.setCancelled(true);
				TNTPrimed tnt = (TNTPrimed) b.getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.PRIMED_TNT);
				tnt.setFuseTicks(0);
			}
		}
	}

}
