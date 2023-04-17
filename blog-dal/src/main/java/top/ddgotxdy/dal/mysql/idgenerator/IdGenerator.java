package top.ddgotxdy.dal.mysql.idgenerator;

import java.util.Date;
import java.util.UUID;

/**
 * @author: ddgo
 * @description: id生成器
 */
public class IdGenerator {
 
    private static IdGenerator instance = new IdGenerator(0);
 
    public static IdGenerator initDefaultInstance(int machineId) {
        instance = new IdGenerator(machineId);
        return instance;
    }
 
    public static IdGenerator getInstance() {
        return instance;
    }
 
    public static long generateId() {
        return instance.nextId();
    }
 
    // private final static long TIME_BIT = 40; // max: 2318-06-04
    /**
     * max 31
     */
    private final static long MACHINE_BIT = 5;
    /**
     * 256/10ms
     */
    private final static long SEQUENCE_BIT = 8;
 
    /**
     * mask/max value
     */
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
 
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long TIMESTAMP_LEFT = MACHINE_BIT + SEQUENCE_BIT;
 
    private final long machineId;
    private long sequence = 0L;
    private long lastStamp = -1L;
 
    private IdGenerator(long machineId) {
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException(
                    "machineId can't be greater than " + MAX_MACHINE_NUM + " or less than 0");
        }
        this.machineId = machineId;
    }
 
    /**
     * generate new ID
     *
     * @return long
     */
    public synchronized long nextId() {
        long currStmp = getTimestamp();
        if (currStmp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }
 
        if (currStmp == lastStamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L) {
                currStmp = getNextTimestamp();
            }
        } else {
            sequence = 0L;
        }
 
        lastStamp = currStmp;
 
        return currStmp << TIMESTAMP_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }
 
    private long getNextTimestamp() {
        long mill = getTimestamp();
        while (mill <= lastStamp) {
            mill = getTimestamp();
        }
        return mill;
    }
 
    private long getTimestamp() {
        // per 10ms
        return System.currentTimeMillis() / 10;
    }
 
    public static Date parseIdTimestamp(long id) {
        return new Date((id >>> TIMESTAMP_LEFT) * 10);
    }
 
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}