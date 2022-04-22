package com.lacombedulionvert.kata_bank;

public class Account {

    private final AccountOperationRepository accountOperationRepository;
    private final HistoryFormatter historyFormatter;
    private final int OVERDRAFT_AMOUNT = 500;

    public Account(AccountOperationRepository accountOperationRepository, HistoryFormatter historyFormatter) {
        this.accountOperationRepository = accountOperationRepository;
        this.historyFormatter = historyFormatter;
    }

    public void makeDeposit(int amount) {
        accountOperationRepository.addDeposit(amount);
    }

    public void makeWithdrawal(int amount) throws NotEnoughAmountException {
        if(amount > this.balance() + OVERDRAFT_AMOUNT)
            throw new NotEnoughAmountException("Not enough amount in the account");
        accountOperationRepository.addWithdrawal(amount);
    }

    public String seeOperationHistory() {
        return historyFormatter.format(accountOperationRepository.getHistory());
    }

    private int balance(){
        return accountOperationRepository.getHistory().stream()
                    .mapToInt(accountOperation -> accountOperation.getSignedAmount())
                    .sum();
    }
}
