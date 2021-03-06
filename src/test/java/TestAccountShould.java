import com.lacombedulionvert.kata_bank.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.lacombedulionvert.kata_bank.OperationType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestAccountShould {

    @Mock
    AccountOperationRepository accountOperationRepository;

    @Mock
    OperationHistoryFormatter statementPrinter;

    private Account account;

    private List<AccountOperation> operations;

    @BeforeEach
    public void initialize(){
        account = new Account(accountOperationRepository, statementPrinter, new BigDecimal(500));
        operations = new ArrayList<>();
        AccountOperation deposit = new AccountOperation(
                LocalDate.of(2022, 4, 21),
                DEPOSIT,
                new BigDecimal(500)
        );
        AccountOperation withdrawal = new AccountOperation(
                LocalDate.of(2022, 4, 22),
                WITHDRAWAL,
                new BigDecimal(400)
        );
        operations.add(deposit);
        operations.add(withdrawal);
    }

    @Test
    public void add_a_deposit_operation() {
        account.makeDeposit(new BigDecimal(200));
        verify(accountOperationRepository).addDeposit(new BigDecimal(200));
    }

    @Test
    public void add_a_withdrawal_operation() throws NotEnoughAmountException {
        given(accountOperationRepository.getHistory()).willReturn(new ArrayList<>());
        account.makeWithdrawal(new BigDecimal(200));
        verify(accountOperationRepository).addWithdrawal(new BigDecimal(200));
    }

    @Test
    public void add_a_withdrawal_operation_having_not_enough_amount_into_the_account() {
        given(accountOperationRepository.getHistory()).willReturn(operations);

        Exception exception = assertThrows(NotEnoughAmountException.class,
                () ->  account.makeWithdrawal(new BigDecimal(700)));

        Class<NotEnoughAmountException> exceptedException = NotEnoughAmountException.class;
        String expectedMessage = "Not enough amount in the account";
        String actualMessage = exception.getMessage();

        assertInstanceOf(exceptedException, exception);
        assertEquals(actualMessage, expectedMessage);
    }
    @Test
    public void add_a_withdrawal_with_negative_amount(){
        Exception exception = assertThrows(IllegalArgumentException.class,
                () ->  account.makeWithdrawal(new BigDecimal(-700)));
        Class<IllegalArgumentException> exceptedException = IllegalArgumentException.class;
        String expectedMessage = "The amount is invalid";
        String actualMessage = exception.getMessage();

        assertInstanceOf(exceptedException, exception);
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void print_a_statement() {
        List<AccountOperation> operations = List.of(new AccountOperation(
                LocalDate.now(),
                DEPOSIT,
                new BigDecimal(700)
        ));
        given(accountOperationRepository.getHistory()).willReturn(operations);

        account.seeOperationHistory();

        verify(accountOperationRepository).getHistory();
        verify(statementPrinter).format(operations);
    }
}
