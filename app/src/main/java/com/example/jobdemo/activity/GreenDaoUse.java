package com.example.jobdemo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jobdemo.MyApplication;
import com.example.jobdemo.adapter.GreenUseAdapter;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.bean.UserDbTest;
import com.example.jobdemo.database.UserDbTestDao;
import com.example.jobdemo.databinding.ActivityRecycleViewDemo2Binding;
import com.example.jobdemo.util.RandomNameUtil;
import com.example.jobdemo.util.RandomUtils;

import java.util.List;

/**
 * @author Administrator
 */
public class GreenDaoUse extends BaseActivity {


    private UserDbTestDao userDbTestDao;
    private List<UserDbTest> list;
    private GreenUseAdapter adapter;
    private ActivityRecycleViewDemo2Binding binding;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecycleViewDemo2Binding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        userDbTestDao = MyApplication.getDaoSession().getUserDbTestDao();
        binding.rvUserList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvUserList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        list = userDbTestDao.loadAll();
        adapter = new GreenUseAdapter(this, list);
        binding.rvUserList.setAdapter(adapter);

        binding.refreshLayout.finishLoadMoreWithNoMoreData();
        binding.tvAdd.setOnClickListener(v -> {
            String userId = RandomUtils.getInstance().getRandomInt(8);
            String name = RandomNameUtil.randomName(true, 3);
            int age = RandomUtils.getInstance().getRandomAge();
            UserDbTest userDbTest = new UserDbTest(userId, name, age);
            userDbTestDao.insertOrReplace(userDbTest);
            list.add(userDbTest);
            adapter.notifyItemRangeInserted(list.size() - 1, 1);
        });
        binding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            list.clear();
            list.addAll(userDbTestDao.loadAll());
            binding.refreshLayout.finishRefresh(1000, true, true);
            adapter.notifyDataSetChanged();
        });

        binding.tvDelete.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(binding.etEnterUserId.getText().toString().trim())) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUserId().equals(binding.etEnterUserId.getText().toString().trim())) {
                        list.remove(i);
                        adapter.notifyItemRemoved(i);
                        userDbTestDao.deleteByKey(binding.etEnterUserId.getText().toString().trim());
                        return;
                    }
                }
            }
        });

        binding.tvDeleteAll.setOnClickListener(v -> {
            userDbTestDao.deleteAll();
            list.clear();
            adapter.notifyDataSetChanged();
        });

        binding.tvAlter.setOnClickListener(v -> {
            String id = binding.etEnterUserId.getText().toString().trim();
            if (!TextUtils.isEmpty(id)) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUserId().equals(id)) {
                        list.get(i).setUserName("李白");
                        list.get(i).setAge(1320);
                        adapter.notifyItemRangeChanged(i, 1);
                        userDbTestDao.queryBuilder().where(UserDbTestDao.Properties.UserId.eq(id)).list();
                        UserDbTest test = userDbTestDao.load(id);
                        test.setUserName("李白");
                        test.setAge(1320);
                        userDbTestDao.update(test);
                        return;
                    }
                }
            }
        });

        binding.tvAddNullName.setOnClickListener(v -> {
            String userId = RandomUtils.getInstance().getRandomInt(8);
            int age = RandomUtils.getInstance().getRandomAge();
            UserDbTest userDbTest = new UserDbTest(userId, null, age);
            userDbTestDao.insertOrReplace(userDbTest);
            list.add(userDbTest);
            adapter.notifyItemRangeChanged(list.size() - 1, list.size());
        });

        binding.tvAlterNameNull.setOnClickListener(v -> {
            String id = binding.etEnterUserId.getText().toString().trim();
            if (!TextUtils.isEmpty(id)) {
                UserDbTest user = new UserDbTest();
                user.setUserId(id);
                user.setAge(RandomUtils.getInstance().getRandomAge());
                //可以覆盖，但顺序变为最后了，因为是新插入的
                userDbTestDao.update(user);
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUserId().equals(id)) {
                        list.set(i, user);
                        adapter.notifyItemRangeChanged(i, 1);
                        return;
                    }
                }
            }
        });
    }
}