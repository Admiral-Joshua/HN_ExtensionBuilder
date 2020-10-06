package org.lunasphere.hn;

import org.lunasphere.hn.models.internal.BuildJob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

public class BuilderThread implements Runnable {
    int threadId;
    BlockingQueue<BuildJob> queue;
    SQLConfig db;

    public BuilderThread(BlockingQueue<BuildJob> queue, int threadId, SQLConfig db) {
        this.queue = queue;
        this.threadId = threadId;
        this.db = db;
    }

    @Override
    public void run() {
        while (true) {
            try {
                BuildJob job = queue.take();
                System.out.println("Thread #" + threadId + " - Build Started for Job #" + job.getJobId());

                try (Connection conn = db.getConnection()) {
                    PreparedStatement startedStmt = conn.prepareStatement("UPDATE build_job SET job_status = 2, time_started = CURRENT_TIMESTAMP WHERE job_id = ?");
                    startedStmt.setLong(1, job.getJobId());
                    startedStmt.executeUpdate();


                    ExtensionBuilder builder = new ExtensionBuilder(db, job.getJobId());

                    if (job.isRebuild()) {
                        builder.RebuildExtension(job.getExtensionId(), job.getUserId());
                    } else {
                        builder.BuildChanges(job.getExtensionId(), job.getUserId());
                    }

                    PreparedStatement completedStmt = conn.prepareStatement("UPDATE build_job SET job_status = 3, time_completed = CURRENT_TIMESTAMP WHERE job_id = ?");
                    completedStmt.setLong(1, job.getJobId());
                    completedStmt.executeUpdate();

                } catch (SQLException ex) {
                    System.err.println("Thread #" + threadId + " - Encountered an error whilst trying to update the DB job queue.\nError Message: " + ex.getMessage());
                    ex.printStackTrace();
                    //System.exit(1);
                }

                System.out.println("Thread #" + threadId + " - Build Job #" + job.getJobId() + " completed!\nThread sleeping...");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.err.println("Thread #" + threadId + " - Interrupted during processing! Thread exiting...");
                break;
            }
        }
    }
}
