package com.xjs.arouterbuilder.feature.transformers;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import com.xjs.arouterbuilder.Constants;
import com.xjs.arouterbuilder.feature.TypeTransformer;
import com.xjs.arouterbuilder.utils.TypeUtils;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;


/**
 * @author xjs
 *         on  2018/1/21
 *         desc:
 *         转换如下类型
 *         .withByteArray()
 *         .withCharArray()
 *         .withFloatArray()
 *         .withParcelableArray()
 *         .withShortArray()
 *         .withCharSequenceArray()
 */

public class ArrayTypeTransformerImpl extends BaseTypeTransformer implements TypeTransformer {

    public ArrayTypeTransformerImpl(Types typesUtils) {
        super(typesUtils);
    }

    @Override
    public boolean accept(Element element) {
        return element.asType().getKind() == TypeKind.ARRAY;
    }

    @Override
    public CharSequence transform(Element element) {
        ArrayType arrayType = (ArrayType) element.asType();
        TypeMirror arrayTypeMirror = arrayType.getComponentType();
        switch (arrayTypeMirror.getKind()) {
            case BYTE:
                return "ByteArray";
            case CHAR:
                return "CharArray";
            case FLOAT:
                return "FloatArray";
            case SHORT:
                return "ShortArray";
            default:
                break;
        }
        TypeElement typeElement = (TypeElement) typesUtils.asElement(arrayTypeMirror);

        //ParcelableArray
        if (TypeUtils.recursionIsImplements(typeElement,
                ClassName.bestGuess(Constants.ANDROID_OS_PARCELABLE_CLASS_NAME), typesUtils)) {
            return "ParcelableArray";
        }
        //withCharSequenceArray
        if (TypeUtils.recursionIsImplements(typeElement, TypeName.get(CharSequence.class), typesUtils)) {
            return "CharSequenceArray";
        }
        return "Object";
    }

}
