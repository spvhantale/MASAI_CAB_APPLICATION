package com.swapnil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapnil.model.CurrentUserSession;

public interface SessionDAO extends JpaRepository<CurrentUserSession, Integer>{

	public Optional<CurrentUserSession> findByUuid(String uuid);
	
	public Optional<CurrentUserSession> findByUserName(String userName);
}
