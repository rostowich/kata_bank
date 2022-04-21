import com.lacombedulionvert.kata_bank.AccountOperation;
import com.lacombedulionvert.kata_bank.AccountOperationRepository;
import com.lacombedulionvert.kata_bank.DateProvider;
import com.lacombedulionvert.kata_bank.OperationType;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.naming.OperationNotSupportedException;

import java.time.LocalDate;
import java.util.List;

import static com.lacombedulionvert.kata_bank.OperationType.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class AccountOperationRepositoryShould {

    @Mock
    private DateProvider dateProvider;

    private AccountOperationRepository accountOperationRepository;
    List<AccountOperation> operations;

    @Before
    public void initialize(){
        accountOperationRepository = new AccountOperationRepository(dateProvider);
        operations = accountOperationRepository.getHistory();
        given(dateProvider.getCurrentDate()).willReturn(LocalDate.of(2022,04,21));

    }
    @Test
    public void create_and_add_a_deposit_operation() {
        assertThat(operations.size(), is(0)) ;
        accountOperationRepository.addDeposit(300);

        assertThat(operations.size(), is(1)) ;
        assertThat(operations.get(0), is(new AccountOperation(
                LocalDate.of(2022,04,21),
                DEPOSIT,
                300
        )));
    }

    @Test
    public void create_and_add_a_withdrawal_operation() throws OperationNotSupportedException {
        assertThat(operations.size(), is(0)) ;
        accountOperationRepository.addWithdrawal(250);

        assertThat(operations.size(), is(1)) ;
        assertThat(operations.get(0), is(new AccountOperation(
                LocalDate.of(2022,04,21),
                WITHDRAWAL,
                250
        )));

    }
}
