package team20.issuetracker.domain.milestone;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team20.issuetracker.domain.AuditingFields;
import team20.issuetracker.service.dto.request.RequestUpdateMilestoneDto;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Milestone extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(length = 800)
    private String description;

    @Enumerated(EnumType.STRING)
    private MilestoneStatus milestoneStatus;

    private Milestone(Long id, String title, LocalDate startDate, LocalDate dueDate, String description) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.description = description;
        this.milestoneStatus = MilestoneStatus.OPEN;
    }

    private Milestone(String title, LocalDate startDate, LocalDate dueDate, String description) {
        this.title = title;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.description = description;
        this.milestoneStatus = MilestoneStatus.OPEN;
    }

    public static Milestone of(String title, LocalDate startDate, LocalDate endDate, String description) {
        return new Milestone(title, startDate, endDate, description);
    }

    public static Milestone of(Long id, String title, LocalDate startDate, LocalDate endDate, String description) {
        return new Milestone(id, title, startDate, endDate, description);
    }

    public void update(RequestUpdateMilestoneDto requestUpdateMilestoneDto) {
        this.title = requestUpdateMilestoneDto.getTitle();
        this.description = requestUpdateMilestoneDto.getDescription();
        this.startDate = requestUpdateMilestoneDto.getStartDate();
        this.dueDate = requestUpdateMilestoneDto.getEndDate();
        this.milestoneStatus = requestUpdateMilestoneDto.getMilestoneStatus();
    }
}
