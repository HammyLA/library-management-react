package com.lib.library_management_react.repository;

import org.springframework.data.repository.CrudRepository;

import com.lib.library_management_react.model.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {

}
