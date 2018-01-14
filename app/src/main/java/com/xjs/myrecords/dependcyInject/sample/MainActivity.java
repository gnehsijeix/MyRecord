package com.xjs.myrecords.dependcyInject.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.xjs.myrecords.R;
import com.xjs.myrecords.dependcyInject.sample.annotations.ProviderPersonFromName;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "HomeActivity";

    @ProviderPersonFromName
    @Inject
    Lazy<Person> person;
    @ProviderPersonFromName
    @Inject
    Provider<Person> person2;

    @Inject
    SingletonObject object1;

    @Inject
    SingletonObject object2;

    @Inject
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*MainComponent component = DaggerMainComponent.builder().mainModel(new MainModule()).generate();
        component.inject(this);*/
        //DaggerMainComponent.builder().mainModel(new MainModule(this)).generate().inject(this);
        DaggerActivityComponent.builder().appComponent(MyApplication.appComponent).build().inject(this);
        //test();
        testGsonDecode();
    }

    private void test() {
        Log.i(TAG, "test: " + object1.equals(object2) + "object1:" + object1 + "object2:" + object2);
        int i = 0;
        Log.i(TAG, "test: person1");
        while(i<5) {
            i++;
            person.get().printMessage();
        }
        Log.i(TAG, "test: person2");
        i = 0;
        while(i<5) {
            i++;
            person2.get().printMessage();
        }
    }
    private void testGsonDecode() {
        person.get().printMessage();
        Log.i(TAG, "testGsonDecode: person decode ");
        Person personBean = gson.fromJson(gson.toJson(person.get()),Person.class);
        personBean.printMessage();
    }


}
