package com.education.repository.custom;

import com.education.dto.CourseFullInfoDTO;
import com.education.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CourseCustomRepository {

    @Autowired
    private EntityManager entityManager;

    public PageImpl<Course> filter(CourseFullInfoDTO dto, Integer page, Integer size) {
        StringBuilder select = new StringBuilder("SELECT c FROM Course c ");
        StringBuilder count = new StringBuilder("SELECT COUNT(c) FROM Course c ");
        Map<String, Object> params = new HashMap();
        StringBuilder filter = new StringBuilder(" WHERE 1=1 ");

        if(dto.getId() != null){
            filter.append(" AND c.id = :id ");
            params.put("id", dto.getId());
;        }
        if(dto.getName() != null){
            filter.append(" AND LOWER(c.name) LIKE :name ");
            params.put("name", "%" + dto.getName().toLowerCase() + "%");
        }
        if(dto.getPrice() != null){
            filter.append(" AND c.price = :price ");
            params.put("price", dto.getPrice());
        }
        if(dto.getDuration() != null){
            filter.append(" AND c.duration = :duration ");
            params.put("duration", dto.getDuration());
        }
        if(dto.getFrom() != null && dto.getTo() != null){
            filter.append(" AND c.createdDate >= :fromDate AND c.createdDate <= :toDate ");
            params.put("fromDate", dto.getFrom().atStartOfDay());
            params.put("toDate", dto.getTo().atTime(LocalTime.MAX));
        }
        else if(dto.getFrom() != null){
            filter.append(" AND c.createdDate >= :fromDate ");
            params.put("fromDate", dto.getFrom().atStartOfDay());
        }
        else if(dto.getTo() != null){
            filter.append(" AND c.createdDate <= :toDate ");
            params.put("toDate", dto.getTo().atTime(LocalTime.MAX));
        }

        select.append(filter).append(" ORDER BY c.createdDate DESC");
        count.append(filter);

        Query selectQuery = entityManager.createQuery(select.toString());
        selectQuery.setFirstResult(page*size);
        selectQuery.setMaxResults(size);
        params.forEach(selectQuery::setParameter);

        Query countQuery = entityManager.createQuery(count.toString());
        params.forEach(countQuery::setParameter);

        List<Course> courses = selectQuery.getResultList();
        Long totalCourse = (Long) countQuery.getSingleResult();

        return new PageImpl<>(courses, PageRequest.of(page, size), totalCourse);
    }
}
