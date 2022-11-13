package kr.co.manager.exception.rdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.manager.domain.jpa.AlarmDictionary;

@Repository
public interface AlarmDictionaryRepository extends JpaRepository<AlarmDictionary, Integer>{

}
