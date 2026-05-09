package com.school.management.School.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.management.School.dto.FeeStructureRequest;
import com.school.management.School.entity.FeeStructure;
import com.school.management.School.entity.FeeStructureItem;
import com.school.management.School.repository.FeeStructureRepository;

@Service
@Transactional
public class FeeStructureService {
	
	@Autowired
    private FeeStructureRepository feeStructureRepository;

    public String createFeeStructure(FeeStructureRequest request) {

        // 🔹 1. Basic Validation
        if (request.getSchoolId() == null)
            throw new RuntimeException("School ID is required");

        if (request.getClassName() == null || request.getClassName().isEmpty())
            throw new RuntimeException("Class name is required");

        if (request.getAcademicYear() == null || request.getAcademicYear().isEmpty())
            throw new RuntimeException("Academic year is required");

        if (request.getItems() == null || request.getItems().isEmpty())
            throw new RuntimeException("At least one fee component is required");

        // 🔹 2. Prevent Duplicate Structure
        if (feeStructureRepository
                .findBySchoolIdAndClassNameAndAcademicYear(
                        request.getSchoolId(),
                        request.getClassName(),
                        request.getAcademicYear()
                ).isPresent()) {

            throw new RuntimeException("Fee structure already exists for this class & year");
        }

        // 🔹 3. Create Main Entity
        FeeStructure fs = new FeeStructure();
        fs.setSchoolId(request.getSchoolId());
        fs.setClassName(request.getClassName());
        fs.setAcademicYear(request.getAcademicYear());
        fs.setFrequency(FeeStructure.Frequency.valueOf(request.getFrequency()));

        double total = 0;
        List<FeeStructureItem> itemList = new ArrayList<>();

        // 🔹 4. Add Items
        for (FeeStructureRequest.FeeItem item : request.getItems()) {

            if (item.getAmount() == null || item.getAmount() <= 0) {
                throw new RuntimeException("Invalid amount for component: " + item.getComponentName());
            }

            FeeStructureItem entity = new FeeStructureItem();
            entity.setFeeStructure(fs); // ✅ correct relation
            entity.setSchoolId(request.getSchoolId());
            entity.setComponentName(item.getComponentName());
            entity.setAmount(item.getAmount());

            total += item.getAmount();
            itemList.add(entity);
        }

        // 🔹 5. Set total & items
        fs.setTotalAmount(total);
        fs.setItems(itemList);

        // 🔹 6. Save (Cascade saves items automatically)
        feeStructureRepository.save(fs);

        return "Fee structure created successfully";
    }

}
