package pl.gajewski.FamilyMemberApp.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.gajewski.FamilyMemberApp.dto.FamilyMember;


@Service
@Log4j2
public class FamilyMemberAppService {
    private static final String URI_CREATE_MEMBER_FAMILY = "http://familyapp-family-database-1:8022/db/createMemberFamily";
    private static final String URI_SEARCH_MEMBER_FAMILY = "http://familyapp-family-database-1:8022/db/searchMemberFamily/";

    private final RestTemplate restTemplate;

    @Autowired
    public FamilyMemberAppService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Boolean createFamilyMember(FamilyMember familyMember) {
        try {
            restTemplate.postForObject(URI_CREATE_MEMBER_FAMILY, familyMember, FamilyMember.class);
            log.info("Odpytanie serwisu: " + URI_CREATE_MEMBER_FAMILY);
            return true;
        } catch (Exception e) {
            log.info("Wykonanie URI: " + URI_CREATE_MEMBER_FAMILY + " nie powiodło się " + e.getMessage());
            return false;
        }
    }

    public FamilyMember[] searchFamilyMember(Integer id) {
        FamilyMember[] result = restTemplate.getForObject(URI_SEARCH_MEMBER_FAMILY + id.toString(), FamilyMember[].class);
        log.info("Pobrano dane rodziny o id: " + id.toString());
        return result;
    }
}
