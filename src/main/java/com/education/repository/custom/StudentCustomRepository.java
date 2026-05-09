package com.education.repository.custom;

import com.education.dto.StudentFullInfDTO;
import com.education.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentCustomRepository {

    @Autowired
    private EntityManager entityManager;

    public Page<Student> filter(StudentFullInfDTO dto, Integer page, Integer size) {
        StringBuilder select =new StringBuilder("SELECT s FROM Student s ");
        StringBuilder count =new StringBuilder("SELECT COUNT(s) FROM Student s ");
        Map<String, Object> params = new HashMap<>();

        StringBuilder filter = new StringBuilder(" WHERE 1=1 ");
        if(dto.getId() != null){
            filter.append(" AND s.id = :id ");
            params.put("id", dto.getId());
        }
        if(dto.getName() != null){
            filter.append(" AND LOWER(s.name) LIKE :name ");
            params.put("name", "%" + dto.getName().toLowerCase() + "%");
        }
        if(dto.getSurname() != null){
            filter.append(" AND LOWER(s.surname) LIKE :surname ");
            params.put("surname", "%" + dto.getSurname().toLowerCase() + "%");
        }
        if(dto.getLevel() != null){
            filter.append(" AND s.level =:level ");
            params.put("level", dto.getLevel());
        }
        if(dto.getAge() != null){
            filter.append(" AND s.age =:age ");
            params.put("age", dto.getAge());
        }
        if(dto.getGender() != null){
            filter.append(" AND s.gender =:gender ");
            params.put("gender", dto.getGender());
        }
        if(dto.getFrom() != null && dto.getTo() != null){
            filter.append(" AND s.createdDate >= :fromDate AND s.createdDate <= :toDate ");
            params.put("fromDate", dto.getFrom().atStartOfDay());
            params.put("toDate", dto.getTo().atTime(LocalTime.MAX));
        }
        else if(dto.getFrom() != null){
            filter.append(" AND s.createdDate >= :fromDate ");
            params.put("fromDate", dto.getFrom().atStartOfDay());
        }
        else if(dto.getTo() != null){
            filter.append(" AND s.createdDate <= :toDate ");
            params.put("toDate", dto.getTo().atTime(LocalTime.MAX));
        }

        select.append(filter);
        count.append(filter);

        Query selectQuery = entityManager.createQuery(select.toString());
        selectQuery.setFirstResult(page*size);
        selectQuery.setMaxResults(size);
        for(Map.Entry<String, Object> entry :params.entrySet()){
            selectQuery.setParameter(entry.getKey(), entry.getValue());
        }
        List<Student> students = selectQuery.getResultList();

        Query countQuery = entityManager.createQuery(count.toString());
        for(Map.Entry<String, Object> entry :params.entrySet()){
            countQuery.setParameter(entry.getKey(), entry.getValue());
        }
        Long totalElements = (Long) countQuery.getSingleResult();

        return new PageImpl<>(students, PageRequest.of(page, size), totalElements);
    }
}
