package com.example.ihsastable.data.repository;

import com.example.ihsastable.data.datasource.CoachRemoteTestDataSource;

public class CoachRepository {
    private CoachRemoteTestDataSource remoteDataSource;

    public CoachRepository(){
        this.remoteDataSource = new CoachRemoteTestDataSource();
    }
}
