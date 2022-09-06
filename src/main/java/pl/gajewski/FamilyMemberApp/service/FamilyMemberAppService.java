package pl.gajewski.FamilyMemberApp.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.gajewski.FamilyMemberApp.dto.FamilyMember;

import java.net.URISyntaxException;

@Service
@Log4j2
public class FamilyMemberAppService {
    @Value("${service.uriCreateMemberFamily}")
    private String uriCreateMemberFamily;

    @Value("${service.uriSearchMemberFamily}")
    private String uriSearchMemberFamily;

    private final RestTemplate restTemplate;

    @Autowired
    public FamilyMemberAppService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Boolean createFamilyMember(FamilyMember familyMember) {
        try {
            restTemplate.postForObject(uriCreateMemberFamily, familyMember, FamilyMember.class);
            log.info("Odpytanie serwisu: " + uriCreateMemberFamily);
            return true;
        } catch (Exception e) {
            log.info("Wykonanie URI: " + uriCreateMemberFamily + " nie powiodło się " + e.getMessage());
            return false;
        }
    }

    public FamilyMember[] searchFamilyMember(Integer id) {
        FamilyMember[] result = restTemplate.getForObject(uriSearchMemberFamily + id.toString(), FamilyMember[].class);
        log.info("Pobrano dane rodziny o id: " + id.toString());
        return result;
    }
}
