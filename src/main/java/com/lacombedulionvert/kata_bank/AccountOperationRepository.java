package com.lacombedulionvert.kata_bank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public void addWithdrawal(int amount) {
        AccountOperation withdrawal = new AccountOperation(
                dateProvider.getCurrentDate(),
                WITHDRAWAL,
                amount
        );
        operations.add(withdrawal);
    }

    public List<AccountOperation> getHistory() {
        return operations.stream()
                .sorted(Comparator.comparing(AccountOperation::getDate))
                .collect(Collectors.toList());
    }
}
