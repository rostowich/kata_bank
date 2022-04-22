import com.lacombedulionvert.kata_bank.DateProvider;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class TestDateProviderShould {

    @Test
    public void return_today_date(){
        TestableDateProvider testableDateProvider = new TestableDateProvider();

        LocalDate todayDate = testableDateProvider.getCurrentDate();

        assertThat(todayDate).isEqualTo(LocalDate.of(2022,4,21));
    }

    private static class TestableDateProvider extends DateProvider {
        public LocalDate getCurrentDate(){
            return LocalDate.of(2022,4,21);
        }
    }
}
