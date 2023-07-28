package Entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class AgeValidator implements ConstraintValidator<AgeLimit, Date> {

    private int minAge;
    private int maxAge;

    @Override
    public void initialize(AgeLimit al){
        minAge = al.minAge();
        maxAge = al.maxAge();
    }

    public boolean isValid(Date dob, ConstraintValidatorContext context){
        if(dob == null){
            return false;
        }

        LocalDate birthDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate,currentDate).getYears();

        return age>=minAge && age <=maxAge;
    }


}
