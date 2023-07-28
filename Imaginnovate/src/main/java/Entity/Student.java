package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Getter@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, message = "First Name should be at least 3 characters")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    @Size(min = 3, message = "Last Name should be at least 3 characters")
    private String lastName;

    @NotNull(message = "Date of Birth is mandatory")
    @Past(message  ="Dob should be in past")
    @AgeLimit(minAge=15,maxAge=20,message = "Age should be in between 15 to 20 years")
    private Date dob;

    @NotBlank(message = "Section is mandatory")
    @Pattern(regexp ="[ABC]", message = "Sections should be like A, B or C")
    private String section;

    @NotBlank(message = "Gender is mandatory")
    @Pattern(regexp = "[MF]",message ="Gender should be either M or F")
    private String gender;

    @Min(value=0,message = "Marks should be greater than or equal to 0")
    @Max(value =100,message = "Marks should not exceed 100")
    private Integer marks1;

    @Min(value=0,message = "Marks should be greater than or equal to 0")
    @Max(value =100,message = "Marks should not exceed 100")
    private Integer marks2;

    @Min(value=0,message = "Marks should be greater than or equal to 0")
    @Max(value =100,message = "Marks should not exceed 100")
    private Integer Marks3;

    private Integer total;

    private Double average;

    private String results;


}
