package me.xqh.awesome.delayqueue.core.job;

/**
 * @author xuqinghua
 * @date 2018-09-26
 * job定义
 */
public class AwesomeJob {
    private String id;
    private String topic;
    private long delaySeconds;
    private long expireTime;
    private String data;
    private int triggerType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getDelaySeconds() {
        return delaySeconds;
    }

    public void setDelaySeconds(long delaySeconds) {
        this.delaySeconds = delaySeconds;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(int triggerType) {
        this.triggerType = triggerType;
    }

    @Override
    public String toString() {
        return "AwesomeJob{" +
                "id='" + id + '\'' +
                ", topic='" + topic + '\'' +
                ", delaySeconds=" + delaySeconds +
                ", expireTime=" + expireTime +
                ", data='" + data + '\'' +
                ", triggerType=" + triggerType +
                '}';
    }
}
