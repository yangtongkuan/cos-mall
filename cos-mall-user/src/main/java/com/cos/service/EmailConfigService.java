package com.cos.service;

import com.cos.dao.EmailConfigRepository;
import com.cos.domain.bean.EmailConfigInfo;
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
 * @Date: @Date 2019/11/28 11:46
 * @Classname: EmailConfigService
 * @To change this template use File | Settings | File Templates.
 */
@Service
public class EmailConfigService {

//    private static ConcurrentHashMap<String, EmailConfigInfo> dataMap = new ConcurrentHashMap<>();

    @Autowired
    private EmailConfigRepository emailConfigRepository;

//    @PostConstruct
//    public void init() {
//        dataMap.clear();
//        List<EmailConfigInfo> dataList = emailConfigRepository.findByDelFlag(0);
//        if (dataList != null && !dataList.isEmpty()) {
//            Map<String, EmailConfigInfo> infoMap = dataList.stream().collect(Collectors.toMap(EmailConfigInfo::getSysCustomer, Function.identity(), (a1, a2) -> a2));
//            dataMap.putAll(infoMap);
//        }
//    }

    public EmailConfigInfo getBySysCustomer(String sysCustomer) {
        return emailConfigRepository.findBySysCustomerAndDelFlag(sysCustomer, 0);
    }


}
