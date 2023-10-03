package net.femtoparsec.units.api;

import lombok.Getter;

/**
 * @author Bastien Aracil
 */
@Getter
public class UnknownUnitException extends FpcUnitException {

    private final String name;
    private final Quantity quantity;

    public UnknownUnitException(String name, Quantity quantity) {
        super("Could not find a unit with name '"+name+"' for quantity '"+quantity+"'");
        this.name = name;
        this.quantity = quantity;
    }

}
