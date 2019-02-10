package yucl.learn.validate;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.metadata.BeanDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Set;


/**
 * Created by YuChunlei on 2017/7/9.
 */

@Service
public class TestValidateServiceImpl implements TestValidateService {
    @Autowired
    private Validator validator;

    @Override
    public void test(@Valid UserCachedObject userCachedObject) {
        System.out.println("call test");
        BeanDescriptor ss = validator.getConstraintsForClass(UserCachedObject.class);
        System.out.println(ss.getConstrainedProperties());
        Set<ConstraintViolation<UserCachedObject>> rst = validator.validate(userCachedObject);
        System.out.println(rst);


    }

    public void testkryo(UserCachedObject userCachedObject){
        Kryo kryo = new Kryo();
        kryo.register(UserCachedObject.class);
        kryo.register(UserInfo.class);


        try {
            try (Output output = new Output(new FileOutputStream("d:/tmp/a", true), 8192)) {
                kryo.writeObject(output, userCachedObject);
                kryo.writeObject(output, userCachedObject);
                output.flush();
                output.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            UserCachedObject uco;
            try (Input input = new Input(new FileInputStream("d:/tmp/a"))) {
                while (!input.eof()) {
                    uco = kryo.readObject(input, UserCachedObject.class);
                    System.out.println(uco.getUserInfo().getAge());
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
