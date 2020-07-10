package tacos.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private long id;
    private Date placedAt;
    private List<Taco> tacos = new ArrayList<>();

    @NotNull
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Zip is required")
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

}
