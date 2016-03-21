package com.app.mvc.datasource;

/**
 * AOP切面的执行顺序, 由小到大执行.
 * 如某方法m有3个切面, 都是Around类型, Order分别为1,2,3.
 * 则m方法的执行顺序为 1,2,3,m,3,2,1
 * <p/>
 * Created by jimin on 16/03/22.
 */
public interface AspectOrder {

    /**
     * 动态数据源
     */
    int ROUTING_DATA_SOURCE = 100;

    /**
     * 事务
     * <pre>
     *   <tx:annotation-driven transaction-manager="transactionManager" proxy-target-class="true" order="200" />
     * </pre>
     *
     * @see org.springframework.transaction.annotation.Transactional
     */
    int TRANSACTIONAL = 200;

}
