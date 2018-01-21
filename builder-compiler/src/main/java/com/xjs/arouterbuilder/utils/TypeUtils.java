package com.xjs.arouterbuilder.utils;

import com.squareup.javapoet.TypeName;

import java.lang.reflect.Type;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.NoType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * @author xjs
 *         on  2018/1/15
 *         desc:
 */

public class TypeUtils {
    /**
     * 是否继承、实现 类接口
     *
     * @param classElement  原类
     * @param impsInterface 继承、实现接口、类
     * @param typeUtils     ；
     * @return ；
     */
    public static boolean recursionIsImplements(TypeElement classElement, TypeName impsInterface, Types typeUtils) {
        //包括但不限于 object
        if (classElement.asType().getKind() == TypeKind.NONE) {
            return false;
        }

        if (TypeName.get(classElement.asType()).equals(impsInterface)) {
            return true;
        }

        List<? extends TypeMirror> list = classElement.getInterfaces();
        for (TypeMirror interfaceTypeMirror : list) {
            if (TypeName.get(interfaceTypeMirror).equals(impsInterface)) {
                return true;
            } else {
                TypeElement interfaceElement = (TypeElement) typeUtils.asElement(interfaceTypeMirror);
                if (recursionIsImplements(interfaceElement, impsInterface, typeUtils)) {
                    return true;
                }
            }
        }

        if (classElement.getSuperclass().getKind() == TypeKind.NONE) {
            return false;
        } else {
            return recursionIsImplements((TypeElement) typeUtils.asElement(classElement.getSuperclass()), impsInterface, typeUtils);
        }
    }
}
