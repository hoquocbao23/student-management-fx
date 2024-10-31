package service;

import pojo.Account;
import repository.AccountRepo;
import repository.IAccountRepo;

public class AccountService implements IAccountService {
	String filename = "hibernate.cfg.xml";
	
	private IAccountRepo accountRepo = null;
	
	public AccountService() {
		super();
		this.accountRepo = new AccountRepo(filename);
	}

	@Override
	public Account getAccount(String username) {
		return accountRepo.getAccount(username);
	}









	
}
