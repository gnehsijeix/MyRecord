package com.xjs.arouterbuilder.feature;

import javax.lang.model.element.Element;

/**
 * @author xjs
 *         on  2018/1/14
 *         desc:
 */

public interface TypeTransformer {

    boolean accept(Element element);

    CharSequence transform(Element element);
}
