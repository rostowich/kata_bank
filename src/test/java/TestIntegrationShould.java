import com.lacombedulionvert.kata_bank.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TestIntegrationShould {

    private Account account;

    @Mock
    private DateProvider dateProvider;

    @BeforeEach
    public void initialize(){
        given(dateProvider.getCurrentDate())
                .willReturn(LocalDate.of(2022,4,20))
                .willReturn(LocalDate.of(2022,4,21))
                .willReturn(LocalDate.of(2022,4,22));
        account = new Account(
                new AccountOperationRepository(dateProvider),
                new OperationHistoryFormatter(),
                new BigDecimal(500));
    }

    @Test
    public void add_account_operations_and_print_statement() throws NotEnoughAmountException {

        account.makeDeposit(new BigDecimal(500.5));
        account.makeWithdrawal(new BigDecimal(200.5));
        account.makeWithdrawal(new BigDecimal(400));

        String expectedValue ="[[WITHDRAWAL, 22/04/2022, 400, -100.0], "+
                              "[WITHDRAWAL, 21/04/2022, 200.5, 300.0], "+
                              "[DEPOSIT, 20/04/2022, 500.5, 500.5]]";

        String result = account.seeOperationHistory();
        assertThat(result).isEqualTo(expectedValue);
    }
}
