package yucl.learn.test;

/**
 * Created by YuChunlei on 2017/7/2.
 */
public class ApplyDto {

    private String clientId;
    private int age;

    public ApplyDto() {
    }

    public ApplyDto(String clientId, int age) {
        this.clientId = clientId;
        this.age = age;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
