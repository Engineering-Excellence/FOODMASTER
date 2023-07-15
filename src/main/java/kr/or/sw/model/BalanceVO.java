package kr.or.sw.model;

import java.sql.Date;

public class BalanceVO {
	
// balance는 getter는 있되 setter는 없어야됨	
	
	private int accountID; // 계좌ID
	private String currency; // 통화
	private int amount; // 보유금
	private java.sql.Date lastUpdated; // 변동일시
	
	public int getAccountID() {
		return accountID;
	}
	
	public String getCurrency() {
		return currency;
	}

	public int getAmount() {
		return amount;
	}

	public java.sql.Date getLastUpdated() {
		return lastUpdated;
	}

	public BalanceVO(int accountID, String currency, int amount, Date lastUpdated) {
		super();
		this.accountID = accountID;
		this.currency = currency;
		this.amount = amount;
		this.lastUpdated = lastUpdated;
	}

}	
