package pl.gajewski.FamilyMemberApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gajewski.FamilyMemberApp.controller.constant.FamilyMemberAppConstant;
import pl.gajewski.FamilyMemberApp.dto.FamilyMember;
import pl.gajewski.FamilyMemberApp.service.FamilyMemberAppService;

@RestController
public class FamilyMemberAppController {

    private final FamilyMemberAppService familyMemberAppService;

    public FamilyMemberAppController(FamilyMemberAppService familyMemberAppService) {
        this.familyMemberAppService = familyMemberAppService;
    }

    @PostMapping(FamilyMemberAppConstant.CREATE_FAMILY_MEMBER)
    public ResponseEntity<HttpStatus> createFamilyMember(@RequestBody FamilyMember familyMember) {
        Boolean result = familyMemberAppService.createFamilyMember(familyMember);
        return Boolean.TRUE.equals(result) ? new ResponseEntity<>(HttpStatus.OK): new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(FamilyMemberAppConstant.SEARCH_FAMILY_MEMBER + "/{id}")
    public FamilyMember[] searchFamilyMember(@PathVariable(value = "id") Integer id) {
        return familyMemberAppService.searchFamilyMember(id);
    }
}
