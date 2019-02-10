package yucl.learn.aop;

import java.util.List;

/**
 * Created by YuChunlei on 2017/7/11.
 */
public class ClassB {


    private String prdId;


   @AutoSetter(table = "prds",column = "prdId")
    private String prdName;

    private  ClassE e ;

    private  int x ;

    private String[] ss;

    private List<String>  slist;

    private List<ClassE>  elist;

    public ClassE getE() {
        return e;
    }

    public void setE(ClassE e) {
        this.e = e;
    }

    public String getPrdId() {
        return prdId;
    }

    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }

    public String getPrdName() {
        return prdName;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }
}
