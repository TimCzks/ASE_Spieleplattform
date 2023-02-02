package domain.code;

public class User {

	private String username;
	private Stats stats;

	public User(String username, Stats stats) {
		super();
		this.username = username;
		this.stats = stats;
	}

	@Override
	public String toString() {
		return "User " + username + ", " + stats.toString() + ".";
	}

	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}
}
