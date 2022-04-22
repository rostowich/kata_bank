package com.lacombedulionvert.kata_bank;

public class Account {

    private final AccountOperationRepository accountOperationRepository;
    private final OperationHistoryFormatter historyFormatter;
    private final int overdraftAmount;

    public Account(AccountOperationRepository accountOperationRepository,
                   OperationHistoryFormatter historyFormatter,
                   int overdraftAmount) {
        this.accountOperationRepository = accountOperationRepository;
        this.historyFormatter = historyFormatter;
        this.overdraftAmount = overdraftAmount;
    }

    public void makeDeposit(int amount) {
        accountOperationRepository.addDeposit(amount);
    }

    public void makeWithdrawal(int amount) throws NotEnoughAmountException {
        if(amount > this.balance() + this.overdraftAmount)
            throw new NotEnoughAmountException("Not enough amount in the account");
        accountOperationRepository.addWithdrawal(amount);
    }

    public String seeOperationHistory() {
        return historyFormatter.format(accountOperationRepository.getHistory());
    }

    private int balance(){
        return accountOperationRepository.getHistory().stream()
                    .mapToInt(AccountOperation::getSignedAmount)
                    .sum();
    }
}
