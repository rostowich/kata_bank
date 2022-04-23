package com.lacombedulionvert.kata_bank;

import java.math.BigDecimal;

public class Account {

    private final AccountOperationRepository accountOperationRepository;
    private final OperationHistoryFormatter historyFormatter;
    private final BigDecimal overdraftAmount;

    public Account(AccountOperationRepository accountOperationRepository,
                   OperationHistoryFormatter historyFormatter,
                   BigDecimal overdraftAmount) {
        this.accountOperationRepository = accountOperationRepository;
        this.historyFormatter = historyFormatter;
        this.overdraftAmount = overdraftAmount;
    }

    public void makeDeposit(BigDecimal amount) {
        accountOperationRepository.addDeposit(amount);
    }

    public void makeWithdrawal(BigDecimal amount) throws NotEnoughAmountException {
        BigDecimal authorizedAmountToWithdraw = this.balance().add(this.overdraftAmount);
        if(amount.compareTo(authorizedAmountToWithdraw) > 0)
            throw new NotEnoughAmountException("Not enough amount in the account");
        accountOperationRepository.addWithdrawal(amount);
    }

    public String seeOperationHistory() {
        return historyFormatter.format(accountOperationRepository.getHistory());
    }

    private BigDecimal balance(){
        return accountOperationRepository.getHistory().stream()
                    .map(AccountOperation::getSignedAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
