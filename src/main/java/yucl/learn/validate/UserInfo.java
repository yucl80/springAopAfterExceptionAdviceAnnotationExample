package yucl.learn.validate;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * Created by YuChunlei on 2017/7/9.
 */


public class UserInfo {

    //@javax.validation.Valid
    @NotNull
    private String name;

    //@javax.validation.Valid
    @DecimalMax("60")
    @DecimalMin("18")
    @MyConstraint(table = "sex",column = "id")
    private int age;

    public UserInfo() {
    }

    public UserInfo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
