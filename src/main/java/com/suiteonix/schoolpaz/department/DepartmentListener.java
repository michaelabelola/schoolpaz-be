package com.suiteonix.schoolpaz.department;

import com.suiteonix.schoolpaz.department.internal.Department;
import com.suiteonix.schoolpaz.department.internal.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DepartmentListener {

    private final DepartmentRepository departmentRepository;

    @EventListener
    public void handle(ContextRefreshedEvent event) {
        if (departmentRepository.existsById_Id("3"))
            return;
        Department department = new Department();
        department.setId(new DepartmentId("3", "eid"));
        Department savedDept = departmentRepository.saveAndFlush(department);
        log.info("Created department {}", savedDept);
    }

}
