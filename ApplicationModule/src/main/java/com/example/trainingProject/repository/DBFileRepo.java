package com.example.trainingProject.repository;

import com.example.trainingProject.entity.DBFile;

public interface DBFileRepo {
    DBFile getFileByProductId(Long productId);
}
