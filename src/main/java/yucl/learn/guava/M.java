package yucl.learn.guava;

import com.google.common.base.Optional;
import com.google.common.collect.BinaryTreeTraverser;
import com.google.common.collect.TreeTraverser;

/**
 * Created by YuChunlei on 2017/7/13.
 */
public class M {

    public static void main(String[] args){
        TreeTraverser<Object> treTraverser = new BinaryTreeTraverser<Object>() {
            @Override
            public Optional<Object> leftChild(Object userCachedObject) {
                return null;
            }

            @Override
            public Optional<Object> rightChild(Object userCachedObject) {
                return null;
            }
        };


    }
}
