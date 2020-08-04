package com.dreams.rj.learn;

import com.dreams.rj.learn.mapper.UserMapper;
import com.dreams.rj.learn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Hello world!
 */
public class App {
    private Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        userMybatisWithProps();
    }

    public static void mybatis1() {
        String resource = "mybatis-config.xml";
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession session = ssf.openSession();
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectOne(1L);
            System.out.println(user);

            User user2 = mapper.selectOne(1L);
            System.out.println(user);

            System.out.println(user == user2);

//            session.selectOne("com.dreams.rj.learn.mapper.UserMapper.selectOne", 1L);
//            List<User> users = mapper.selectAll();
//            for (User u : users) {
//                System.out.println(u);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void userMybatisWithProps() {
        String resource = "mybatis-config-properties.xml";
        try {

            InputStream propIns = Resources.getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(propIns);

            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(resourceAsStream, properties);

            SqlSession session = ssf.openSession();
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectOne(1L);
            System.out.println(user);

            User user2 = mapper.selectOne(2L);
            System.out.println(user);

            System.out.println(user == user2);

//            session.selectOne("com.dreams.rj.learn.mapper.UserMapper.selectOne", 1L);
//            List<User> users = mapper.selectAll();
//            for (User u : users) {
//                System.out.println(u);
//            }

            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
