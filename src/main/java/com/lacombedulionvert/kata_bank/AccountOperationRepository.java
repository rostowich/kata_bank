package com.lacombedulionvert.kata_bank;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

import static com.lacombedulionvert.kata_bank.OperationType.*;

public class AccountOperationRepository {

    private final DateProvider dateProvider;

    private List<AccountOperation> operations = new ArrayList<>();

    public AccountOperationRepository(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    public void addDeposit(int amount) {
        AccountOperation deposit = new AccountOperation(
                dateProvider.getCurrentDate(),
                DEPOSIT,
                amount
        );
        operations.add(deposit);
    }

    public void addWithdrawal(int amount) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Not yet implemented");
    }

    public List<AccountOperation> getHistory() {
       return operations;
    }
}
