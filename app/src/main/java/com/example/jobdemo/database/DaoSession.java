package com.example.jobdemo.database;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.jobdemo.bean.UserDbTest;

import com.example.jobdemo.database.UserDbTestDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDbTestDaoConfig;

    private final UserDbTestDao userDbTestDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDbTestDaoConfig = daoConfigMap.get(UserDbTestDao.class).clone();
        userDbTestDaoConfig.initIdentityScope(type);

        userDbTestDao = new UserDbTestDao(userDbTestDaoConfig, this);

        registerDao(UserDbTest.class, userDbTestDao);
    }
    
    public void clear() {
        userDbTestDaoConfig.clearIdentityScope();
    }

    public UserDbTestDao getUserDbTestDao() {
        return userDbTestDao;
    }

}
