# springboot-shardingjdbc
使用spring boot+sharding-jdbc+mybatis 实现分库分表


数据分片
http://shardingsphere.io/document/current/cn/manual/sharding-jdbc/usage/sharding/


spring-boot-starter-actuator这个库让我们可以访问应用的很多信息，包括：/env、/info、/metrics、/health等。
现在运行程序，然后在浏览器中访问：http://localhost:8080/health






订单表逻辑语句：
CREATE DATABASE IF NOT EXISTS cloud0 ;

CREATE TABLE IF NOT EXISTS t_order (id bigint(20) NOT NULL, user_id bigint(20) NOT NULL, status VARCHAR(50), PRIMARY KEY (id));
CREATE TABLE IF NOT EXISTS t_order_0 (id bigint(20) NOT NULL, user_id bigint(20) NOT NULL, status VARCHAR(50), PRIMARY KEY (id));
CREATE TABLE IF NOT EXISTS t_order_1 (id bigint(20) NOT NULL, user_id bigint(20) NOT NULL, status VARCHAR(50), PRIMARY KEY (id));
CREATE TABLE IF NOT EXISTS t_order_2 (id bigint(20) NOT NULL, user_id bigint(20) NOT NULL, status VARCHAR(50), PRIMARY KEY (id));
CREATE TABLE IF NOT EXISTS t_order_3 (id bigint(20) NOT NULL, user_id bigint(20) NOT NULL, status VARCHAR(50), PRIMARY KEY (id));



订单项逻辑语句：
CREATE DATABASE IF NOT EXISTS cloud1 ;


CREATE TABLE IF NOT EXISTS t_order_item (id bigint(20) NOT NULL, order_id bigint(20) NOT NULL, user_id bigint(20) NOT NULL, PRIMARY KEY (id));
CREATE TABLE IF NOT EXISTS t_order_item_0 (id bigint(20) NOT NULL, order_id bigint(20) NOT NULL, user_id bigint(20) NOT NULL, PRIMARY KEY (id));
CREATE TABLE IF NOT EXISTS t_order_item_1 (id bigint(20) NOT NULL, order_id bigint(20) NOT NULL, user_id bigint(20) NOT NULL, PRIMARY KEY (id));
CREATE TABLE IF NOT EXISTS t_order_item_2 (id bigint(20) NOT NULL, order_id bigint(20) NOT NULL, user_id bigint(20) NOT NULL, PRIMARY KEY (id));
CREATE TABLE IF NOT EXISTS t_order_item_3 (id bigint(20) NOT NULL, order_id bigint(20) NOT NULL, user_id bigint(20) NOT NULL, PRIMARY KEY (id));




商品表逻辑语句：
CREATE DATABASE IF NOT EXISTS elastic0 ;
CREATE TABLE IF NOT EXISTS t_business (business_id bigint(20) NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (business_id));




分库分表，读写分离配置官方
http://shardingsphere.io/document/current/en/manual/sharding-jdbc/configuration/config-java/





cd spring-boot-sharding-api3/

mvn mybatis-generator:generate




swagger2:
http://localhost:9061/swagger-ui.html








