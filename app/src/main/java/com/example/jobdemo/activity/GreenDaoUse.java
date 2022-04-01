package com.example.jobdemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jobdemo.MyApplication;
import com.example.jobdemo.R;
import com.example.jobdemo.adapter.GreenUseAdapter;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.bean.UserDbTest;
import com.example.jobdemo.database.UserDbTestDao;
import com.example.jobdemo.databinding.ActivityRecycleViewDemo2Binding;
import com.example.jobdemo.util.RandomNameUtil;
import com.example.jobdemo.util.RandomUtils;
import com.example.jobdemo.util.ToastUtils;

import java.util.List;

/**
 * @author Administrator
 */
public class GreenDaoUse extends BaseActivity implements GreenUseAdapter.ItemClickCell {


    private UserDbTestDao userDbTestDao;
    private List<UserDbTest> list;
    private GreenUseAdapter adapter;
    private ActivityRecycleViewDemo2Binding binding;

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
        adapter.setItemClickCell(this::itemClick);
        binding.rvUserList.setAdapter(adapter);
        binding.refreshLayout.finishLoadMoreWithNoMoreData();
        binding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            list.clear();
            list.addAll(userDbTestDao.loadAll());
            binding.refreshLayout.finishRefresh(1000, true, true);
            adapter.notifyDataSetChanged();
        });

        binding.tvAdd.setOnClickListener(v -> {
            addRow(0);
        });

        binding.tvAddNullName.setOnClickListener(v -> {
            addRow(1);
        });


        binding.tvDelete.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(binding.etEnterUserId.getText().toString().trim())) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getUserId().equals(Long.parseLong(binding.etEnterUserId.getText().toString().trim()))) {
                        list.remove(i);
                        adapter.notifyItemRemoved(i);
                        adapter.notifyItemRangeChanged(i, list.size() - i);
                        userDbTestDao.deleteByKey(Long.parseLong(binding.etEnterUserId.getText().toString().trim()));
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
                    if (list.get(i).getUserId().equals(Long.parseLong(id))) {
                        list.get(i).setUserName("李白");
                        list.get(i).setAge(1320);
                        adapter.notifyItemRangeChanged(i, 1);
                        UserDbTest test = userDbTestDao.load(Long.parseLong(id));
                        test.setUserName("李白");
                        test.setAge(1320);
                        userDbTestDao.update(test);
                        return;
                    }
                }
            }
        });

        binding.tvAlterNameNull.setOnClickListener(v -> {
            Long id = Long.parseLong(binding.etEnterUserId.getText().toString().trim());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUserId().equals(id)) {
                    UserDbTest user = new UserDbTest();
                    user.setUserId(id);
                    user.setAge(RandomUtils.getInstance().getRandomAge());
                    userDbTestDao.update(user);
                    list.set(i, user);
                    adapter.notifyItemRangeChanged(i, 1);
                    return;
                }
            }
        });
    }

    /**
     * @param tag 0 正常添加，1添加姓名为空
     */
    private void addRow(int tag) {
        Long userId = RandomUtils.getInstance().getRandomId(8);
        int age = RandomUtils.getInstance().getRandomAge();
        String name = null;
        if (0 == tag) {
            name = RandomNameUtil.randomName(true, 3);
        }
        UserDbTest userDbTest = new UserDbTest(userId, name, age);
        userDbTestDao.insertOrReplace(userDbTest);
        if (list.size() == 0 || list.get(list.size() - 1).getUserId() < userId) {
            list.add(userDbTest);
            adapter.notifyItemInserted(list.size() - 1);
            ToastUtils.shortToast(this, String.format(getString(R.string.addPosition)
                    , list.size()
                    , list.get(list.size() - 1).getUserName()));
            binding.rvUserList.smoothScrollToPosition(list.size() - 1);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUserId() > userId) {
                    list.add(i, userDbTest);
                    adapter.notifyItemRangeInserted(i, 1);
                    adapter.notifyItemRangeChanged(i, list.size() - i);
                    ToastUtils.shortToast(this, String.format(getString(R.string.addPosition)
                            , i + 1
                            , list.get(i).getUserName()));
                    binding.rvUserList.smoothScrollToPosition(i);
                    return;
                }
            }
        }
    }

    @Override
    public void itemClick(int position, Long id) {
        binding.etEnterUserId.setText(String.valueOf(id));
        ToastUtils.shortToast(this, String.format(getString(R.string.click_object), list.get(position).getUserName()));
    }
}