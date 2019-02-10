package yucl.learn.validate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YuChunlei on 2017/7/9.
 */



public class UserCachedObject {

    @Valid
    @MyConstraint(table = "channelType",column = "id")
    private UserInfo userInfo;

    @MyConstraint(table = "sex",column = "id")
    private String sex;

   @MyConstraint(table = "sex",column = "id")
    private String[] ss =new String[]{"aa","bb"};

    @MyConstraint(table = "sex",column = "id")
    private UserInfo[] uu=new UserInfo[]{new UserInfo("xx",89),new UserInfo("yy",19)};

    //@MyConstraint(table = "sex",column = "id")
    private java.time.Year year;

    @Valid
    @MyConstraint(table = "sex",column = "id")
    private List<UserInfo> userInfoList;

    @MyConstraint(table = "sex",column = "id")
    Map<String,UserInfo> um = new HashMap<>();

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }



    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
