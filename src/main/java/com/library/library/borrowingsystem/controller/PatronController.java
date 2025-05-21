package com.library.library.borrowingsystem.controller;

import com.library.library.borrowingsystem.dto.PatronDTO;
import com.library.library.borrowingsystem.entity.Patron;
import com.library.library.borrowingsystem.exception.ResourceNotFoundException;
import com.library.library.borrowingsystem.repository.PatronRepository;
import com.library.library.borrowingsystem.service.MapperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
public class PatronController {
    private final PatronRepository patronRepository;
    private final MapperService mapperService;

    @GetMapping
    public ResponseEntity<List<PatronDTO>> getAllPatrons() {
        List<Patron> patrons = patronRepository.findAll();
        return ResponseEntity.ok(mapperService.toPatronDTOs(patrons));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDTO> getPatronById(@PathVariable Long id) {
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron", "id", id));
        return ResponseEntity.ok(mapperService.toPatronDTO(patron));
    }

    @PostMapping
    public ResponseEntity<PatronDTO> createPatron(@Valid @RequestBody PatronDTO patronDTO) {
        Patron patron = mapperService.toPatron(patronDTO);
        Patron savedPatron = patronRepository.save(patron);
        return new ResponseEntity<>(mapperService.toPatronDTO(savedPatron), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronDTO> updatePatron(@PathVariable Long id, @Valid @RequestBody PatronDTO patronDTO) {
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron", "id", id));

        patron.setName(patronDTO.getName());
        patron.setEmail(patronDTO.getEmail());
        patron.setPhone(patronDTO.getPhone());
        patron.setAddress(patronDTO.getAddress());

        Patron updatedPatron = patronRepository.save(patron);
        return ResponseEntity.ok(mapperService.toPatronDTO(updatedPatron));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatron(@PathVariable Long id) {
        return patronRepository.findById(id).map(patron -> {
            patronRepository.delete(patron);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Patron", "id", id));
    }
}