package com.xjs.routebuilder.utils;

import com.squareup.javapoet.TypeName;

import java.util.List;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
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

        if (TypeName.get(classElement.asType()).equals(TypeName.get(Object.class))) {
            return false;
        }
        if (TypeName.get(classElement.asType()).equals(impsInterface)) {
            return true;
        }

        List<? extends TypeMirror> list = classElement.getInterfaces();
        for (TypeMirror typeMirror : list) {
            if (TypeName.get(typeMirror).equals(impsInterface)) {
                return true;
            }
        }
        if (TypeName.get(classElement.getSuperclass()).equals(TypeName.get(Object.class))) {
            return false;
        } else {
            return recursionIsImplements((TypeElement) typeUtils.asElement(classElement.getSuperclass()), impsInterface, typeUtils);
        }
    }
}
