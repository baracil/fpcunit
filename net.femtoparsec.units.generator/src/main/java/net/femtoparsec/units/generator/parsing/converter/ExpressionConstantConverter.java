package net.femtoparsec.units.generator.parsing.converter;

import lombok.RequiredArgsConstructor;
import net.femtoparsec.units.generator.bean.Constant;
import net.femtoparsec.units.generator.bean.ExpressionConstant;
import net.femtoparsec.units.generator.parsing.xml.XMLExpressionConstant;

/**
 * @author Bastien Aracil
 */
@RequiredArgsConstructor
public class ExpressionConstantConverter {

    public Constant convert(XMLExpressionConstant constant) {
        return ExpressionConstant.builder()
            .name(constant.getName())
            .expression(constant.getExpression())
            .build();
    }


}
