package com.xjs.myrecords;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.google.gson.Gson;

import java.lang.reflect.Type;

import javax.inject.Inject;

/**
 * @author xjs
 *         on 2018/1/18
 *         desc:序列化的实现
 */
public class SerializationServiceGsonImpl implements SerializationService {

    Gson gson;

    interface SerializationServiceGsonImplComponent {
        /**
         * inject
         *
         * @param impl impl
         */
        void inejct(SerializationServiceGsonImpl impl);
    }

    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        return null;
    }

    @Override
    public String object2Json(Object instance) {
        return gson.toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return gson.fromJson(input,clazz);
    }

    @Override
    public void init(Context context) {
    }
}
