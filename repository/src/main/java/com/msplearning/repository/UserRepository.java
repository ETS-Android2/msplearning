package com.msplearning.repository;

import com.msplearning.entity.User;
import com.msplearning.repository.jpa.UserRepositoryJpa;

/**
 * Interface of {@link UserRepositoryJpa}.
 * 
 * @author Venilton Falvo Junior (veniltonjr)
 */
public interface UserRepository {
	
	boolean authenticate(String username, String password);

	User findByUsername(String username);
}
