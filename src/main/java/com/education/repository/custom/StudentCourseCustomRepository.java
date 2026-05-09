package com.education.repository.custom;

import com.education.dto.StudentCourseFullInfoDTO;
import com.education.entity.StudentCourse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Repository
public class StudentCourseCustomRepository {

    @Autowired
    private EntityManager entityManager;

    public Page<StudentCourse> filter(StudentCourseFullInfoDTO dto, int page, Integer size) {
        StringBuilder select = new StringBuilder("SELECT sc FROM StudentCourse sc ");
        StringBuilder count = new StringBuilder("SELECT COUNT(sc) FROM StudentCourse sc ");
        StringBuilder filter = new StringBuilder(" WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if(dto.getStudentId() != null){
            filter.append(" AND sc.studentId = :sid ");
            params.put("sid", dto.getStudentId());
        }
        if(dto.getCourseId() != null){
            filter.append(" AND sc.courseId = :cid");
            params.put("sid", dto.getCourseId());
        }
        if(dto.getMarkFrom() != null && dto.getMarkTo() != null){
            filter.append(" AND sc.mark >= :markFrom AND sc.mark <= :markTo ");
            params.put("markFrom", dto.getMarkFrom());
            params.put("markTo", dto.getMarkTo());
        }
        else if(dto.getMarkFrom() != null){
            filter.append(" AND sc.mark >= :markFrom ");
            params.put("markFrom", dto.getMarkFrom());
        }
        else if(dto.getMarkTo() != null){
            filter.append(" AND sc.mark <= :markTo ");
            params.put("markTo", dto.getMarkTo());
        }
        if(dto.getDateFrom() != null && dto.getDateTo() != null){
            filter.append(" AND sc.createdDate >= :fromDate AND sc.createdDate <= :toDate ");
            params.put("fromDate", dto.getDateFrom().atStartOfDay());
            params.put("toDate", dto.getDateTo().atTime(LocalTime.MAX));
        }
        else if(dto.getDateFrom() != null){
            filter.append(" AND sc.createdDate >= :fromDate ");
            params.put("fromDate", dto.getDateFrom().atStartOfDay());
        }
        else if(dto.getDateTo() != null){
            filter.append(" AND s.createdDate <= :toDate ");
            params.put("toDate", dto.getDateTo().atTime(LocalTime.MAX));
        }
        if(dto.getStudentName() != null){
            filter.append(" AND LOWER(sc.student.name) LIKE :sName");
            params.put("sName", "%" + dto.getStudentName().toLowerCase() + "%");
        }
        if(dto.getCourseName() != null){
            filter.append(" AND LOWER(sc.course.name) LIKE :cName");
            params.put("cName", "%" + dto.getCourseName().toLowerCase() + "%");
        }

        select.append(filter);
        count.append(filter);
        Query selectQuery = entityManager.createQuery(select.toString());
        selectQuery.setFirstResult(page*size);
        selectQuery.setMaxResults(size);
        params.forEach(selectQuery::setParameter);
        List<StudentCourse> resultList = selectQuery.getResultList();

        Query countQuery = entityManager.createQuery(count.toString());
        params.forEach(countQuery::setParameter);

        return new PageImpl<>(resultList, PageRequest.of(page, size), (Long) countQuery.getSingleResult());
    }
}
