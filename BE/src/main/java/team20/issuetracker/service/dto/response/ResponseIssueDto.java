package team20.issuetracker.service.dto.response;

import lombok.*;
import team20.issuetracker.domain.assginee.Assignee;
import team20.issuetracker.domain.issue.Issue;
import team20.issuetracker.domain.issue.IssueAssignee;
import team20.issuetracker.domain.issue.IssueLabel;
import team20.issuetracker.domain.issue.IssueStatus;
import team20.issuetracker.domain.label.Label;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseIssueDto {

    private Long id;
    private String title;
    private String author;
    private LocalDate createAt;
    private IssueStatus issueStatus;
    private String milestoneTitle;
    private Set<ResponseLabelDto> labels;
    private Set<ResponseAssigneeDto> assignees;

    public static ResponseIssueDto from(Issue issue) {
        String milestoneTitle = " ";

        if (issue.getMilestone() != null) {
            milestoneTitle = issue.getMilestone().getTitle();
        }

        return new ResponseIssueDto(
                issue.getId(),
                issue.getTitle(),
                issue.getAuthorId(),
                issue.getCreatedAt(),
                issue.getStatus(),
                milestoneTitle,

                issue.getIssueLabels().stream()
                        .map(IssueLabel::getLabel)
                        .map(ResponseLabelDto::from)
                        .collect(Collectors.toSet()),

                issue.getIssueAssignees().stream()
                        .map(IssueAssignee::getAssignee)
                        .map(ResponseAssigneeDto::from)
                        .collect(Collectors.toSet()));
    }

    // 다 지워서 나중에 테스트 한 번 더해보기
    public static ResponseIssueDto from(Issue issue, Set<Label> label, Set<Assignee> assignee) {
        String milestoneTitle = " ";

        if (issue.getMilestone() != null) {
            milestoneTitle = issue.getMilestone().getTitle();
        }

        return new ResponseIssueDto(
                issue.getId(),
                issue.getTitle(),
                issue.getAuthorId(),
                issue.getCreatedAt(),
                issue.getStatus(),
                milestoneTitle,
                label.stream().map(ResponseLabelDto::from).collect(Collectors.toSet()),
                assignee.stream().map(ResponseAssigneeDto::from).collect(Collectors.toSet()));
    }
}
