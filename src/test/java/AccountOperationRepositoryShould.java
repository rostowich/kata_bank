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
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountOperationRepositoryShould {

    @Mock
    private DateProvider dateProvider;

    private AccountOperationRepository accountOperationRepository;

    @Before
    public void initialize(){
        accountOperationRepository = new AccountOperationRepository(dateProvider);
    }
    @Test
    public void create_and_add_a_deposit_operation() throws OperationNotSupportedException {
        List<AccountOperation> operations = accountOperationRepository.getHistory();
        assertThat(operations.size(), is(0)) ;
        
        given(dateProvider.getCurrentDate()).willReturn(LocalDate.of(2022,04,21));

        accountOperationRepository.addDeposit(300);

        assertThat(operations.size(), is(1)) ;
        assertThat(operations.get(0), is(new AccountOperation(
                LocalDate.of(2022,04,21),
                DEPOSIT,
                300
        )));

    }
}
