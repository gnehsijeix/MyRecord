package com.xjs.arouterbuilder.feature;

import com.alibaba.android.arouter.facade.annotation.Route;

import javax.lang.model.element.TypeElement;

/**
 * @author xjs
 *         on 2018/1/18
 *         desc:
 */

public interface IRouteAnnotationFilter {
    /**
     * 筛选
     *
     * @param typeElement class element
     * @return true 跳过这个元素
     */
    boolean filter(TypeElement typeElement);
}
