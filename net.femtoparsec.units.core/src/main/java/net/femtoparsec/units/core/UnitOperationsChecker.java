package net.femtoparsec.units.core;

import lombok.experimental.UtilityClass;
import net.femtoparsec.units.api.Dimension;
import net.femtoparsec.units.api.Dimensioned;
import net.femtoparsec.units.api.Unit;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author bastien.a
 */
@UtilityClass
public class UnitOperationsChecker {

    private static final boolean ENV_CHECK_ENABLED = Boolean.getBoolean("units-debug");

    public static boolean USER_CHECK_ENABLED = false;


    public static <U extends Unit<?,?,?>> U checkMultiplyUnit(Unit<?,?,?> unit1, Unit<?,?,?> unit2, U unit) {
        checkBiAddic(unit1, unit2, UnitOperationsChecker::getMultiplyDimension, unit, "*");
        return unit;
    }

    public static <U extends Unit<?,?,?>> U checkDividedUnit(Unit<?,?,?> unit1, Unit<?,?,?> unit2, U unit) {
        checkBiAddic(unit1, unit2, UnitOperationsChecker::getDividedDimension, unit, "/");
        return unit;
    }

    public static <U extends Unit<?,?,?>> U checkInvertedUnit(Unit<?,?,?> unit1, U unit) {
        checkMonadic(unit1, unit, UnitOperationsChecker::getInvertedDimension, Dimensioned::getDimension, "^-1");
        return unit;
    }

    public static <U extends Unit<?,?,?>> U checkSquareUnit(Unit<?,?,?> unit, U unitResult) {
        checkMonadic(unit, unitResult, UnitOperationsChecker::getSquareDimension, Dimensioned::getDimension, "^2");
        return unitResult;
    }

    public static <U extends Unit<?,?,?>> U checkSquareRootUnit(Unit<?,?,?> unit, U unitResult) {
        checkMonadic(unit, unitResult, Dimensioned::getDimension, UnitOperationsChecker::getSquareDimension, "^0.5");
        return unitResult;
    }

    public static <U extends Unit<?,?,?>> U checkCubicUnit(Unit<?,?,?> unit, U unitResult) {
        checkMonadic(unit, unitResult, UnitOperationsChecker::getCubicDimension, Dimensioned::getDimension, "^3");
        return unitResult;
    }


    private static void checkBiAddic(Unit<?,?,?> unit1, Unit<?,?,?> unit2, BiFunction<Unit<?,?,?>, Unit<?,?,?>, Dimension> dimensionGetter, Unit result, String operationName) {
        if (ENV_CHECK_ENABLED || USER_CHECK_ENABLED) {
            final Dimension dimension1 = dimensionGetter.apply(unit1, unit2);
            final Dimension dimension2 = result.getDimension();

            System.out.println(dimension1 + " " + dimension2);
            if (!dimension1.equals(dimension2)) {
                throw new IllegalArgumentException("Incompatible units : (" + unit1 + " " +operationName+" "+unit2+") <> " + result);
            }
        }

    }

    private static void checkMonadic(Unit<?,?,?> unit1, Unit<?,?,?> unit2, Function<Unit<?,?,?>, Dimension> dimensionGetter1, Function<Unit, Dimension> dimensionGetter2, String operationName) {
        if (ENV_CHECK_ENABLED || USER_CHECK_ENABLED) {
            final Dimension dimension1 = dimensionGetter1.apply(unit1);
            final Dimension dimension2 = dimensionGetter2.apply(unit2);

            if (!dimension1.equals(dimension2)) {
                throw new IllegalArgumentException("Incompatible units : (" + unit1 + ")"+operationName+" <> " + unit2);
            }
        }
    }

    public static Dimension getSquareDimension(Unit<?,?,?> unit) {
        return unit.getDimension().scale(2);
    }

    public static Dimension getCubicDimension(Unit<?,?,?> unit) {
        return unit.getDimension().scale(3);
    }

    public static Dimension getMultiplyDimension(Unit<?,?,?>...multiplicators) {
        return Dimension.sum(multiplicators);
    }

    public static Dimension getDividedDimension(Unit<?,?,?> unit1, Unit<?,?,?> unit2) {
        return unit1.getDimension().subtract(unit2.getDimension());
    }

    public static Dimension getInvertedDimension(Unit<?,?,?> unit1) {
        return unit1.getDimension().negate();
    }


}
