package iit.cs445.models.orders;

import iit.cs445.models.BaseEntity;
import iit.cs445.models.products.Product;
import iit.cs445.models.users.Address;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order")
public class Order extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "order_type")
    private OrderType orderType;

    @Column(name = "products")
    @OneToMany
    @JoinColumn(name = "id_products")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Product> products;

    @OneToOne
    @JoinColumn(name = "id")
    private Address billingAddress;

    @OneToOne
    @JoinColumn(name = "id")
    private Address shippingAddress;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}