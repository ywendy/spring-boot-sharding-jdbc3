package com.abin.lee.sharding.jdbc.common.test.snowflake;

/**
 * 1 34 6 12 11
 */
public class SnowflakeIdWorker {

    // ==============================Fields===========================================
    /** 开始时间截 (2015-01-01) */
    private final long twepoch = 1420041600L;

    /** 机器id所占的位数 */
    private final long workerIdBits = 6L;

    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /** 分配给userId基因的位数 */
    private final long geneBits = 11L;

    /** 序列在id中占的位数 */
    private final long sequenceBits = 11L;

    /** 机器ID向左移10+6=22位 */
    private final long sequenceIdShift = geneBits;

    /** 机器ID向左移10+6=22位 */
    private final long workerIdShift = sequenceBits + geneBits;

    /** 时间截向左移22位(10+6+6) */
    private final long timestampLeftShift = sequenceBits + workerIdBits + geneBits;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 工作机器ID(0~31) */
    private long workerId;

    /** 数据中心ID(0~31) */
    private long datacenterId;

    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;


    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    //==============================Constructors=====================================
    /**
     * 构造函数
     * @param workerId 工作ID (0~31)
     */
    public SnowflakeIdWorker(long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.workerId = workerId;
    }

    // ==============================Methods==========================================
    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    public synchronized long nextId(Long geneEnd) {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }else {
            //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) //
                | (workerId << workerIdShift) //
                | sequence << sequenceIdShift
                | geneEnd & 0x7ff;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis()/1000;
    }



    //==============================Test=============================================
    /** 测试 */
    public static void main(String[] args) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1);
        for (int i = 0; i < 10; i++) {
            Long param = (long)(Math.random()*10000);
            long id = idWorker.nextId(param);
            long paramResidual = param % 1024L;
            long idResidual = id % 1024L;
            if(paramResidual == idResidual){
                System.out.println("param=" +param +",paramResidual=" +paramResidual +",    " + "id=" +id +",idResidual=" +idResidual);
            }else{
                System.out.println("----------------");
            }
//            System.out.println(Long.toBinaryString(id));
//            System.out.println(id);

        }
//        long workerIdBits = 6L ;
//        long maxWorkerId = -1L ^ (-1L << workerIdBits);
//        System.out.println("maxWorkerId="+maxWorkerId);
    }

    public static Long getId(Long param){
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1);
        long id = idWorker.nextId(1234567L);
        return id;
    }

}


