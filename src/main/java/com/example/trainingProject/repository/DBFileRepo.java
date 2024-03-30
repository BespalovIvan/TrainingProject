package com.example.trainingProject.repository;

import com.example.trainingProject.entity.DBFile;

import java.util.List;

public interface DBFileRepo {
    byte[] getFileByProductId(Long productId);
    List<DBFile> getAllFiles();
}
