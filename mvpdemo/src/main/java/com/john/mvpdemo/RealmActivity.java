package com.john.mvpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import realm.Dog;

public class RealmActivity extends AppCompatActivity implements View.OnClickListener {

    Realm realm;
    @ViewInject(R.id.editText)
    EditText nameEt;
    @ViewInject(R.id.editText2)
    EditText ageEt;
    @ViewInject(R.id.editText3)
    EditText colorEt;
    @ViewInject(R.id.listview)
    ListView mListView;
    List<Map<String,String>>  data;
    SimpleAdapter mAdapter;
    public static int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        x.view().inject(this);
        realm = Realm.getDefaultInstance();
        data = new ArrayList<>();
    }

    /**
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button3:
                insertData();
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                List<Dog> mDogList = queryAllDatas();
                if(data!=null && data.size()>0){
                   data.clear();
                }

                Map<String,String> map = null;
                    for (int i = 0; i <mDogList.size() ; i++) {
                    map = new HashMap<String,String>();
                    map.put("dog",mDogList.get(i).getId()+"|"+mDogList.get(i).getName()+
                            "|"+mDogList.get(i).getAge()+mDogList.get(i).getColor());
                    data.add(map);
                }
                mAdapter = new SimpleAdapter(RealmActivity.this, data, android.R.layout.simple_list_item_1,
                        new String[]{"dog"}, new int[]{android.R.id.text1});
                mListView.setAdapter(mAdapter);

                break;
        }
    }

    /**
     * 创建一个Dog对象，复制到数据库中
     */
    private void insertData() {
        realm.beginTransaction();
        Dog dog = new Dog();
        dog.setId(id++);
        dog.setAge(Integer.valueOf(ageEt.getText().toString()));
        dog.setName(nameEt.getText().toString());
        dog.setColor(colorEt.getText().toString());
        realm.copyToRealm(dog);
        realm.commitTransaction();


    }

    /**
     * 使用realm创建一个对象，给对象赋值并储存
     */
    private void insertDataWithCreate() {
        realm.beginTransaction();
        Dog dog = realm.createObject(Dog.class);
        dog.setId(id++);
        dog.setAge(Integer.valueOf(ageEt.getText().toString()));
        dog.setName(nameEt.getText().toString());
        dog.setColor(colorEt.getText().toString());
        realm.commitTransaction();

    }

    /**
     * 使用事务块插入数据
     */
    private void insertDataWithTrans() {
        final Dog dog = new Dog();
        dog.setId(id++);
        dog.setAge(Integer.valueOf(ageEt.getText().toString()));
        dog.setName(nameEt.getText().toString());
        dog.setColor(colorEt.getText().toString());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(dog);
            }
        });

    }

    /**
     * 删除ID为index的数据
     */
    private void deleteDataById(int id) {
        realm.beginTransaction();
        Dog dog = realm.where(Dog.class).equalTo("id", id).findFirst();
        RealmResults<Dog> dogs = realm.where(Dog.class).findAll();
        dog.deleteFromRealm();
        realm.commitTransaction();

    }

    /**
     * @param index 是结果集中的索引
     *              删除指定索引的数据
     */
    private void deleteDataByIndex(final int index) {
        realm.beginTransaction();
        final RealmResults<Dog> dogs = realm.where(Dog.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dog dog = dogs.get(index);
                dog.deleteFromRealm();
                dogs.deleteFirstFromRealm();
                dogs.deleteLastFromRealm();
                dogs.deleteFromRealm(5);
                dogs.deleteAllFromRealm();
            }
        });
    }

    /**
     * @param index id
     * @param name  将ID为index的数据中name值修改为name
     */
    private void updateData(int index, String name) {
        Dog dog = realm.where(Dog.class).equalTo("id", index).findFirst();
        realm.beginTransaction();
        dog.setName(name);
        realm.commitTransaction();
    }

    /**
     * 查询全部数据，可以使用mRealm.copyFromRealm(dogs)方法将它转为List<T>
     *
     * @return
     */
    private List<Dog> queryAllDatas() {
        RealmResults<Dog> dogs = realm.where(Dog.class).findAll();
        return realm.copyFromRealm(dogs);
    }

    /**
     * 按条件查询
     *
     * @param index
     * @return between(), greaterThan(), lessThan(), greaterThanOrEqualTo() & lessThanOrEqualTo()
     * <p>
     * equalTo() & notEqualTo()
     * <p>
     * contains(), beginsWith() & endsWith()
     * <p>
     * isNull() & isNotNull()
     * <p>
     * isEmpty() & isNotEmpty()
     */

    private Dog queryDataByCondition(int index) {
        Dog dog = realm.where(Dog.class).equalTo("id", id).equalTo("id", id).findFirst();
        return dog;
    }

    /**
     * @param flag 标示符 1表示增序 2表示降序
     * @return 返回排列好得集合
     */
    private List<Dog> queryDataWithSort(int flag) {
        RealmResults<Dog> dogs = realm.where(Dog.class).findAll();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
        //增序排序
        if (flag == 1) {
            dogs.sort("age");
        } else {
            //降序排列
            dogs.sort("age", Sort.DESCENDING);
        }
        return realm.copyFromRealm(dogs);

    }

    /**
     * 查询年龄最好最大的一条数据
     *
     * @return
     */
    private Dog queryDataAgeMax() {
        Number max = realm.where(Dog.class).max("age");
        Dog dog = realm.where(Dog.class).equalTo("age", max.intValue()).findFirst();
        return dog;
    }

    /**
     * @return 查询年龄总和
     */
    private int queryAgaSum() {
        return realm.where(Dog.class).findAll().sum("age").intValue();

    }

    /**
     * @return 查询平均年龄
     */
    private double queryAgeAverage() {
        return realm.where(Dog.class).findAll().average("age");
    }
}
