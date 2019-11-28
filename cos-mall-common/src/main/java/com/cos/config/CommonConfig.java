package com.cos.config;

import com.cos.service.sys.ParamsSettingService;
import com.cos.service.sys.ParamsSettingServiceImpl;
import com.cos.service.sys.SysCustomerService;
import com.cos.service.sys.SysCustomerServiceImpl;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/28 10:51
 * @Classname: CommonConfig
 * @To change this template use File | Settings | File Templates.
 */
@Configuration
public class CommonConfig {

    /**
     * @return
     * @desc 系统客户
     */
    @Bean
    @ConditionalOnMissingBean(SysCustomerService.class)
    public SysCustomerService getSysCustomerService() {
        return new SysCustomerServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(ParamsSettingService.class)
    public ParamsSettingService getParamsSettingService() {
        return new ParamsSettingServiceImpl();
    }

}
