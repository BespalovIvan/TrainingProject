package com.example.trainingProject.repository;

import com.example.trainingProject.entity.DBFile;

import java.util.List;

public interface DBFileRepo {
    DBFile getFileByProductId(Long productId);
}
