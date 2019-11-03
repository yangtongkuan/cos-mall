package com.cos.service.system;

import com.cos.dao.system.SysConstantSettingRepository;
import com.cos.domain.bean.SysConstantSettingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/2 16:07
 * @To change this template use File | Settings | File Templates.
 */
@Service
public class SysConstantSettingService {

    // 数据缓存  直接缓存在 map结构上 
    private static ConcurrentHashMap<String, SysConstantSettingInfo> dataMap = new ConcurrentHashMap<>();
    @Autowired
    private SysConstantSettingRepository sysConstantSettingRepository;

    @PostConstruct
    public void initLoad() {
        dataMap.clear();
        List<SysConstantSettingInfo> dataList = sysConstantSettingRepository.findByDelFlag(0);
        if (dataList != null && !dataList.isEmpty()) {
            Map<String, SysConstantSettingInfo> data_db = dataList.stream().collect(Collectors.toMap(bean -> getKey(bean.getSysCustomer(), bean.getParam()), Function.identity()));
            dataMap.putAll(data_db);
        }
    }

    public static String getKey(String sysCustomer, String param) {
        return sysCustomer + "_" + param;
    }

    public SysConstantSettingInfo getBySysCustomerAndParam(String sysCustomer, String param) {
        SysConstantSettingInfo info = dataMap.get(getKey(sysCustomer, param));
        if (info == null) {
            info = sysConstantSettingRepository.findBySysCustomerAndParamAndDelFlag(sysCustomer, param, 0);
            if (info != null) {
                dataMap.put(getKey(sysCustomer, param), info);
            }
        }
        return info;
    }
}
