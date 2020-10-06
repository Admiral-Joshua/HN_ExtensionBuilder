package org.lunasphere.hn.models.internal;

import java.sql.Timestamp;

public class BuildJob {
    Long jobId;
    int extensionId, userId;
    JobStatus jobStatus;
    Timestamp submitted, started, completed;
    boolean rebuild;

    public static enum JobStatus {
        UNKNOWN,
        QUEUED,
        RUNNING,
        COMPLETED,
        CANCELLED;

        public static JobStatus fromInt(int i) {
            switch (i) {
                case 1:
                    return QUEUED;
                case 2:
                    return RUNNING;
                case 3:
                    return COMPLETED;
                case 4:
                    return CANCELLED;
                default:
                    return UNKNOWN;
            }
        }
    }

    public BuildJob(Long jobId, int extensionId, int userId, JobStatus jobStatus, Timestamp submitted, Timestamp started, Timestamp completed, boolean rebuild) {
        this.jobId = jobId;
        this.extensionId = extensionId;
        this.userId = userId;
        this.jobStatus = jobStatus;
        this.submitted = submitted;
        this.started = started;
        this.completed = completed;
        this.rebuild = rebuild;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public int getExtensionId() {
        return extensionId;
    }

    public void setExtensionId(int extensionId) {
        this.extensionId = extensionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getStarted() {
        return started;
    }

    public void setStarted(Timestamp started) {
        this.started = started;
    }

    public Timestamp getCompleted() {
        return completed;
    }

    public void setCompleted(Timestamp completed) {
        this.completed = completed;
    }

    public boolean isRebuild() {
        return rebuild;
    }

    public void setRebuild(boolean rebuild) {
        this.rebuild = rebuild;
    }
}
