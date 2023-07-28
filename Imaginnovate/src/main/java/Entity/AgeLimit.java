package Entity;




import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface AgeLimit {

    int minAge();
    int maxAge();

    String message() default "Invalid Age";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
