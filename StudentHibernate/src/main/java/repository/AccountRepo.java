package repository;

import dao.AccountDao;
import pojo.Account;

public class AccountRepo implements IAccountRepo {
	private AccountDao accountDao = null;
	
	
	
	public AccountRepo(String filename) {
		super();
		this.accountDao = new AccountDao(filename);
	}

	@Override
	public Account getAccount(String username) {
		return accountDao.checkLogin(username);
	}

	

	
}
