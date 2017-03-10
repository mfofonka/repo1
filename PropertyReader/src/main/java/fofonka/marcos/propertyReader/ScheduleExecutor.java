package fofonka.marcos.propertyReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ScheduleExecutor {
    private static final Logger           logger     = LoggerFactory.getLogger(ScheduleExecutor.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("E dd/MM/yyyy HH:mm:ss");
    private static final ExecutorService  executor 	 = Executors.newFixedThreadPool(5);	
    
	// http://www.cronmaker.com cron for every hour
	@Scheduled(cron = "0 0 0/1 1/1 * ?")
	public void execTasksHourly() {
		String cmdHourly = System.getProperty("execTasksHourly");
		if (StringUtils.isEmpty(cmdHourly)) {
			return;
		}

		HashMap<String, String> keyValue = transformIntoParameters(cmdHourly);
		for (final String cmd : keyValue.keySet()) {
			String timeMaskToExec = keyValue.get(cmd);
			if (shouldExec(timeMaskToExec)) {
				Runnable task = new Runnable() {
					public void run() {
						executeCommandLineProgram(cmd);
					}
				};
				executor.execute(task);
			} else {
				logger.info("Skipped!!");
			}
		}
	}

    private void executeCommandLineProgram(String cmd) {
        Process process;
        try {
            // Create a process
            process = Runtime.getRuntime().exec(cmd);
            // read the output from the command
            try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                // print the output on the log file
                String line = null;
                while ((line = stdInput.readLine()) != null) {
                    logger.info(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        logger.info(String.format("Finished the Exec: %s!", cmd));
    }

    /**
     * TimeMask is RegExp based, so it should match with this pattern
     * <code>E dd/MM/yyyy HH:mm:ss</code>.<br>
     * return like Mon 26/12/2016 16:01:47 For example: <code>
     * .*(14:|15:|16:) matches with every day at 2,3,4 PM
     * (15/|16/) matches with 15th and 16th days of the month (every hour)
     * (15/|16/).*(14:|15:|16:) days 15th and 16th at 2,3,4 PM
     * </code>
     **/
    private boolean shouldExec(String timeMaskToExec) {
        Calendar now = Calendar.getInstance();
        return dateFormat.format(now.getTime()).matches(timeMaskToExec);
    }

    private HashMap<String, String> transformIntoParameters(String cmdHourly) {
        // key-value pair based format
        HashMap<String, String> keyValue = new HashMap<String, String>();
        for (int i = 0; i < cmdHourly.split("[,]").length; i = i + 2) {
            keyValue.put(cmdHourly.split("[,]")[i], cmdHourly.split("[,]")[i + 1]);
        }
        return keyValue;
    }
}
