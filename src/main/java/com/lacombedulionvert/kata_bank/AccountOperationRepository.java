package com.lacombedulionvert.kata_bank;

import java.util.ArrayList;
import java.util.List;

import static com.lacombedulionvert.kata_bank.OperationType.*;

public class AccountOperationRepository {

    private final DateProvider dateProvider;

    private final List<AccountOperation> operations;

    public AccountOperationRepository(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        operations = new ArrayList<>();
    }

    public void addDeposit(int amount) {
        AccountOperation deposit = new AccountOperation(
                dateProvider.getCurrentDate(),
                DEPOSIT,
                amount
        );
        operations.add(deposit);
    }

    public void addWithdrawal(int amount) {
        AccountOperation withdrawal = new AccountOperation(
                dateProvider.getCurrentDate(),
                WITHDRAWAL,
                amount
        );
        operations.add(withdrawal);
    }

    public List<AccountOperation> getHistory() {
        return operations;
    }
}
