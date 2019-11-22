package by.htp.jd2.bean;

public class Account {
	
	private int accountId;
    private double balance;
    private boolean blocked;
    
	public Account() {
	}

	public Account(int accountId, double balance, boolean blocked) {
		
		this.accountId = accountId;
		this.balance = balance;
		this.blocked = blocked;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (blocked ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (blocked != other.blocked)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [accountId=" + accountId + ", balance=" + balance + ", blocked=" + blocked + "]";
	}
}
