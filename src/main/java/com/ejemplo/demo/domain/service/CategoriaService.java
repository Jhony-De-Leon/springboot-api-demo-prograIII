package com.ejemplo.demo.domain.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ejemplo.demo.domain.model.Categoria;
import com.ejemplo.demo.domain.repository.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Categoria obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    public Categoria crear(Categoria categoria) {
        return repository.save(categoria);
    }

    public Categoria actualizar(Long id, Categoria data) {
        Categoria cat = obtener(id);
        cat.setNombre(data.getNombre());
        cat.setDescripcion(data.getDescripcion());
        return repository.save(cat);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}