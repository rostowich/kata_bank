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

    @Before
    public void initialize(){
        accountOperationRepository = new AccountOperationRepository(dateProvider);
        given(dateProvider.getCurrentDate())
                .willReturn(LocalDate.of(2022,04,21))
                .willReturn(LocalDate.of(2022,04,22));

    }
    @Test
    public void create_and_add_a_deposit_operation() {
        List<AccountOperation> historyBeforeDeposit = accountOperationRepository.getHistory();
        assertThat(historyBeforeDeposit.size(), is(0)) ;

        accountOperationRepository.addDeposit(300);
        List<AccountOperation> historyAfterDeposit = accountOperationRepository.getHistory();

        assertThat(historyAfterDeposit.size(), is(1)) ;
        assertThat(historyAfterDeposit.get(0), is(new AccountOperation(
                LocalDate.of(2022,04,21),
                DEPOSIT,
                300
        )));
    }

    @Test
    public void create_and_add_a_withdrawal_operation() {
        List<AccountOperation> historyBeforeWithdrawal = accountOperationRepository.getHistory();
        assertThat(historyBeforeWithdrawal.size(), is(0)) ;

        accountOperationRepository.addWithdrawal(250);
        List<AccountOperation> historyAfterWithdrawal = accountOperationRepository.getHistory();

        assertThat(historyAfterWithdrawal.size(), is(1));
        assertThat(historyAfterWithdrawal.get(0), is(new AccountOperation(
                LocalDate.of(2022,04,21),
                WITHDRAWAL,
                250
        )));
    }

    @Test
    public void return_history_of_operation(){
        List<AccountOperation> initialHistory = accountOperationRepository.getHistory();
        assertThat(initialHistory.size(), is(0)) ;

        accountOperationRepository.addDeposit(300);
        accountOperationRepository.addWithdrawal(250);

        List<AccountOperation> expected = accountOperationRepository.getHistory();

        assertThat(expected.size(), is(2));
        assertThat(expected.get(0), is(new AccountOperation(
                LocalDate.of(2022,04,21),
                DEPOSIT,
                300
        )));
        assertThat(expected.get(1), is(new AccountOperation(
                LocalDate.of(2022,04,22),
                WITHDRAWAL,
                250
        )));
    }
}
