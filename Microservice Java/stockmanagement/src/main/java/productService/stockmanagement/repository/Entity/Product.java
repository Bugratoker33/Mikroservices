package productService.stockmanagement.repository.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "product" ,schema = "stock_management")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(name = "product_name")
    private  String ProductName;

    @Column(name = "quantitiy")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Builder.Default
    @Column(name = "product_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date prductUpdateDate=new Date();

    @Builder.Default
    @Column(name = "prduct_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productCreatedDate=new Date();

    @Column(name = "is_deleted")
    private boolean deleted;

}
