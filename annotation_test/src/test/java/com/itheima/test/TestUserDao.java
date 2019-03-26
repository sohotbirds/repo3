package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @program: mybatis_eesy_day01
 * @description:
 * @author: Mr.Wang
 * @create: 2019-03-20 15:32
 */
public class TestUserDao {
    public static void main(String[] args) throws IOException {
        //1.读取配置文件
        InputStream in = org.apache.ibatis.io.Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.使用构建者模式创建工厂类
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.构建者模式拿到读取的配置文件之后 创建了工厂类
        SqlSessionFactory factory = builder.build(in);
        //4.使用工厂生产了SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //4.使用SqlSession 创建dao接口的代理对象
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        //5.拿到代理对象之后, 执行接口中的方法
        List<User> all = mapper.findAll();
        for (User us:all) {
            System.out.println(us);
        }
        //6.释放资源
        sqlSession.close();
        in.close();
    }
}
