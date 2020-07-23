package tacos.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable{
    public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String deliveryName;
    private Date placedAt;
    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    @NotNull
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 55)
    private String name;

    @NotBlank(message = "Street is required")
    @Size(min = 3, max = 25)
    private String street;

    @NotBlank(message = "City is required")
    @Size(min = 3, max = 25)
    private String city;

    @NotBlank(message = "State is required")
    @Size(min = 3, max = 25)
    private String state;

    @NotBlank(message = "Zip is required")
    @Size(min=5)
    @Pattern(regexp = "[0-9]{5}", message = "Not a valid zip number")
    private String zip;

    @Pattern(regexp = "[0-9]{16}", message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @Pattern(regexp = "[0-9]{3}", message = "Invalid CVV")
    private String ccCVV;

    public void addDesign(Taco taco){
        this.tacos.add(taco);
    }

    public List<Taco> getTacos(){
        return tacos;
    }

    @PrePersist
    void placedAt(){
        this.placedAt = new Date();
    }
}
