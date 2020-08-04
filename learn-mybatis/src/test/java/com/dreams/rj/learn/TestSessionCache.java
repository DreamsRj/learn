package com.dreams.rj.learn;

import com.dreams.rj.learn.mapper.UserMapper;
import com.dreams.rj.learn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 测试一级缓存
 */
public class TestSessionCache {

    /**
     * 第一次和第二次查询在同一个session 中查询Id为1的对象，两次返回同一个对象。同时sql也只执行了一次
     */
    @Test
    public void testSessionCache() {
        String resource = "mybatis-config.xml";
        try {
            //读取配置文件，创建SessionFactory
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(resourceAsStream);

            //获取一个链接（数据库会话）
            SqlSession session = ssf.openSession();
            //获取mapper
            UserMapper mapper = session.getMapper(UserMapper.class);

            User user = mapper.selectOne(1L); //第一次查询
            System.out.println(user);

            User user2 = mapper.selectOne(1L); //第二次查询
            System.out.println(user);

            System.out.println(user == user2); //true, 返回的同一个对象

            SqlSession session2 = ssf.openSession();//重新获取链接
            UserMapper mapper1 = session2.getMapper(UserMapper.class);

            User user3 = mapper1.selectOne(1L);
            System.out.println(user == user3); //false, 返回的同一个对象


            session.close();
            session2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试更新数据后对缓存的影响
     */
    @Test
    public void testUpdateCache() {
        String resource = "mybatis-config.xml";
        try {
            //读取配置文件，创建SessionFactory
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(resourceAsStream);

            //获取一个链接（数据库会话）
            SqlSession session = ssf.openSession();
            //获取mapper
            UserMapper mapper = session.getMapper(UserMapper.class);

            User user = mapper.selectOne(1L); //第一次查询
            System.out.println(user);

            //执行更新操作
            user.setName("NewName");
            int i = mapper.updateUserName(user);
            System.out.println("update record num : " + i);

            User user2 = mapper.selectOne(1L); //第二次查询
            System.out.println(user);

            System.out.println("user equal : " + (user == user2));
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testStatementCache() {
        String resource = "mybatis-config.xml";
        try {
            //读取配置文件，创建SessionFactory
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(resourceAsStream);
            //设置缓存范围为 STATEMENT
            ssf.getConfiguration().setLocalCacheScope(LocalCacheScope.STATEMENT);

            //获取一个链接（数据库会话）
            SqlSession session = ssf.openSession();
            //获取mapper
            UserMapper mapper = session.getMapper(UserMapper.class);

            User user = mapper.selectOne(1L); //第一次查询
            System.out.println(user);

            User user2 = mapper.selectOne(1L); //第二次查询
            System.out.println(user);

            System.out.println("user equal : " + (user == user2));
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
