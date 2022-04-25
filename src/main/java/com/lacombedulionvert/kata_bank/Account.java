package com.lacombedulionvert.kata_bank;

import java.math.BigDecimal;

import static com.lacombedulionvert.kata_bank.AmountValidator.isEmptyAmount;
import static com.lacombedulionvert.kata_bank.AmountValidator.isValidAmount;
import static com.lacombedulionvert.kata_bank.ValidationResult.SUCCESS;

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
        validate(amount);
        accountOperationRepository.addDeposit(amount);
    }

    public void makeWithdrawal(BigDecimal amount) throws NotEnoughAmountException {
        validate(amount);
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

    private void validate(BigDecimal amount){
        //Implementation of the Combinator pattern to validate operation amount
        ValidationResult isAmountValid = isEmptyAmount()
                .and(isValidAmount())
                .apply(amount);
        if(!isAmountValid.equals(SUCCESS)) {
            throw new IllegalArgumentException(isAmountValid.getMessage());
        }
    }
}
