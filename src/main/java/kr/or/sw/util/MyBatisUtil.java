package kr.or.sw.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Locale;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MyBatisUtil {

    private static class SqlSessionFactoryHolder {  // Lazy Holder
        private static final SqlSessionFactory INSTANCE = buildSqlSessionFactory();
    }

    private static SqlSessionFactory buildSqlSessionFactory() {
        try (Reader reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml")) {
            log.info(String.valueOf(Locale.getDefault()));
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            return builder.build(reader);
        } catch (IOException e) {
            log.error("SqlSessionFactory 초기화 실패", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return SqlSessionFactoryHolder.INSTANCE;
    }

    public static SqlSession getSession() {
        return getSqlSessionFactory().openSession();
    }
}
