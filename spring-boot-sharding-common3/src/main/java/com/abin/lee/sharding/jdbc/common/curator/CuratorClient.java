package com.abin.lee.sharding.jdbc.common.curator;

import com.abin.lee.sharding.jdbc.common.util.AddressUtils;
import com.google.common.primitives.Longs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by lee on 2019/6/26.
 */
@Slf4j
public class CuratorClient {

    @Value("${curator.address}")
    String curatorAddress;

    private static CuratorFramework client = null;
    private static final String parentPath = "/generatorid";

    public CuratorFramework create() throws Exception {
        client = CuratorFrameworkFactory.builder()
                .connectString(curatorAddress)
                .sessionTimeoutMs(10000)
                .connectionTimeoutMs(10000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 5))
                .build();
        client.start();

        return client;
    }

    @PostConstruct
    public void init() throws Exception {
        CuratorFramework client = this.create();
        String ip = AddressUtils.getInnetIp();
        if (StringUtils.isBlank(ip)) {
            throw new RuntimeException("get ip error");
        }
        Stat stat = client.checkExists().forPath(parentPath);
        if (null == stat) {
            log.info("parentPath=" + parentPath + ", is not exists, create it");
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(parentPath + "/" + ip + "_", ip.getBytes());
        } else {
            boolean flag = this.findPath(ip, client);
            if(!flag){
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                        .forPath(parentPath + "/" + ip + "_", ip.getBytes());
            }
            Long workId = this.findWorkId();
            log.info("parentPath=" + parentPath + ", is exists, workId=" + workId);
        }

    }


    public boolean findPath(String ip, CuratorFramework client) throws Exception {
        List<String> children = client.getChildren().forPath(parentPath);
        if(CollectionUtils.isNotEmpty(children) && children.size()>1){
            Integer total = 0 ;
            for (String child : children) {
                if (StringUtils.startsWith(child, ip)) {
                    ++total ;
                }
            }
            if(total > 1) {
                throw new RuntimeException("parentPath=" + parentPath + ", zkpath > 1 ");
            }
        }
        for (String child : children) {
            if (StringUtils.startsWith(child, ip)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Long findWorkId() throws Exception {
        String ip = AddressUtils.getInnetIp();
        List<String> children = client.getChildren().forPath(parentPath);
        if(CollectionUtils.isNotEmpty(children) && children.size()>1){
            Integer total = 0 ;
            for (String child : children) {
                if (StringUtils.startsWith(child, ip)) {
                    ++total ;
                }
            }
            if(total > 1) {
                throw new RuntimeException("parentPath=" + parentPath + ", zkpath > 1 ");
            }
        }
        for (String child : children) {
            if (StringUtils.startsWith(child, ip)) {
                String workId = child.substring(child.indexOf("_") + 1, child.length());
                Long id = Longs.tryParse(workId);
                return id;
            }
        }
        return null;
    }



    public static void main(String[] args) throws Exception {
        new CuratorClient().init();


        Long workId = new CuratorClient().findWorkId();
        System.out.println("workId=" + workId);

    }


}
