import com.lacombedulionvert.kata_bank.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
                500);
    }

    @Test
    public void add_account_operations_and_print_statement() throws NotEnoughAmountException {

        account.makeDeposit(500);
        account.makeWithdrawal(200);
        account.makeWithdrawal(400);

        String expectedValue ="[[WITHDRAWAL, 22/04/2022, 400, -100], "+
                              "[WITHDRAWAL, 21/04/2022, 200, 300], "+
                              "[DEPOSIT, 20/04/2022, 500, 500]]";

        String result = account.seeOperationHistory();
        assertThat(result).isEqualTo(expectedValue);
    }
}
