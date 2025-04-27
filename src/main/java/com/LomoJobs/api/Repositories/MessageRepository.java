package com.LomoJobs.api.Repositories;

import com.LomoJobs.api.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}
