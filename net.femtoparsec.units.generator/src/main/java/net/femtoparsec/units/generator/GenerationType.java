package net.femtoparsec.units.generator;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public enum GenerationType {

    CORE("core-project-basedir"),
    PROPERTY("property-project-basedir"),
    HIBERNATE("hibernate-project-basedir"),
    SPRING_CLASS("spring-class-project-basedir"),
    SPRING_CONFIGURATION("spring-configuration-project-basedir"),
;

    @NonNull
    @Getter
    private final String baseDirProperty;
}
