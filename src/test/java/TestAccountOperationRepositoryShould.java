import com.lacombedulionvert.kata_bank.AccountOperation;
import com.lacombedulionvert.kata_bank.AccountOperationRepository;
import com.lacombedulionvert.kata_bank.Datasource;
import com.lacombedulionvert.kata_bank.DateProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.lacombedulionvert.kata_bank.OperationType.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TestAccountOperationRepositoryShould {

    @Mock
    private DateProvider dateProvider;

    private AccountOperationRepository accountOperationRepository;

    @BeforeEach
    public void initialize(){
        accountOperationRepository = new AccountOperationRepository(dateProvider, new Datasource());
        given(dateProvider.getCurrentDate())
                .willReturn(LocalDate.of(2022,4,21))
                .willReturn(LocalDate.of(2022,4,22));

    }
    @Test
    public void create_and_add_a_deposit_operation() {
        List<AccountOperation> historyBeforeDeposit = accountOperationRepository.getHistory();
        assertThat(historyBeforeDeposit.size()).isZero();

        accountOperationRepository.addDeposit(new BigDecimal("300.50"));
        List<AccountOperation> historyAfterDeposit = accountOperationRepository.getHistory();

        assertThat(historyAfterDeposit.size()).isOne() ;
        assertThat(historyAfterDeposit.get(0)).isEqualTo(new AccountOperation(
                LocalDate.of(2022,4,21),
                DEPOSIT,
                new BigDecimal("300.50")
        ));
    }

    @Test
    public void create_and_add_a_withdrawal_operation() {
        List<AccountOperation> historyBeforeWithdrawal = accountOperationRepository.getHistory();
        assertThat(historyBeforeWithdrawal.size()).isZero() ;

        accountOperationRepository.addWithdrawal(new BigDecimal("250.50"));
        List<AccountOperation> historyAfterWithdrawal = accountOperationRepository.getHistory();

        assertThat(historyAfterWithdrawal.size()).isOne();
        assertThat(historyAfterWithdrawal.get(0)).isEqualTo(new AccountOperation(
                LocalDate.of(2022,4,21),
                WITHDRAWAL,
                new BigDecimal("250.50")
        ));
    }

    @Test
    public void return_history_of_operation(){
        List<AccountOperation> initialHistory = accountOperationRepository.getHistory();
        assertThat(initialHistory.size()).isZero() ;

        accountOperationRepository.addDeposit(new BigDecimal(300));
        accountOperationRepository.addWithdrawal(new BigDecimal(250));

        List<AccountOperation> expected = accountOperationRepository.getHistory();

        assertThat(expected.size()).isEqualTo(2);
        assertThat(expected.get(0)).isEqualTo(new AccountOperation(
                LocalDate.of(2022,4,21),
                DEPOSIT,
                new BigDecimal(300)
        ));
        assertThat(expected.get(1)).isEqualTo(new AccountOperation(
                LocalDate.of(2022,4,22),
                WITHDRAWAL,
                new BigDecimal(250)
        ));
    }
}
