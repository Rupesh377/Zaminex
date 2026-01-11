package com.rupesh.Zaminex.Service;

import com.rupesh.Zaminex.DTOs.CreateLandDTO;
import com.rupesh.Zaminex.DTOs.LandResponseDTO;
import com.rupesh.Zaminex.DTOs.LandResponseToUser;
import com.rupesh.Zaminex.DTOs.UpdateLandDTO;
import com.rupesh.Zaminex.Entity.Land;
import com.rupesh.Zaminex.Entity.User;
import com.rupesh.Zaminex.Repository.AuthRepository;
import com.rupesh.Zaminex.Repository.LandRepository;
import com.rupesh.Zaminex.Type.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class LandService {

    private final AuthRepository authRepository;
    private final LandRepository landRepository;

    public LandService(AuthRepository authRepository, LandRepository landRepository) {
        this.authRepository = authRepository;
        this.landRepository = landRepository;
    }

    public  LandResponseDTO createLand(CreateLandDTO createLandDTO , String phone){

        User seller = authRepository.findByPhone(phone).orElseThrow(()-> new RuntimeException("User not found"));

        if(seller.getRole()!= Role.SELLER)
            throw new AccessDeniedException("Access Denied!  Only seller can post land");

        Land land=Land.builder()
                .seller(seller)
                .description(createLandDTO.getDescription())
                .price(createLandDTO.getPrice())
                .area(createLandDTO.getArea())
                .state(createLandDTO.getState())
                .city(createLandDTO.getCity())
                .locality(createLandDTO.getLocality())
                .pincode(createLandDTO.getPincode())
                .active(true)
                .contact(createLandDTO.getContact())
                .build();

        landRepository.save(land);

        return landToDTO(land);
    }
    public LandResponseDTO landToDTO(Land land)
    {
        return new LandResponseDTO(
                land.getId(),
                land.getDescription(),
                land.getPrice(),
                land.getArea(),
                land.getCity(),
                land.getState(),
                land.getPincode(),
                land.getContact(),
        land.getSeller().getName());
    }


    public  List<LandResponseToUser> getAllLand()
    {
        return landRepository.findAllActive().stream().map(this::landToDTOForUser).toList();
    }
    public LandResponseToUser landToDTOForUser(Land land)
    {
        return new LandResponseToUser(
                land.getDescription(),
                land.getPrice(),
                land.getArea(),
                land.getCity(),
                land.getState(),
                land.getPincode(),
                land.getContact(),
                land.getSeller().getName());
    }


    public List<LandResponseDTO> myLands(Long sellerId) {
        return landRepository.findBySeller_IdAndActiveTrue(sellerId)
                .stream()
                .map(this::landToDTO)
                .toList();
    }


    public LandResponseDTO updateLand(Long id, Long sellerId, UpdateLandDTO dto) {

        Land land = landRepository.findById(id).orElseThrow(() -> new RuntimeException("Land not found"));

        if (!land.getSeller().getId().equals(sellerId)) {
            throw new AccessDeniedException("Not you land");
        }

        if (!land.isActive()) {
            throw new RuntimeException("Land is inactive");
        }

        if (dto.getState() != null) land.setState(dto.getState());
        if (dto.getCity() != null) land.setCity(dto.getCity());
        if (dto.getPrice() != null) land.setPrice(dto.getPrice());
        if (dto.getArea() != null) land.setArea(dto.getArea());
        if (dto.getLocality() != null) land.setLocality(dto.getLocality());
        if (dto.getDescription() != null) land.setDescription(dto.getDescription());
        if (dto.getPincode() != null) land.setPincode(dto.getPincode());
        if (dto.getContact() != null) land.setContact(dto.getContact());

        landRepository.save(land);

        return landToDTO(land);
    }

    @Transactional
    public String deactivateLand(Long id, Long sellerId) {
        Land land = landRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Land not found"));

        if (!land.getSeller().getId().equals(sellerId)) {
            throw new AccessDeniedException("Not your land");
        }

        land.setActive(false);
        landRepository.save(land);

        return "Land deactivated successfully";
    }


//    public List<LandResponseDTO> search(LandFilterDTO dto ) {
//
//        Specification<Land> spec = LandSpecification.filter(dto);
//
//        Sort sort = Sort.unsorted();
//
//        Set<String> allowed = Set.of("price", "area" ,"createdAt");
//
//        if (dto.getSortBy() != null && !allowed.contains(dto.getSortBy())) {
//            throw new RuntimeException("Invalid sort field");
//        }
//        if (dto.getSortBy() != null) {
//
//            Sort.Direction direction = "desc".equalsIgnoreCase(dto.getDirection())
//                    ? Sort.Direction.DESC : Sort.Direction.ASC;
//
//            sort = Sort.by(direction, dto.getSortBy());
//        }
//
//        return landRepository.findAll(spec)
//                .stream()
//                .map(this::landToDTO)
//                .toList();
//    }
//
//    public List<LandResponseDTO> myLands(Long sellerId, LandFilterDTO dto
//    ) {
//        Specification<Land> spec = LandSpecification.filter(dto, sellerId);
//
//        Sort sort = buildSort(dto);
//
//        return landRepository.findAll(spec, sort)
//                .stream()
//                .map(this::landToDTO)
//                .toList();
//    }


}
