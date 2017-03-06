package assignment13;

import java.util.HashSet;

public enum Characters {
	FOX,
	GOOSE,
	BEANS,
	FARMER;
	
	private HashSet<Characters> enemies;
	
	void addEnemy(Characters enemy) {
		enemies.add(enemy);
	}
	
	Characters() {
		enemies = new HashSet<Characters>();
	}
	
	public HashSet<Characters> getEnemies() {
		return enemies;
	}
	
	public String niceName() {
		return name().substring(0, 1) + name().substring(1).toLowerCase();
	}
	
	public static Characters getByName(String name) {
		Characters character = null;
		for (Characters chara : Characters.values()) {
			if (chara.name().equalsIgnoreCase(name)) {
				character = chara;
				break;
			}
		}
		return character;
	}
}
