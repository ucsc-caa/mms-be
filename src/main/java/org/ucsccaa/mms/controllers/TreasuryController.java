package org.ucsccaa.mms.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ucsccaa.mms.domains.Treasury;
import org.ucsccaa.mms.models.ServiceResponse;
import org.ucsccaa.mms.models.Status;
import org.ucsccaa.mms.services.TreasuryService;

@RestController
@RequestMapping("/treasury")
public class TreasuryController {
    @Autowired
    private TreasuryService treasuryService;

    @PostMapping
    public ServiceResponse<URI> addStaff(@RequestBody Treasury treasury, HttpServletRequest req) throws URISyntaxException{
        try {
            Long id = treasuryService.addTreasury(treasury);
            return new ServiceResponse<>(new URI(req.getRequestURI() + "/" + id));
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
    }

    @PutMapping
    public ServiceResponse<Treasury> updateTreasury(@RequestBody Treasury treasury) {
        try {
            Optional<Treasury> updatedTreasury = treasuryService.updateTreasury(treasury);
            if (updatedTreasury.isPresent()) {
                return new ServiceResponse<>(updatedTreasury.get());
            } else {
                return new ServiceResponse<>(Status.NOT_FOUND, "Treasury is not found");
            }
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ServiceResponse<Treasury> getTreasury(@PathVariable Long id) {
        try {
            Optional<Treasury> treasury = treasuryService.getTreasury(id);
            if (treasury.isPresent()) {
                return new ServiceResponse<>(treasury.get());
            } else {
                return new ServiceResponse<>(Status.NOT_FOUND, "Treasury is not found");
            }
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ServiceResponse<Object> deleteTreasury(@PathVariable Long id) {
        try {
            Boolean result = treasuryService.deleteTreasury(id);
            if (result) {
                return new ServiceResponse<>(Status.SUCCESS, "Successfully deleted");
            } else {
                return new ServiceResponse<>(Status.NOT_FOUND, "Treasury is not found");
            }
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
    }

    @GetMapping("/staff/{staff_id}")
    public ServiceResponse<List<Treasury>> getTreasuriesByStaff(@PathVariable("staff_id") Long staffId) {
        try {
            List<Treasury> treasuries = treasuryService.getTreasuriesByStaff(staffId);
            return new ServiceResponse<>(treasuries);
        } catch (Exception e) {
            return new ServiceResponse<>(Status.ERROR, e.getMessage());
        }
    }

    @GetMapping
    public ServiceResponse<List<Treasury>> getAllTreasuries() {
        List<Treasury> treasuries = treasuryService.getAll();
        return new ServiceResponse<>(treasuries);
    }
}
