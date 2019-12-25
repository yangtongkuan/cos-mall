package com.cos.service.sys.impl;

import com.cos.dao.sys.SysCustomerRepository;
import com.cos.domain.bean.sys.SysCustomerInfo;
import com.cos.domain.utils.exception.UnKnowSysCustomerException;
import com.cos.service.sys.SysCustomerService;
import com.cos.service.sys.adapter.SysCustomerServiceAdapter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/21 8:54
 * @Classname: SysCustomerServiceDefaultImpl
 * @To change this template use File | Settings | File Templates.
 * @desc System customer default configuration
 */
public class SysCustomerServiceDefaultImpl extends SysCustomerServiceAdapter {
    @Override
    public void initLoad() {
        super.initLoad();
        System.out.println("exx ----");
    }
}
