package com.lacombedulionvert.kata_bank;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class HistoryFormatter {

    private static final DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/YYYY");
    public String format(List<AccountOperation> operations) {
        AtomicInteger currentBalance = new AtomicInteger(0);
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
                                             AtomicInteger currentBalance){
        return "["+accountOperation.getOperationType()
                +", "
                +accountOperation.getDate().format(formatter)
                + ", "
                +accountOperation.getAmount()
                + ", "
                +currentBalance.addAndGet(accountOperation.getSignedAmount())
                +"]";
    }
}
