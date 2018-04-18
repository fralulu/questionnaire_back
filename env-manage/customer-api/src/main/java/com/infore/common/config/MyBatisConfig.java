package com.infore.common.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.pagehelper.PageHelper;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by xuyao on 2017/6/26.
 */
@Configuration
//@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
@MapperScan(basePackages="com.infore.**.mapper")//扫描的是mapper.xml中namespace指向值的包位置
public class MyBatisConfig {

    @Autowired
    private Environment env;
    
    
    /**
     * 创建数据源
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     */
    @Bean
    public DataSource getDataSource() throws Exception{
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("jdbc.driverClassName"));
        props.put("url", env.getProperty("jdbc.url"));
        props.put("username", env.getProperty("jdbc.username"));
        props.put("password", env.getProperty("jdbc.password"));
        
       //下面为连接池的补充设置，应用到上面所有数据源中  
        props.put("initialSize", env.getProperty("druid.initialSize"));
        props.put("minIdle", env.getProperty("druid.minIdle"));
        props.put("maxActive",  env.getProperty("druid.maxActive"));
        props.put("maxWait",  env.getProperty("druid.maxWait"));
        props.put("removeAbandoned",  env.getProperty("druid.removeAbandoned"));
        props.put("removeAbandonedTimeout", env.getProperty("druid.removeAbandonedTimeout"));
        props.put("testWhileIdle", env.getProperty("druid.testWhileIdle"));
        props.put("testOnBorrow", env.getProperty("druid.testOnBorrow"));
        props.put("poolPreparedStatements", env.getProperty("druid.poolPreparedStatements"));
        props.put("maxPoolPreparedStatementPerConnectionSize", env.getProperty("druid.maxPoolPreparedStatementPerConnectionSize"));
//        
        props.put("validationQuery","select 1 ");
        return DruidDataSourceFactory.createDataSource(props);
    }
    
    @Bean
    public PlatformTransactionManager txManager() throws Exception {
        return new DataSourceTransactionManager(getDataSource() );
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory( DataSource ds) throws Exception{
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);//指定数据源(这个必须有，否则报错)
        fb.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//指定基包
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//指定xml文件位置

        return fb.getObject();
    }
    
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	
    

}
