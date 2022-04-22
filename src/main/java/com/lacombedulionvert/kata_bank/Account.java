package com.lacombedulionvert.kata_bank;

import java.util.stream.Collectors;

public class Account {

    private final AccountOperationRepository accountOperationRepository;
    private final StatementPrinter statementPrinter;
    private final int OVERDRAFT_AMOUNT = 500;

    public Account(AccountOperationRepository accountOperationRepository, StatementPrinter statementPrinter) {
        this.accountOperationRepository = accountOperationRepository;
        this.statementPrinter = statementPrinter;
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
        return statementPrinter.print(accountOperationRepository.getHistory());
    }

    private int balance(){
        int balance = 0;
        balance = accountOperationRepository.getHistory().stream()
                    .mapToInt(accountOperation -> (
                            OperationType.DEPOSIT.equals(accountOperation.getOperationType())
                                    ? accountOperation.getAmount()
                                    : -accountOperation.getAmount()))
                    .sum();
        return balance;
    }
}
