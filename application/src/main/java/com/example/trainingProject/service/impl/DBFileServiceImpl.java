package com.example.trainingProject.service.impl;

import com.example.trainingProject.entity.DBFile;
import com.example.trainingProject.repository.DBFileRepo;
import com.example.trainingProject.service.DBFileService;
import org.springframework.stereotype.Service;

@Service
public class DBFileServiceImpl implements DBFileService {
    private final DBFileRepo dbFileRepo;

    public DBFileServiceImpl(DBFileRepo dbFileRepo) {
        this.dbFileRepo = dbFileRepo;
    }

    @Override
    public DBFile getFileByProductId(Long productId) {

        return dbFileRepo.getFileByProductId(productId);
    }
}
