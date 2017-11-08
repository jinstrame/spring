package quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

import static java.lang.reflect.Proxy.newProxyInstance;
import static java.util.Objects.nonNull;

public class ProfilingBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class> nameClassMap = new HashMap<>();

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            nameClassMap.put(beanName, beanClass);
        }

        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = nameClassMap.get(beanName);
        if (nonNull(beanClass)) {
            return newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
                System.out.println("Profiling...");
                long before = System.nanoTime();
                Object ret = method.invoke(bean, args);
                long after = System.nanoTime();
                System.out.println("Done.");
                System.out.println(after - before);
                return ret;
            });
        }
        return bean;
    }
}
