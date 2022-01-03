public class AllowanceTracker {

	public static void main(String[] args) {
		AllowanceTracker tr = new AllowanceTracker(50.0);
		System.out.println(tr.numExcessiveDays());
		tr.spend(10.5);
		System.out.println(tr.getBalance());
		tr.earn(2.0);
		System.out.println(tr.getBalance());
		tr.replenish();
		System.out.println(tr.getBalance());
		System.out.println(tr.numExcessiveDays());
	}

	private double balance;
	private double allowance;
	private int xDays;

	public AllowanceTracker() {
		balance = 0;
		allowance = 0;
		xDays = 0;
	}

	public AllowanceTracker(double allowance) {
		this();
		balance = allowance;
		this.allowance = allowance;
		xDays = 0;
	}

	public void spend(double amount) {
		if (amount > 0.2 * allowance) {
			xDays++;
		}
		balance -= amount;
	}

	public void earn(double amount) {
		balance += amount;
	}

	public double getBalance() {
		return balance;
	}

	public int numExcessiveDays() {
		return xDays;
	}

	public void replenish() {
		balance += allowance;
	}
}
