package net.femtoparsec.units.named.test;

import net.femtoparsec.units.core.UnitOperationsChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

/**
 * @author Bastien Aracil
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class UnitTest {

    @BeforeEach
    public void setup() {
        UnitOperationsChecker.USER_CHECK_ENABLED = true;
    }
}
