package com.ryl.res.util;

/**
 * @author: ryl
 * @description:
 * @date: 2020-03-02 17:22:17
 */
public class SnowflakeIdGenerator {

    private final long startTime = 1498608000000L;
    private final long workerIdBits = 5L;
    private final long dataCenterIdBits = 5L;
    private final long maxWorkerId = 31L;
    private final long maxDataCenterId = 31L;
    private final long sequenceBits = 12L;
    private final long workerIdMoveBits = 12L;
    private final long dataCenterIdMoveBits = 17L;
    private final long timestampMoveBits = 22L;
    private final long sequenceMask = 4095L;
    private long workerId;
    private long dataCenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public SnowflakeIdGenerator(long workerId, long dataCenterId) {
        if (workerId <= maxWorkerId && workerId >= 0L) {
            if (dataCenterId <= maxDataCenterId && dataCenterId >= 0L) {
                this.workerId = workerId;
                this.dataCenterId = dataCenterId;
            } else {
                throw new IllegalArgumentException(String.format("DataCenter Id can't be greater than %d or less than 0", maxDataCenterId));
            }
        } else {
            throw new IllegalArgumentException(String.format("Worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
    }

    public synchronized long nextId() {
        long timestamp = this.currentTime();
        if (timestamp < this.lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
        } else {
            if (this.lastTimestamp == timestamp) {
                this.sequence = this.sequence + 1L & sequenceMask;
                if (this.sequence == 0L) {
                    timestamp = this.blockTillNextMillis(this.lastTimestamp);
                }
            } else {
                this.sequence = 0L;
            }

            this.lastTimestamp = timestamp;
            return timestamp - startTime << timestampMoveBits | this.dataCenterId << dataCenterIdMoveBits | this.workerId << workerIdMoveBits | this.sequence;
        }
    }

    protected long blockTillNextMillis(long lastTimestamp) {
        long timestamp;
        for(timestamp = this.currentTime(); timestamp <= lastTimestamp; timestamp = this.currentTime()) {
        }

        return timestamp;
    }

    protected long currentTime() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowflakeIdGenerator idWorker = new SnowflakeIdGenerator(0L, 0L);

        for(int i = 0; i < 1000; ++i) {
            long id = idWorker.nextId();
            System.out.println(Long.toBinaryString(id));
            System.out.println(id);
        }

    }
}
