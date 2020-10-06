package org.lunasphere.hn;

import org.lunasphere.hn.models.internal.BuildJob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BuilderService {
    public static void main(String[] args) throws InterruptedException {
        int threadCount = 4;

        SQLConfig sql = new SQLConfig("localhost", 5432, "EXTENSIONS_DB", "postgres", "postgres");

        final BlockingQueue<BuildJob> jobQueue = new ArrayBlockingQueue<BuildJob>(threadCount);

        Thread[] threads = new Thread[threadCount];

        /* Start up builder threads */
        for (int i = 0; i < threadCount; i++) {
            Runnable builderT = new BuilderThread(jobQueue, i, sql);
            threads[i] = new Thread(builderT);
            threads[i].start();
        }
        /* --- */

        try (Connection conn = sql.getConnection()) {
            // Get oldest job first
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM build_job WHERE job_status = 1 ORDER BY time_submitted ASC");

            while (true) {
                ResultSet results = stmt.executeQuery();

                int addedCount = 0;

                while (results.next()) {
                    //Long jobId, int extensionId, int userId, JobStatus jobStatus, Timestamp submitted, Timestamp started, Timestamp completed, boolean rebuild
                    BuildJob job = new BuildJob(
                            results.getLong("job_id"),
                            results.getInt("extension_id"),
                            results.getInt("user_id"),
                            BuildJob.JobStatus.fromInt(results.getInt("job_status")),
                            results.getTimestamp("time_submitted"),
                            results.getTimestamp("time_started"),
                            results.getTimestamp("time_completed"),
                            results.getBoolean("rebuild")
                    );

                    addedCount += 1;

                    boolean success = false;

                    while (!success) {
                        try {
                            jobQueue.put(job);
                            success = true;
                        } catch (InterruptedException ex) {
                            System.err.println("Queueing Thread interrupted whilst attempting to push job to queue. Awaiting 10 seconds before retry... ");
                        }

                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException ex) {
                            System.err.println("FATAL ERROR! - Queueing Thread interrupted whilst paused! Application exiting...");
                            System.exit(1);
                        }
                    }
                }

                results.close();

                System.out.println("Job Poll Completed!\nAdded " + addedCount + " job(s) to the queue.\nSleeping for 10 seconds before new poll...");
                Thread.sleep(10000);
            }
        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
            ex.printStackTrace();
        }
    }
}
