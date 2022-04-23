package com.lacombedulionvert.kata_bank;

import java.math.BigDecimal;
import java.util.List;

import static com.lacombedulionvert.kata_bank.OperationType.*;

public class AccountOperationRepository {

    private final DateProvider dateProvider;

    private final Datasource datasource;

    public AccountOperationRepository(DateProvider dateProvider, Datasource datasource) {
        this.dateProvider = dateProvider;
        this.datasource = datasource;
    }

    public void addDeposit(BigDecimal amount) {
        AccountOperation deposit = new AccountOperation(
                dateProvider.getCurrentDate(),
                DEPOSIT,
                amount
        );
        datasource.getDataSource().add(deposit);
    }

    public void addWithdrawal(BigDecimal amount) {
        AccountOperation withdrawal = new AccountOperation(
                dateProvider.getCurrentDate(),
                WITHDRAWAL,
                amount
        );
        datasource.getDataSource().add(withdrawal);
    }

    public List<AccountOperation> getHistory() {
        return datasource.getDataSource();
    }
}
