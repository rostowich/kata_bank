package com.lacombedulionvert.kata_bank;

import javax.naming.OperationNotSupportedException;

public class Account {

    private final AccountOperationRepository accountOperationRepository;
    private final StatementPrinter statementPrinter;

    public Account(AccountOperationRepository accountOperationRepository, StatementPrinter statementPrinter) {
        this.accountOperationRepository = accountOperationRepository;
        this.statementPrinter = statementPrinter;
    }

    public void makeDeposit(int amount) {
        accountOperationRepository.addDeposit(amount);
    }

    public void makeWithdrawal(int amount) {
        accountOperationRepository.addWithdrawal(amount);
    }

    public void printStatement() {
        statementPrinter.print(accountOperationRepository.getHistory());
    }
}
