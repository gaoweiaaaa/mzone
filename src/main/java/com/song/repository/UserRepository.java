package com.song.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.song.entity.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select t from User t where t.name = :name")
    @Qualifier("sqlSessionFactory")
    User findByUserName(@Param("name") String name);
    @Query("select t from User t")
    @Qualifier("sqlSessionFactory")
    List<User> findAll();
}
