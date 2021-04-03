package pl.robert.repos.app.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import pl.robert.repos.app.domain.dto.ReposDto;
import pl.robert.repos.app.shared.UrlHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class ReposService {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public List<ReposDto> findAllRepositoriesByOwner(String owner, Sort sort) {
        String url = UrlHelper.buildUrl(UrlHelper.GITHUB_API_URL, "/users/", owner, "/repos");
        List<ReposDto> repos;
        try {
            String json = restTemplate.getForObject(url, String.class);
            repos = mapper.readValue(json, new TypeReference<>() {
            });
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (sort == null || sort.isUnsorted()) {
            return repos;
        }
        Boolean sortedByStarsAscending = isSortedByStarsAscending(sort);
        if (sortedByStarsAscending == null) {
            return repos;
        }
        return sortByStars(repos, sortedByStarsAscending);
    }

    private Boolean isSortedByStarsAscending(Sort sort) {
        return sort.stream()
                .filter(order -> "stars".equals(order.getProperty()))
                .map(Sort.Order::isAscending)
                .findFirst()
                .orElse(null);
    }

    private List<ReposDto> sortByStars(List<ReposDto> repos, Boolean sortedAscending) {
        List<ReposDto> sortedRepos = repos.stream()
                .sorted(Comparator.comparingInt(ReposDto::getStars))
                .collect(Collectors.toList());
        if (!sortedAscending) {
            Collections.reverse(sortedRepos);
        }
        return sortedRepos;
    }
}
