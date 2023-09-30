package net.femtoparsec.units.named.test;

import lombok.NonNull;
import org.junit.jupiter.api.Assertions;

import java.io.*;

/**
 * @author Bastien Aracil
 */
public class AbstractTestSerialization {

    protected <T extends Serializable> T testSerialization(@NonNull T serializable) throws IOException, ClassNotFoundException {
        final byte[] data = serialize(serializable);
        final Object mirror = deserialize(data);

        Assertions.assertEquals(serializable, mirror);
        return (T)mirror;
    }

    private byte[] serialize(@NonNull Object serializable) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream o = new ObjectOutputStream(baos)) {
            o.writeObject(serializable);
        }
        return baos.toByteArray();
    }

    private Object deserialize(@NonNull byte[] bytes) throws IOException, ClassNotFoundException {
        try (ObjectInputStream s = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return s.readObject();
        }
    }

}
