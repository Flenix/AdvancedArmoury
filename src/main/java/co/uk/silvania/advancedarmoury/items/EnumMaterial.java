package co.uk.silvania.advancedarmoury.items;

public enum EnumMaterial {
	
	GOLD(1.1, 1935, 0.31F, "\u00A7e", 3),
	IRON(1.5, 785, 0.5F, "\u00A77", 2),
	BRONZE(2.65, 770, 0.49F, "\u00A76", 2),
	TITANIUM(3.1, 450, 0.43F, "\u00A78", 1),
	ALUMINIUM(1.4, 256, 0.51F, "\u00A7b", 2),
	STEEL(3.9, 800, 0.4F, "\u00A78", 2),
	POLYMER(1.9, 95, 0.68F, "\u00A78", 1),
	MYTHIAN(4.8, 1000, 0.38F, "\u00A72", 2),
	PROMETHIUM(6.9, 1450, 0.34F, "\u00A7d", 3),
	SARINUM(4.4, 1100, 0.39F, "\u00A74", 3),
	VELERIUM(3.7, 1050, 0.41F, "\u00A7a", 3),
	CELIBRAS(4.1, 995, 0.41F, "\u00A71", 2),
	AERICAS(3.95, 1125, 0.4F, "\u00A7f", 3),
	DARISIN(3.95, 1125, 0.4F, "\u00A78", 3);
	
	public final double durability;
	public final int weight;
	public final float accuracy;
	public final int fireRate;
	private String colour;
	
	EnumMaterial(double durability, int weight, float accuracy, String colour, int fireRate) {
		this.durability = durability;
		this.weight = weight;
		this.accuracy = accuracy;
		this.colour = colour;
		this.fireRate = fireRate;
	}
	
	public String getRawString() {
		String output = this.toString().toLowerCase();
		return output.substring(0, 1).toUpperCase() + output.substring(1);
	}

	public String getString() {
		String output = this.toString().toLowerCase();
		return colour + output.substring(0, 1).toUpperCase() + output.substring(1);
	}
}
