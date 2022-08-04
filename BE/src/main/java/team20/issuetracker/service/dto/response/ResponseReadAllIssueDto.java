package team20.issuetracker.service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseReadAllIssueDto {

    private long openIssueCount;
    private long closedIssueCount;
    private long labelCount;
    private List<ResponseIssueDto> issues;

    public static ResponseReadAllIssueDto of(long openIssueCount, long closedIssueCount, long labelCount, List<ResponseIssueDto> issues) {
        return new ResponseReadAllIssueDto(openIssueCount, closedIssueCount, labelCount, issues);
    }
}
