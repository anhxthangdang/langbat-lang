package com.langbat.lang.model.bson.user;


import com.langbat.lang.model.xnum.AddressMode;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Address {
    private Long id;
    private AddressMode mode;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    public Address() {
        this.id = System.currentTimeMillis();
    }
}
