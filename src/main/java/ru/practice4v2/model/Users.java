package ru.practice4v2.model;

import org.springframework.stereotype.Repository;


import org.springframework.data.repository.CrudRepository;

@Repository
public interface Users extends CrudRepository<SQLUsers, Long> { SQLUsers findByUserName(String userName);}
