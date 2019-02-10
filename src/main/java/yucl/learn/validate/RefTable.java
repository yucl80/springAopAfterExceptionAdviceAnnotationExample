package yucl.learn.validate;

import java.util.Collection;

/**
 * Created by YuChunlei on 2017/7/9.
 */
public interface RefTable<T> {

    Collection<T> getCollection(String cl);

}
