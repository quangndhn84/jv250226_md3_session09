package ra.com.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import ra.com.model.Category;


import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequestCreateDTO {
    //NotBlank
    @NotBlank(message = "Product Id is required")
    private String productId;
    @NotBlank(message = "Product Name is required")
    private String productName;
    @Min(value = 0, message = "More than 0")
    private float price;
    private MultipartFile file;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;
    private Category catalog;
}
