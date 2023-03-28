package com.example.ihsastable.data.repository;

import com.example.ihsastable.data.datasource.EventAdminRemoteTestDataSource;

public class EventAdminRepository {

    private final EventAdminRemoteTestDataSource remoteDataSource;

    public EventAdminRepository(){
        this.remoteDataSource = new EventAdminRemoteTestDataSource();
    }
}
