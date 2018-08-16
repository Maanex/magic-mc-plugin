package de.maanex.magic.spells.basic;


import org.bukkit.Material;
import org.bukkit.Particle;

import de.maanex.magic.MagicPlayer;
import de.maanex.magic._legacy.LegacyWandModifiers;
import de.maanex.magic.spell.MagicSpell;
import de.maanex.magic.spell.SpellCategory;
import de.maanex.magic.spell.SpellRarity;
import de.maanex.magic.spell.SpellType;
import de.maanex.magic.wands.WandType;
import de.maanex.utils.ParticleUtil;


public class EarthSpirit extends MagicSpell {

	public EarthSpirit() {
		super(25, "Erdgeist", "Spiritus terrae", 0, 0, SpellType.NOT_USEABLE, SpellCategory.UTILITY, SpellRarity.COMMON);
	}

	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, LegacyWandModifiers mods) {
		ParticleUtil.spawn(caster.getMCPlayer(), Particle.BLOCK_CRACK, caster.getMCPlayer().getEyeLocation(), 300, .2, 2, .3, 2, Material.DIRT.createBlockData());
	}

}
