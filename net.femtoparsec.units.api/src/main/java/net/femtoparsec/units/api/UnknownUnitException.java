package net.femtoparsec.units.api;

import lombok.Getter;

/**
 * @author Bastien Aracil
 */
@Getter
public class UnknownUnitException extends RuntimeException {

    private final String name;
    private final Quantity<?,?,?> quantity;

    public UnknownUnitException(String name, Quantity<?,?,?> quantity) {
        this.name = name;
        this.quantity = quantity;
    }

}
