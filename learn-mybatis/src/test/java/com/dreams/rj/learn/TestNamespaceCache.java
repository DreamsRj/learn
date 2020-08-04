package com.dreams.rj.learn;

import com.dreams.rj.learn.mapper.UserMapper;
import com.dreams.rj.learn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试二级缓存
 */
public class TestNamespaceCache {
    private SqlSessionFactory ssf;

    @Before
    public void init() throws IOException {
        String resource = "mybatis-config-cache-twoLevel.xml";
        //读取配置文件，创建SessionFactory
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        ssf = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //设置一级缓存为 STATEMENT，可以理解为关闭一级缓存
        ssf.getConfiguration().setLocalCacheScope(LocalCacheScope.STATEMENT);
    }


    /**
     * 在mybatis的配置文件中开启二级缓存，没有在mapper中开启缓存，仍然查询两次
     */
    @Test
    public void testNamespaceCache() {

        SqlSession session1 = ssf.openSession();
        SqlSession session2 = ssf.openSession();

        UserMapper mapper1 = session1.getMapper(UserMapper.class);
        UserMapper mapper2 = session1.getMapper(UserMapper.class);

        User user1 = mapper1.selectOne(1L); //第一次查询
        System.out.println(user1);

        User user2 = mapper2.selectOne(1L); //第二次查询
        System.out.println(user2);

        System.out.println(user1 == user2); //true, 返回的同一个对象

        session1.close();
        session2.close();
    }

    /**
     * 在提交事务后测试缓存
     */
    @Test
    public void testNamespaceCacheWithCommit() {

        SqlSession session1 = ssf.openSession();
        SqlSession session2 = ssf.openSession();


        UserMapper mapper1 = session1.getMapper(UserMapper.class);
        UserMapper mapper2 = session1.getMapper(UserMapper.class);

        User user1 = mapper1.selectOne(1L); //第一次查询
        System.out.println(user1);
        //提交事务后是否可以命中缓存
        session1.commit();

        User user2 = mapper2.selectOne(1L); //第二次查询
        System.out.println(user2);

        System.out.println(user1 == user2); //true, 返回的同一个对象

        session1.close();
        session2.close();
    }

}
