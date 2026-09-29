package com.salesianos.dam.apimonumentos.apimonumentos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monument")
public class MonumentController {

    private final MonumentRepository monumentRepository;

    @PostMapping
    public ResponseEntity<Monument> addMonument (@RequestBody Monument monument) {

        if (StringUtils.hasText(monument.getName())) {

            return ResponseEntity.status(201)
                    .body(monumentRepository.save(monument));

        }

        return ResponseEntity.badRequest().build();

    }

    @GetMapping
    public ResponseEntity<List<Monument>> getAllMonuments() {

        List<Monument> result = monumentRepository.findAll();

        if (result.isEmpty()) {

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(result);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Monument> getMonumentById(@PathVariable Long id) {

        return ResponseEntity.of(monumentRepository.findById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Monument> updateMonument(
            @PathVariable Long id,
            @RequestBody Monument monument) {

        return monumentRepository.findById(id)
                .map(m -> {
                    m.setCountryCode(monument.getCountryCode());
                    m.setCountryName(monument.getCountryName());
                    m.setCityName(monument.getCityName());
                    m.setLatitude(monument.getLatitude());
                    m.setLongitude(monument.getLongitude());
                    m.setName(monument.getName());
                    m.setDesc(monument.getDesc());
                    m.setPhotoURL(monument.getPhotoURL());
                    return ResponseEntity.ok(monumentRepository.save(m));
                })
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonument(@PathVariable Long id) {

        monumentRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}
