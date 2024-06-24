package com.andrew.springsecuritybasic.repository;

import com.andrew.springsecuritybasic.mdoel.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {


}
