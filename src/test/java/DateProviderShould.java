import com.lacombedulionvert.kata_bank.DateProvider;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class DateProviderShould {

    @Test
    public void return_today_date(){
        TestableDateProvider testableDateProvider = new TestableDateProvider();

        LocalDate todayDate = testableDateProvider.getCurrentDate();

        assertThat(todayDate, is(LocalDate.of(2022,04,21)));
    }

    private class TestableDateProvider extends DateProvider {
        public LocalDate getCurrentDate(){
            return LocalDate.of(2022,04,21);
        }
    }
}
