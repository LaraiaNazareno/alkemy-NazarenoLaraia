package com.example.alktest.controller;


import com.example.alktest.dto.IconBasicDTO;
import com.example.alktest.dto.IconDTO;
import com.example.alktest.service.IconService;
import org.apache.coyote.Response;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("icons")
public class IconController {


    private IconService iconService;

    @Autowired
    public IconController(IconService iconService) {
        this.iconService = iconService;
    }

    /*
    @GetMapping
    public ResponseEntity<List<IconBasicDTO>> getALL(){
        List<IconBasicDTO>  icons =this.iconService.getAll();
        return ResponseEntity.ok().body(icons);

    }*/


    @GetMapping("/{id}")
    public ResponseEntity<IconDTO>getDetailsById(@PathVariable Long id){
       IconDTO icon = this.iconService.getDetailsById(id);
        return ResponseEntity.ok(icon);

    }

    @PostMapping
    public ResponseEntity<IconDTO> save(@RequestBody IconDTO icon){
        IconDTO result = this.iconService.save(icon);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @PutMapping ("/{id}/pais/{idPais}")
    public ResponseEntity<Void> addPais(@PathVariable Long id, @PathVariable Long idPais) {
        this.iconService.addPais(id, idPais);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @DeleteMapping("/{id}/pais/{idPais}")
    public ResponseEntity<Void> removePais(@PathVariable Long id, @PathVariable Long idPais) {
        this.iconService.removePais(id, idPais);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        this.iconService.delete(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping
    public ResponseEntity<List<IconDTO>> getDetailsByFilters(

            @RequestParam (required = false) String name,
            @RequestParam (required = false) String date,
            @RequestParam(required = false) Set<Long> cities,
            @RequestParam(required = false,defaultValue = "ASC") String order

    ){
        List<IconDTO> icons = this.iconService.getByFilters(name,date,cities,order);
        return ResponseEntity.ok(icons);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IconDTO> update(@PathVariable Long id, @RequestBody IconDTO icon) {
        IconDTO result = this.iconService.update(id, icon);
        return ResponseEntity.ok().body(result);
    }
}