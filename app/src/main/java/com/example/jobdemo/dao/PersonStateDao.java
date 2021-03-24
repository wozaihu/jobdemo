package com.example.jobdemo.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.jobdemo.base.BaseDao;
import com.example.jobdemo.bean.PersonStateBean;

import java.util.List;

/**
 * 使用dao注解， @Insert注解插入，@Delete删除（根据定义的主键删除）， @Query注解后面可以写SQL语句，增删改查都可以
 * 方法名前为返回类型，delete返回类型只能为void或int（删除了那行）
 */
@Dao
public interface PersonStateDao extends BaseDao<PersonStateBean> {
    @Query("select * from PERSONSTATEBEAN")
    List<PersonStateBean> queryPerson();

    @Query("select * from personstatebean where person_id=(:personId)")
    PersonStateBean queryPersonById(int personId);

    @Query("select * from personstatebean where name=(:personName)")
    PersonStateBean queryPersonByName(String personName);

    @Query("select * from personstatebean where is_eat=(:isEat)")
    List<PersonStateBean> queryPersonByEat(boolean isEat);

    @Query("select * from personstatebean where type=(:type)")
    List<PersonStateBean> queryPersonByType(int type);

    @Query("delete from personstatebean where name in(:names)")
    void deletePersonName(String... names);

}
