package com.cos.service.system;

import com.cos.dao.system.SysConstantSettingRepository;
import com.cos.dao.system.SysCustomerRepository;
import com.cos.domain.bean.SysCustomerInfo;
import com.cos.domain.util.UnKnowSysCustomerException;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Date: @Date 2019/11/2 16:54
 * @To change this template use File | Settings | File Templates.
 */
@Service
public class SysCustomerService {

    private final static String EmptySysCustomer = "客户端标识不能为空";
    private final static String UnSearchSysCustomer = "未查询到客户端信息";

    private static ConcurrentHashMap<String, SysCustomerInfo> dataMap = new ConcurrentHashMap<>();
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

    // 获取一个客户端标识
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

    // 获取客户端异常
    public SysCustomerInfo getValidSysCustomerInfo(String sysCustomer) throws UnKnowSysCustomerException {
        if (StringUtils.isEmpty(sysCustomer)) {
            throw new UnKnowSysCustomerException(EmptySysCustomer);
        }
        SysCustomerInfo sysCustomerInfo = this.get(sysCustomer);
        if (sysCustomerInfo == null) {
            throw new UnKnowSysCustomerException(UnSearchSysCustomer);
        }
        return sysCustomerInfo;
    }
}
