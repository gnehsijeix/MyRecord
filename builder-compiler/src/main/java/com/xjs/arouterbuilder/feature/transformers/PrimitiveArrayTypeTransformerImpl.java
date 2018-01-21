package com.xjs.arouterbuilder.feature.transformers;

import com.xjs.arouterbuilder.feature.TypeTransformer;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.Types;


/**
 * @author xjs
 *         on  2018/1/21
 *         desc:
 */

public class PrimitiveArrayTypeTransformerImpl extends BaseTypeTransformer implements TypeTransformer {

    public PrimitiveArrayTypeTransformerImpl(Types typesUtils) {
        super(typesUtils);
    }

    @Override
    public boolean accept(Element element) {
        return element.asType().getKind() == TypeKind.ARRAY && isPrimitiveArray(element);
    }

    @Override
    public CharSequence transform(Element element) {
        switch (typesUtils.getArrayType(element.asType()).getKind()) {
            case BOOLEAN:
            case BYTE:
            case SHORT:
            case INT:
            case LONG:
            case CHAR:
            case FLOAT:
            case DOUBLE:
                return "DoubleArray";
            default:
                return "Object";
        }
    }

    private boolean isPrimitiveArray(Element element) {
        switch (typesUtils.getArrayType(element.asType()).getKind()) {
            case BOOLEAN:
            case BYTE:
            case SHORT:
            case INT:
            case LONG:
            case CHAR:
            case FLOAT:
            case DOUBLE:
                return true;
            default:
                return false;
        }
    }
}
