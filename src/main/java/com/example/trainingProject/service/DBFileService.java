package com.example.trainingProject.service;

import com.example.trainingProject.entity.DBFile;

public interface DBFileService {
    DBFile getFileByProductId(Long fileId);
}
