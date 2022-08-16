package com.tcmr.xihe.utils;

import com.tcmr.xihe.model.Page;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/08/14 14:36
 **/
public class BeanMapperUtil {

    private BeanMapperUtil() {
        // nothing
    }

    private static DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 转换page
     *
     * @param sourcePage       源
     * @param destinationClass 目标类
     * @return {@link Page}<{@link T}>
     */
    public static <T> Page<T> mapPage(Page<?> sourcePage, Class<T> destinationClass) {
        if (sourcePage == null || destinationClass == null) {
            return null;
        }
        Page<T> page = new Page<>();
        copy(sourcePage, page);
        page.setObjects(mapList(sourcePage.getObjects(), destinationClass));
        return page;
    }

    /**
     * 转换Page Spring.data 暂不使用
     *
     * @param sourcePage
     * @param destinationClass
     * @param <T>
     * @return Page<T>
     */
    //public static <T> org.springframework.data.domain.Page<T> mapPage(org.springframework.data.domain.Page<?> sourcePage,
    //                                                                  Class<T> destinationClass) {
    //    if (sourcePage == null || destinationClass == null) {
    //        return null;
    //    }
    //    org.springframework.data.domain.Page<T> page =
    //            new org.springframework.data.domain.PageImpl<>(mapList(sourcePage.getContent(), destinationClass));
    //    copy(sourcePage, page);
    //    return page;
    //}

    /**
     * 转换对象的类型.
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        return dozer.map(source, destinationClass);
    }

    /**
     * 转换Collection中对象的类型.
     */
    public static <T> List<T> mapList(Collection<?> sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<>();
        if (CollectionUtils.isEmpty(sourceList)) {
            return destinationList;
        }

        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }

    /**
     * 获取dozer
     *
     * @return
     */
    public static Mapper getMapper() {
        return dozer;
    }
}
