package com.lacombedulionvert.kata_bank;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public class AccountOperationRepository {
    public void addDeposit(int amount) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Not yet implemented");
    }

    public void addWithdrawal(int amount) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Not yet implemented");
    }

    public List<Operation> getHistory() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Not yet implemented");
    }
}
