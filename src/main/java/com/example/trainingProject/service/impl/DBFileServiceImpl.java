package com.example.trainingProject.service.impl;

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
    public byte[] getFileById(Long fileId) {

        return dbFileRepo.getFileByProductId(fileId);
    }
}
