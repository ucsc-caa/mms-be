package org.ucsccaa.mms.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ucsccaa.mms.domains.OPT;
import org.ucsccaa.mms.services.OPTService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Api(tags = "OPT RESTFUL API")
@RestController
@RequestMapping(value = "/OPTs", produces = MediaType.APPLICATION_JSON_VALUE)
public class OPTController {
    @Autowired
    private OPTService optService;

    @ApiOperation("create new OPT")
    @PostMapping
    public ResponseEntity<OPT> addOPT(@RequestBody OPT opt, HttpServletRequest req) throws URISyntaxException {
        try {
            Long id = optService.createOPT(opt);
            return ResponseEntity.created(new URI(req.getRequestURL() + "/" + id)).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiOperation("get OPT by ID")
    @GetMapping("/{id}")
    public ResponseEntity<OPT> getUserByID(@PathVariable Long id) {
        Optional<OPT> opt = optService.findOPTByID(id);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation("update OPT by ID")
    @PutMapping("/{id}")
    public ResponseEntity<OPT> updateUserByID(@PathVariable Long id, @RequestBody OPT opt) {
        opt.setId(id);
        Optional<OPT> optionalOPT = optService.updateOPTByID(opt);
        return optionalOPT.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation("delete OPT by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserByID(@PathVariable Long id) {
        try {
            optService.deleteOPT(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("list all OPTs")
    @GetMapping
    public ResponseEntity<Object> listAll(){
        List<OPT> OPTAll = optService.listAll();
        return ResponseEntity.ok().body(OPTAll);
    }
}
