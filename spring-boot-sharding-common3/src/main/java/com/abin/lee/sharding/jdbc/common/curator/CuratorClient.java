package com.abin.lee.sharding.jdbc.common.curator;

import com.abin.lee.sharding.jdbc.common.util.AddressUtils;
import com.google.common.primitives.Ints;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by lee on 2019/6/26.
 */
@Slf4j
public class CuratorClient {

    private static CuratorFramework client = null ;
    private static final String parentPath = "/secgenerator";

    public CuratorFramework create() throws Exception {
        client = CuratorFrameworkFactory.builder()
                .connectString("10.96.79.68:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(3000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();

        return client;
    }

    public void init() throws Exception {
        CuratorFramework client = this.create();
        String ip = AddressUtils.getInnetIp();
        if(StringUtils.isBlank(ip)){
            throw new RuntimeException("get ip error");
        }
        Stat stat = client.checkExists().forPath(parentPath);
        if(null == stat){
            log.info("parentPath="+parentPath+", is not exists, create it");
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(parentPath+"/"+ip+"_", ip.getBytes());
        }else{
            Integer workId = new CuratorClient().findWorkId();
            log.info("parentPath="+parentPath+", is exists, workId="+workId);
        }

    }


    public boolean findPath(String ip, CuratorFramework client) throws Exception {
        List<String> children = client.getChildren().forPath(parentPath); //获取子节点
        for(String child : children) {
            if(StringUtils.startsWith(child, ip)){
                return Boolean.TRUE;
            }
        }
       return Boolean.FALSE;
    }

    public Integer findWorkId() throws Exception {
        String ip = AddressUtils.getInnetIp();
        List<String> children = client.getChildren().forPath(parentPath); //获取子节点
        for(String child : children) {
            if(StringUtils.startsWith(child, ip)){
                String workId = child.substring(child.indexOf("_")+1, child.length());
                Integer id = Ints.tryParse(workId);
                return id;
            }
        }
        return null;
    }



    public static void main(String[] args) throws Exception {
        new CuratorClient().init();
      
        Integer workId = new CuratorClient().findWorkId();
        System.out.println("workId="+workId);

    }




}
