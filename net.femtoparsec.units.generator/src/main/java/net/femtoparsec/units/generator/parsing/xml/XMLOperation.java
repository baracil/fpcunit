package net.femtoparsec.units.generator.parsing.xml;

import lombok.RequiredArgsConstructor;

/**
 * @author Bastien Aracil
 */
public interface XMLOperation {

    <T> T accept(XMLOperationVisitor<T> visitor);


    @RequiredArgsConstructor
    public static enum Types {
        PRODUCT("product",XMLProduct.class),
        NS_ADD("nsadd",XMLNSAdd.class),
        INVERSE("inverse",XMLInverse.class),
        CUBIC("cubic",XMLCubic.class),
        SQUARE("square",XMLSquare.class),
        ;
        private final String xmlName;
        private final Class<? extends XMLOperation> type;

        public static Class<? extends XMLOperation> findType(String xmlName) {
            for (Types value : Types.values()) {
                if (value.xmlName.equals(xmlName)) {
                    return value.type;
                }
            }
            throw new IllegalArgumentException("Unknown operation name '"+xmlName+"'");
        }

    }
}
