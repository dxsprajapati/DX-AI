package com.dxprajapati;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatLogRepository extends MongoRepository<ChatLog, String> {
}