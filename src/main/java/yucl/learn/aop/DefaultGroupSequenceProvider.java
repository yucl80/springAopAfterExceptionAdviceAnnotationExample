package yucl.learn.aop;

import java.util.List;

/**
 * Created by YuChunlei on 2017/7/13.
 */
public interface DefaultGroupSequenceProvider<T> {

    /**
     * This method returns the default group sequence for the given instance.
     * <p>
     * The object parameter allows to dynamically compose the default group sequence in function of the validated value state.
     * </p>
     *
     * @param object the instance being validated. This value can be {@code null} in case this method was called as part of
     * {@linkplain javax.validation.Validator#validateValue(Class, String, Object, Class[]) Validator#validateValue}.
     *
     * @return a list of classes specifying the default group sequence. The same constraints to the redefined group list
     *         apply as for lists defined via {@code GroupSequence}. In particular the list has to contain the type T.
     */
    List<Class<?>> getValidationGroups(T object);
}

