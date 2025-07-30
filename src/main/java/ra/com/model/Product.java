package ra.com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.Name;
import javax.persistence.*;

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
    //Khóa ngoại: product thuộc về 1 category
    @ManyToOne
    @JoinColumn(name = "catalog_id", referencedColumnName = "catalog_id")
    private Category catalog;
    @Column(name = "product_status", columnDefinition = "bit default(1)")
    private boolean status;
}
