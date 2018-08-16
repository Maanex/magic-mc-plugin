package de.maanex.magic.spell;


public enum SpellRarity {

	COMMON("Gew�hnlich"), RARE("Selten"), VERY_RARE("Sehr Selten"), EPIC("Episch"), LEGENDARY("Legend�r"), MYTHIC("Mythisch"), //

	FORBIDDEN("Verboten"), GODLIKE("G�ttlich")

	;

	private String displayname;

	SpellRarity(String displayname) {
		this.displayname = displayname;
	}

	public String getDisplayName() {
		return displayname;
	}

}
