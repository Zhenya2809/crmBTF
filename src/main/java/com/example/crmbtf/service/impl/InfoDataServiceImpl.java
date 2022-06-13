package com.example.crmbtf.service.impl;

import com.example.crmbtf.model.InfoData;
import com.example.crmbtf.repository.InfoDataRepository;
import com.example.crmbtf.service.InfoDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@Configurable
public class InfoDataServiceImpl implements InfoDataService {
    private final InfoDataRepository infoDataRepository;
    ;

    public InfoDataServiceImpl(InfoDataRepository infoDataRepository) {
        this.infoDataRepository = infoDataRepository;
    }

    @Override
    public void setAdministratorToday(Long admin_id) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);

        Optional<InfoData> adminDate = infoDataRepository.findByData(format);
        if (adminDate.isPresent()) {
            InfoData infoData = adminDate.get();
            infoData.setAdministrator_id(admin_id);
            infoData.setData(format);
            infoDataRepository.save(infoData);
            log.info("IN setAdministratorToday for {} setup admin with id:{} ", format, admin_id);
        } else {
            InfoData infoData = new InfoData();
            infoData.setData(format);
            infoData.setAdministrator_id(admin_id);
            infoDataRepository.save(infoData);
            log.info("IN setAdministratorToday for {} setup admin with id:{} ", format, admin_id);
        }
    }

    @Override
    public Long getAdministratorId() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        Optional<InfoData> adminDate = infoDataRepository.findByData(format);
        if (adminDate.isPresent()) {
            log.info("IN getAdministratorId for date:{} administrator id:{}", format, adminDate.get().getAdministrator_id());
            return adminDate.get().getAdministrator_id();
        }
        log.info("IN getAdministratorId administrator not found for date:{}", format);
        throw new RuntimeException("administrator not found");

    }
}
