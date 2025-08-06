package ra.com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.naming.Name;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "product_id", columnDefinition = "char(5)")
    private String productId;
    @Column(name = "product_name", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String productName;
    @Column(name = "product_price", columnDefinition = "float check(product_price>0)", nullable = false)
    private float price;
    @Column(name = "product_image", columnDefinition = "varchar(200)", nullable = false)
    private String image;
    @Column(name = "product_created")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;
    //Khóa ngoại: product thuộc về 1 category
    @ManyToOne
    @JoinColumn(name = "catalog_id", referencedColumnName = "catalog_id")
    private Category catalog;
    @Column(name = "product_status", columnDefinition = "bit default(1)")
    private boolean status;
}
