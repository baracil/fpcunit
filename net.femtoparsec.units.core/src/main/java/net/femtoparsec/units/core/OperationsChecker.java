package net.femtoparsec.units.core;

import net.femtoparsec.units.api.Quantity;

/**
 * @author bastien.a
 */
public interface OperationsChecker {

    void checkMultiply(Quantity q1, Quantity q2, Quantity target);
    void checkDivided(Quantity q1, Quantity q2, Quantity target);
    void checkInverted(Quantity quantity, Quantity target);
    void checkSquare(Quantity quantity, Quantity target);
    void checkSquareRoot(Quantity quantity, Quantity target);
    void checkCubic(Quantity quantity, Quantity target);


}
