package com.cos.service.sys;

import com.cos.dao.sys.SysCustomerRepository;
import com.cos.domain.bean.sys.SysCustomerInfo;
import com.cos.domain.utils.exception.UnKnowSysCustomerException;
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
 * @Classname: SysCustomerServiceImpl
 * @To change this template use File | Settings | File Templates.
 */
@Service
@ConditionalOnMissingBean(SysCustomerService.class)
public class SysCustomerServiceImpl implements SysCustomerService {

    // 客户端标识
    private static ConcurrentHashMap<String, SysCustomerInfo> dataMap = new ConcurrentHashMap<>();

    private final static String EmptySysCustomer = "the sysCustomer is not allowed empty";
    private final static String UnSearchSysCustomer = "the customer is not find in system";

    @Autowired
    private SysCustomerRepository sysCustomerRepository;

    @PostConstruct
    public void initLoad() {
        dataMap.clear();
        List<SysCustomerInfo> customerInfoList = sysCustomerRepository.findByDelFlag(0);
        if (customerInfoList != null) {
            Map<String, SysCustomerInfo> dataDbMap = customerInfoList.stream().filter(a -> a.getIdentify() != null && StringUtils.isNotEmpty(a.getIdentify())).
                    collect(Collectors.toMap(SysCustomerInfo::getIdentify, info -> info, (a1, a2) -> a1));
            dataMap.putAll(dataDbMap);
        }
    }

    @Override
    public SysCustomerInfo get(String sysCustomer) {
        if (StringUtils.isEmpty(sysCustomer)) {
            return null;
        }
        if (dataMap.containsKey(sysCustomer)) {
            return dataMap.get(sysCustomer);
        }
        SysCustomerInfo sysCustomerInfo = sysCustomerRepository.findByIdentifyAndDelFlag(sysCustomer, 0);
        if (sysCustomerInfo != null) {
            dataMap.put(sysCustomerInfo.getIdentify(), sysCustomerInfo);
        }
        return sysCustomerInfo;
    }

    @Override
    public SysCustomerInfo getValidSysCustomerInfo(String sysCustomer) throws UnKnowSysCustomerException {
        if (StringUtils.isEmpty(sysCustomer)) {
            throw new UnKnowSysCustomerException(EmptySysCustomer);
        }
        SysCustomerInfo customerInfo = this.get(sysCustomer);
        if (customerInfo == null) {
            throw new UnKnowSysCustomerException(UnSearchSysCustomer);
        }
        return customerInfo;
    }
}
