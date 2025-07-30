package ra.com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_id")
    private int catalogId;
    //Mặc định của String: catalog_name varchar(255)
    @Column(name = "catalog_name", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String catalogName;
    @Column(name = "catalog_description", columnDefinition = "text")
    private String description;
    @Column(name = "catalog_status")
    private boolean status;
    @OneToMany(mappedBy = "catalog")
    private List<Product> listProducts = new ArrayList<Product>();
}
