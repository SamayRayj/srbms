package entity;
import java.time.LocalDateTime;

public class DateTimeRange {
    private LocalDateTime start;
    private LocalDateTime end;

    public DateTimeRange(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) throw new IllegalArgumentException("Start time must be before end time.");
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }

    public boolean overlapsWith(DateTimeRange other) {
        return !this.end.isBefore(other.start) && !this.start.isAfter(other.end);
    }

    public long getDurationInHours() {
        return java.time.Duration.between(start, end).toHours();
    }

    @Override
    public String toString() {
        return start + " → " + end;
    }
}