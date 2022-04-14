package sugimomoto.samplecrud.model;

import java.util.List;
import lombok.*;

@Getter
@Setter
public class SleepSummary {
    private long apneaHypopneaIndex;
    private long asleepduration;
    private long breathingDisturbancesIntensity;
    private long deepsleepduration;
    private long durationtosleep;
    private long durationtowakeup;
    private long hrAverage;
    private long hrMax;
    private long hrMin;
    private long lightsleepduration;
    private long nbRemEpisodes;
    private List<Object> nightEvents;
    private long outOfBedCount;
    private long remsleepduration;
    private long rrAverage;
    private long rrMax;
    private long rrMin;
    private long sleepEfficiency;
    private long sleepLatency;
    private long sleepScore;
    private long snoring;
    private long snoringepisodecount;
    private long totalSleepTime;
    private long totalTimeinbed;
    private long wakeupLatency;
    private long wakeupcount;
    private long wakeupduration;
    private long waso;
}
