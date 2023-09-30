package net.femtoparsec.units.generator.bean;

import jakarta.annotation.Nullable;
import net.femtoparsec.units.generator.StringUtils;
import lombok.NonNull;

/**
 * @author Bastien Aracil
 */
public interface Operation {

    default boolean isMonadic() {
        return getOther() == null;
    }

    boolean isNonStandard();

    @NonNull
    Quantity getReference();

    @Nullable
    Quantity getOther();

    @NonNull
    Quantity getResult();

    String getMethodName();

    default String getDecapitalizedMethodName() {
        return StringUtils.decapitalizeFirstLetter(getMethodName());
    }

    <T> T accept(OperationVisitor<T> visitor);

}
