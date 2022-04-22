import com.lacombedulionvert.kata_bank.Account;
import com.lacombedulionvert.kata_bank.AccountOperationRepository;
import com.lacombedulionvert.kata_bank.AccountOperation;
import com.lacombedulionvert.kata_bank.StatementPrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.naming.OperationNotSupportedException;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {

    @Mock
    AccountOperationRepository accountOperationRepository;

    @Mock
    StatementPrinter statementPrinter;

    private Account account;

    @Before
    public void initialize(){
        account = new Account(accountOperationRepository, statementPrinter);
    }

    @Test
    public void add_a_deposit_operation() throws OperationNotSupportedException {
        account.makeDeposit(200);
        verify(accountOperationRepository).addDeposit(200);
    }

    @Test
    public void add_a_withdrawal_operation() throws OperationNotSupportedException {
        account.makeWithdrawal(200);
        verify(accountOperationRepository).addWithdrawal(200);
    }

    @Test
    public void print_a_statement() throws OperationNotSupportedException {
        List<AccountOperation> operations = Arrays.asList(new AccountOperation());
        given(accountOperationRepository.getHistory()).willReturn(operations);
        account.seeOperationHistory();
        verify(accountOperationRepository).getHistory();
        verify(statementPrinter).print(operations);
    }
}
