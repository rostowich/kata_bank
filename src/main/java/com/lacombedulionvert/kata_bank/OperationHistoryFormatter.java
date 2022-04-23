package com.lacombedulionvert.kata_bank;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class OperationHistoryFormatter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String format(List<AccountOperation> operations) {
        AtomicReference<BigDecimal> currentBalance = new AtomicReference<>(BigDecimal.ZERO);
        List<String> operationFormattedInChronologicalOrder =
                operations.stream()
                .sorted(Comparator.comparing(AccountOperation::getDate))
                .map(accountOperation ->
                        accountOperationFormatter(accountOperation, currentBalance))
                .collect(Collectors.toList());

        Collections.reverse(operationFormattedInChronologicalOrder);

        return operationFormattedInChronologicalOrder.toString();
    }

    private String accountOperationFormatter(AccountOperation accountOperation,
                                             AtomicReference<BigDecimal> currentBalance){
        return "["+accountOperation.getOperationType()
                +", "
                +accountOperation.getDate().format(formatter)
                + ", "
                +accountOperation.getAmount()
                + ", "
                +currentBalance.accumulateAndGet(
                        accountOperation.getSignedAmount(),
                        (balance, amount) -> balance.add(amount))
                +"]";
    }
}
