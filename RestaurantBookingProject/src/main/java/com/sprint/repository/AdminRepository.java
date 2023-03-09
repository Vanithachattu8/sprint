package com.sprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{
	@Query("Select u from Admin u where u.emailId=:email_id and u.password=:password")
	public Admin validateAdmin(@Param(value="email_id")String emailId, @Param(value="password") String password);
}
