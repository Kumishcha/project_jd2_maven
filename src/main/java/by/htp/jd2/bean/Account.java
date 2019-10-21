package by.htp.jd2.bean;

public class Account {
	
	private int accountId;
    private int balance;
    private boolean blocked;
    
	public Account() {
	}

	public Account(int accountId, int balance, boolean blocked) {
		
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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
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
		result = prime * result + balance;
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
		if (balance != other.balance)
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
