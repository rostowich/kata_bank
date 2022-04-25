package com.lacombedulionvert.kata_bank;

import java.math.BigDecimal;
import java.util.function.Function;

import static com.lacombedulionvert.kata_bank.ValidationResult.*;

public interface AmountValidator extends Function<BigDecimal, ValidationResult> {

    static AmountValidator isEmptyAmount(){
        return amount ->
                (amount != null ? SUCCESS : AMOUNT_REQUIRED);
    }

    static AmountValidator isValidAmount(){
        return amount ->
                (amount.compareTo(BigDecimal.ZERO) > 0 ? SUCCESS : AMOUNT_IS_NOT_VALID);
    }

    default AmountValidator and(AmountValidator other){
        return amount -> {
            ValidationResult result = this.apply(amount);
            return  result.equals(SUCCESS) ? other.apply(amount) : result;
        };
    }


}
