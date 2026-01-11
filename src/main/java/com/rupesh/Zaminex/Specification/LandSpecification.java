//package com.rupesh.Zaminex.Specification;
//
//import com.rupesh.Zaminex.DTOs.LandFilterDTO;
//import com.rupesh.Zaminex.Entity.Land;
//import jakarta.persistence.criteria.Predicate;
//import org.springframework.data.jpa.domain.Specification;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class LandSpecification {
//
//    public static Specification<Land> filter(LandFilterDTO dto ) {
//        return buildSort(dto, null);
//    }
//
//    public static Specification<Land> filter(LandFilterDTO dto  , Long ownerId) {
//        return buildSort(dto, ownerId);
//    }
//
//    private static Specification<Land> buildSort(LandFilterDTO dto, Long ownerId) {
//        return (root, query, cb) -> {
//
//            List<Predicate> predicates = new ArrayList<>();
//
//            predicates.add(cb.isTrue(root.get("active")));
//
//            if (dto.getState() != null)
//                predicates.add(cb.equal(root.get("state"), dto.getState()));
//
//            if (dto.getCity() != null)
//                predicates.add(cb.equal(root.get("city"), dto.getCity()));
//
//            if (dto.getPincode() != null)
//                predicates.add(cb.equal(root.get("pincode"), dto.getPincode()));
//
//            if (dto.getMinPrice() != null)
//                predicates.add(cb.greaterThanOrEqualTo(
//                        root.get("price"), dto.getMinPrice()));
//
//            if (dto.getMaxPrice() != null)
//                predicates.add(cb.lessThanOrEqualTo(
//                        root.get("price"), dto.getMaxPrice()));
//
//            if (dto.getMinArea() != null)
//                predicates.add(cb.greaterThanOrEqualTo(
//                        root.get("area"), dto.getMinArea()));
//
//            if (dto.getMaxArea() != null)
//                predicates.add(cb.lessThanOrEqualTo(
//                        root.get("area"), dto.getMaxArea()));
//
//            return cb.and(predicates.toArray(new Predicate[0]));
//        };
//    }
//}
