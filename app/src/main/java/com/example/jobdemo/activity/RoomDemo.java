package com.example.jobdemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jobdemo.R;
import com.example.jobdemo.adapter.RoomDemoAdapter;
import com.example.jobdemo.base.AppDataBase;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.bean.PersonStateBean;
import com.example.jobdemo.dao.PersonStateDao;
import com.example.jobdemo.databinding.RoomdameBinding;
import com.example.jobdemo.util.LogUtil;
import com.example.jobdemo.util.RandomNameUtil;
import com.example.jobdemo.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomDemo extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "RoomDemo";
    private List<PersonStateBean> list = new ArrayList<>();
    private RoomDemoAdapter adapter;
    PersonStateDao personStateDao = AppDataBase.getInstance().getPersonStateDao();
    private RoomdameBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RoomdameBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.rvPerson.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RoomDemoAdapter(this, list);
        binding.rvPerson.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_insert) {
            insertDb();
        } else if (id == R.id.btn_queryType) {
            List<PersonStateBean> type1 = personStateDao.queryPersonByType(1);
            notifyAdapter(type1);
        } else if (id == R.id.btn_queryAll) {
            List<PersonStateBean> beans = personStateDao.queryPerson();
            if (beans != null) {
                LogUtil.showD(TAG, beans.get(0).toString());
            }
            notifyAdapter(beans);
        } else if (id == R.id.btn_queryPersonId) {
            List<PersonStateBean> stateBean = personStateDao.queryPersonByEat(true);
            notifyAdapter(stateBean);
        } else if (id == R.id.btn_queryPersonAge || id == R.id.btn_queryPersonName || id == R.id.btn_deleteName) {
            if (TextUtils.isEmpty(binding.etDeleteName.getText().toString().trim())) {
                ToastUtils.shortToast(this,"请输入要删除的姓名");
                return;
            }
            LogUtil.showD(TAG, "删除：" + binding.etDeleteName.getText().toString().trim());
            personStateDao.deletePersonName(binding.etDeleteName.getText().toString().trim());
        }
    }

    private void notifyAdapter(List<PersonStateBean> beanList) {
        list.clear();
        list.addAll(beanList);
        adapter.notifyDataSetChanged();
    }

    /**
     * 添加一个数据到数据库
     */
    private void insertDb() {
        PersonStateBean bean = new PersonStateBean();
        int i = new Random().nextInt(99999);
        bean.personId = i;
        bean.name = RandomNameUtil.randomName(false, 4);
        bean.age = i + 1; //年级比personId大一
        if (i % 2 == 0) {
            bean.isEat = true;
            bean.type = 2;
        } else {
            bean.isEat = false;
            bean.type = 1;
        }
        personStateDao.insert(bean);
        LogUtil.showD(TAG, bean.toString());
    }

    /**
     * @param bean 要删除的列
     */
    private void delete(PersonStateBean bean) {
        personStateDao.delete(bean);
    }
}
